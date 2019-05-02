package com.man.activity;

import com.example.eat.R;
import com.man.dao.UserDao;
import com.man.entity.User;

import android.R.string;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/*
 * 编辑收货地址
 */
public class EditAddress extends Activity {
	
	private EditText name;
	private EditText tel;
	private EditText address;
	private String acc;
	private Button saveBtn;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		setContentView(R.layout.edit_address);
		
		//获取账号
		Intent intent = getIntent();
		acc = intent.getStringExtra("stu_id");
		
		//初始化控件
		name = (EditText) findViewById(R.id.edit_name);
		tel = (EditText) findViewById(R.id.edit_tel);
		address = (EditText) findViewById(R.id.edit_add);
		saveBtn = (Button) findViewById(R.id.save_edit_add);
		
		
		//保存修改值
		saveBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String userName = name.getText().toString().trim();
				String telPhone = tel.getText().toString().trim();
				String add = address.getText().toString().trim();
				
				if (TextUtils.isEmpty(userName) || TextUtils.isEmpty(telPhone) || TextUtils.isEmpty(add)) {
					Toast.makeText(EditAddress.this, "请填入正确的信息", 0).show();
					return;
				}
				
				//更新数据库信息
				UserDao userDao = new UserDao(EditAddress.this);
				userDao.updateAddress(userName, telPhone, add, acc);
				Toast.makeText(EditAddress.this, "修改成功", 0).show();
				
				//返回地址显示页面,并带回修改后的数据
				Intent toLookAdd = new Intent();
				toLookAdd.putExtra("ret_name", userName);
				toLookAdd.putExtra("ret_tel", telPhone);
				toLookAdd.putExtra("ret_add", add);
				setResult(RESULT_OK, toLookAdd);
				finish();//销毁活动
			}
		});
	}

}
