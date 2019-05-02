package com.man.test;

import android.test.AndroidTestCase;
import com.man.dao.UserDao;
import com.man.entity.User;

/*
 * 注意: 写测试程序，需要在AndroidManifest中添加instrumentation及uses-library
 */
public class TestUserDao extends AndroidTestCase {
	public void testAdd(){
		User user = new User(null, "160405222", "123456", "sut", "娃哈哈", "小明", "123456", "沈工大",500);
		//User user2 = new User();
		//user2.setAccount("198402");
		//user2.setPassword("45130");
		UserDao userDao = new UserDao(getContext());//getContext()返回一个模拟的context
		userDao.add(user);
		//userDao.add(user2);
	}
	
	public void testDelete(){
		UserDao userDao = new UserDao(getContext());
		userDao.delete("3");
	}

	public void testUpdate(){
		
		UserDao userDao = new UserDao(getContext());
		userDao.updateAddress("Jack", "18012450039", "重庆市云阳县", "160405221");
	}
	
	public void testFind(){
		UserDao userDao = new UserDao(getContext());
		User user = userDao.find("198402");
		
		System.out.println(user.toString());
	}
}
