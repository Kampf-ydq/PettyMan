package com.man.activity;

import com.example.eat.R;
import com.man.dao.UserDao;
import com.man.entity.User;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/*
 * 用户注册
 */
public class RegistActivity extends Activity {
	
	private EditText account;
	private EditText password;
	private EditText pwdSure;
	private Button registBtn;
	private boolean flag = false;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.regist);
		
		//初始化控件
		account = (EditText) findViewById(R.id.account);
		password = (EditText) findViewById(R.id.password);
		pwdSure = (EditText) findViewById(R.id.pwd_sure);
		registBtn = (Button) findViewById(R.id.regist);

		
		registBtn.setOnClickListener(regListener);
	}
	
	OnClickListener regListener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			if (v.getId() == R.id.regist) {
				
				regist();
				
				//註冊成功，前去登錄
				if (flag) {
					Intent intent = new Intent(RegistActivity.this, LoginActivity.class);
					startActivity(intent);
					finish();
				}
				
			}
		}
	};
	
	private static boolean isNumeric(String str){
		for (int i = 0; i < str.length(); i++) {
			if (!Character.isDigit(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}
	
	private void regist(){
		String acc = account.getText().toString().trim();
		String pd = password.getText().toString().trim();
		String pd_sure = pwdSure.getText().toString().trim();
		
		//判断输入是否为空
		if (TextUtils.isEmpty(acc) || TextUtils.isEmpty(pd) || TextUtils.isEmpty(pd_sure)) {
			Toast.makeText(RegistActivity.this, "请正确填写学号和密码", 0).show();
			return;
		}
		
		//判断输入是否合法
		if (acc.length() != 9 || !isNumeric(acc)) {
			Toast.makeText(RegistActivity.this, "学号格式不对", 0).show();
			return;
		}
		if (pd.length() != 6 || !isNumeric(pd)) {
			Toast.makeText(RegistActivity.this, "密码格式不对", 0).show();
			return;
		}
		if (!pd.equals(pd_sure)) {
			Toast.makeText(RegistActivity.this, "两次输入的密码不一致", 0).show();
			return;
		}
		
		//判断该学号是否已注册
		UserDao findUserDao = new UserDao(RegistActivity.this);
		User dbUser = findUserDao.find(acc);
		
		//一定要注意判斷查找結果是否為空
		if (dbUser != null && acc.equals(dbUser.getAccount())) {
			Toast.makeText(RegistActivity.this, "该学号已注册，请前往登录", 0).show();
			return;
		}
		
	    //一切合法，完成注册
		User user = new User(acc, pd);
		System.out.println(user);
		UserDao addUserDao = new UserDao(RegistActivity.this);
		addUserDao.add(user);
		flag = true;
		Toast.makeText(RegistActivity.this, "注册成功", 0).show();
	}

}
