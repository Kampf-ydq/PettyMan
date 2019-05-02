package com.man.activity;

import com.example.eat.R;
import com.man.dao.UserDao;
import com.man.entity.User;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

/*
 * 个人中心
 */
public class PersonalActivity extends Activity {
	
	private TextView nick;
	private TextView stuId;
	private TextView school;
	private TextView money;
	private TextView editInfo;
	private TextView editAddre;
	
	private String acc;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		setContentView(R.layout.personal_layout);
		
		Intent intent = getIntent();
		acc = intent.getStringExtra("stu_id");
		
		//控件初始化
		nick = (TextView) findViewById(R.id.nick_name);
		stuId = (TextView) findViewById(R.id.stu_number);
		school = (TextView) findViewById(R.id.school);
		money = (TextView) findViewById(R.id.money);
		editAddre = (TextView) findViewById(R.id.in_edit_add);
		editInfo = (TextView) findViewById(R.id.edit_infor);
		
		nick.setText(obtainUser().getNickName());
		stuId.setText(obtainUser().getAccount());
		school.setText(obtainUser().getSchool());
		money.setText(String.valueOf(obtainUser().getMoney()));
		
		//编辑收货地址
		editInfo.setOnClickListener(perListener);
		editAddre.setOnClickListener(perListener);
		
	}
	
	OnClickListener perListener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.edit_infor:
				Intent toEditInfo = new Intent(PersonalActivity.this, EditInfo.class);
				toEditInfo.putExtra("stu_id", acc);
				startActivityForResult(toEditInfo, 1);
				break;
			case R.id.in_edit_add:
				Intent toLookAdd = new Intent(PersonalActivity.this, LookAddress.class);
				toLookAdd.putExtra("stu_id", acc);
				startActivity(toLookAdd);
				break;
			default:
				break;
			}
			
		}
	};
	
	//获取acc账号的用户信息
	private User obtainUser(){
		UserDao userDao = new UserDao(PersonalActivity.this);
		User accUser = userDao.find(acc);
		return accUser;
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {
		case 1:
			if (resultCode == RESULT_OK) {
				String n = data.getStringExtra("nick");
				String s = data.getStringExtra("school");
				nick.setText(n);
				school.setText(s);
			}
			break;

		default:
			break;
		}
	}
	
}
