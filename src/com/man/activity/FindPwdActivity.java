package com.man.activity;

import com.example.eat.R;
import com.man.dao.UserDao;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/*
 * 找回密码
 */
public class FindPwdActivity extends Activity {
	
	private LinearLayout pwdArea;
	private EditText inputAcc;
	private Button findBtn;
	private TextView showPwd;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		//去掉标题栏
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		//设置全屏
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		setContentView(R.layout.find_pwd_layout);
		
		//初始化控件
		pwdArea = (LinearLayout) findViewById(R.id.pwd_area);
		inputAcc = (EditText) findViewById(R.id.input_account);
		findBtn = (Button) findViewById(R.id.sure_find);
		showPwd = (TextView) findViewById(R.id.show_password);
		
		findBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//获取账号
				String acc = inputAcc.getText().toString().trim();
				if (TextUtils.isEmpty(acc)) {
					Toast.makeText(FindPwdActivity.this, "学号不能为空", 0).show();
					return;
				}
				
				UserDao userDao = new UserDao(FindPwdActivity.this);
				if (userDao.find(acc) == null) {
					Toast.makeText(FindPwdActivity.this, "该学号尚未注册", 0).show();
					return;
				}
				
				showPwd.setText(userDao.find(acc).getPassword());
				
				//将密码区设置为可见
				pwdArea.setVisibility(View.VISIBLE);
				
				Toast.makeText(FindPwdActivity.this, "找回成功", 0).show();
				
			}
		});
	}
}
