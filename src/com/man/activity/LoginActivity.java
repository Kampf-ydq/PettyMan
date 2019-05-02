package com.man.activity;

import com.example.eat.R;
import com.man.dao.UserDao;
import com.man.entity.User;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/*
 * 登录页
 */
public class LoginActivity extends Activity {
	
	private boolean isLoginSuccess = true;//登录是否成功
	
	private EditText stuId;
	private EditText pwd;
	private CheckBox rem;
	private Button loginBtn;
	private TextView regist;
	private TextView forgetPwd;

	
	private SharedPreferences sp;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//去掉标题栏
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		//设置全屏
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		setContentView(R.layout.login);
		
		//初始化控件
		stuId = (EditText)findViewById(R.id.std_id);
		pwd = (EditText)findViewById(R.id.pwd);
		rem = (CheckBox)findViewById(R.id.remember);
		loginBtn = (Button)findViewById(R.id.login);
		regist = (TextView) findViewById(R.id.regist);
		forgetPwd = (TextView) findViewById(R.id.forget_pwd);
		
		sp = getSharedPreferences("loginStatu", 0);
		
		
		boolean isRemember = sp.getBoolean("isChecked", false);//默认值为false
		
		//勾选了记住密码
		if (isRemember) {
			
			//回显,找到了number、password的值就返回该值，否则返回默认值
			String num = sp.getString("number", "");
			String passwd = sp.getString("password", "");
			
			stuId.setText(num);
			pwd.setText(passwd);
			rem.setChecked(true);
		}
		
		//采用OnClickListener()方法设置不同按钮下的监听事件
		loginBtn.setOnClickListener(mListener);
		regist.setOnClickListener(mListener);
		forgetPwd.setOnClickListener(mListener);
		
	}

	OnClickListener mListener = new OnClickListener() {//不同按钮下的监听事件
		
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.login:	//登录页面的登录按钮
				login();
				if (isLoginSuccess) {
					finish();//销毁
				}
				break;
			case R.id.regist:	//登录页面的注册选项
				Intent toRegist = new Intent();
				toRegist.setClass(LoginActivity.this, RegistActivity.class);
				startActivity(toRegist);
				finish();
				break;	
			case R.id.forget_pwd:	//登录页面的找回密码选项
				Intent toFindPwd = new Intent();
				toFindPwd.setClass(LoginActivity.this, FindPwdActivity.class);
				startActivity(toFindPwd);
				finish();
				break;
			default:
				break;
			}
			
		}
	};
	
	private void login(){
		//获取内容
		String studentId = stuId.getText().toString().trim();
		String password = pwd.getText().toString().trim();
		Editor editor = sp.edit();
		
		//判断输入是否合法
		if (TextUtils.isEmpty(studentId) || TextUtils.isEmpty(password)) {
			Toast.makeText(LoginActivity.this, "请输入学号和密码", Toast.LENGTH_SHORT).show();
			return;
		}
		
		//判断学号、密码是否正确
		UserDao finUserDao = new UserDao(LoginActivity.this);
		User dbUser = finUserDao.find(studentId);
		
		if (dbUser != null && studentId.equals(dbUser.getAccount()) && password.equals(dbUser.getPassword())) {//登录成功
			//判断是否勾选了checkBox
			if (rem.isChecked()) {
				//保存数据(使用SharedPreference)
				editor.putString("number", studentId);
				editor.putString("password", password);
				editor.putBoolean("isChecked", true);
			}else {
				editor.clear();
			}
			editor.commit();
			
			Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
			
			//进入主页
			Intent toHome = new Intent(LoginActivity.this, HomeActivity.class);
			
			//利用intent将学号带过去
			toHome.putExtra("stu_id", dbUser.getAccount());
			
			startActivity(toHome);
		}else {
			Toast.makeText(LoginActivity.this, "学号或密码错误", Toast.LENGTH_SHORT).show();
			isLoginSuccess = false;
		}	
	}
	
}
