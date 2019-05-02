package com.man.test;

import java.util.List;

import com.example.eat.R;
import com.man.dao.CourseDao;
import com.man.entity.Course;

import android.test.AndroidTestCase;

public class TestCourseDao extends AndroidTestCase {
	
	public void testAdd(){
		CourseDao courseDao = new CourseDao(getContext());
		Course course = new Course(null, "算法导论", courseDao.bitmapToBytes(getContext(), R.drawable.suanfa), "C-214", "46", "杨威");
		courseDao.insert(course);
	}
	
	public void testFind(){
		CourseDao courseDao = new CourseDao(getContext());
		Course course = courseDao.find("1");
		if (course != null) {
			System.out.println(course);
		}
		
	}
	
	public void testGetAll(){
		CourseDao courseDao = new CourseDao(getContext());
		List<Course> courList = courseDao.getAll();
		
		if (courList != null) {
			System.out.println("大小: "+courList.size());
		}
	}

}
