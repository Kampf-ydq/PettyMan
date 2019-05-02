package com.man.dao;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.man.entity.Course;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import db.helper.MySQLiteHelper;

public class CourseDao {

	MySQLiteHelper helper;
	
	public CourseDao(Context context){
		helper = new MySQLiteHelper(context);
	}
	
	public void insert(Course course ){
		SQLiteDatabase db = helper.getWritableDatabase();
		db.execSQL("insert into t_course values(null,?,?,?,?,?)", new Object[]{
				course.getCourName(),course.getCourImg(),course.getClassRoom(),course.getClassNum(),course.getTeacher()
		});
	}
	
	public void delete(String id){
		SQLiteDatabase db = helper.getWritableDatabase();
		db.execSQL("delete from t_course where courId=?", new Object[]{id});
	}
	
	public Course find(String id){
		SQLiteDatabase db = helper.getReadableDatabase();
		Cursor cursor = db.rawQuery("select * from t_course where courId=?", new String[]{id});
		
		Course course = null;
		
		if (cursor.moveToNext()) {
			String courId = cursor.getString(cursor.getColumnIndex("courId"));
			String courName = cursor.getString(cursor.getColumnIndex("courName"));
			byte[] courImg = cursor.getBlob(cursor.getColumnIndex("courImg"));
			String classRoom = cursor.getString(cursor.getColumnIndex("classRoom"));
			String classNum = cursor.getString(cursor.getColumnIndex("classNum"));
			String teacher = cursor.getString(cursor.getColumnIndex("teacher"));
			
			course = new Course(courId, courName, courImg, classRoom, classNum, teacher);
		}
		return course;
	}
	
	public List<Course> getAll(){
		List<Course> courList = new ArrayList<Course>();
		SQLiteDatabase db = helper.getReadableDatabase();
		Cursor cursor = db.rawQuery("select * from t_course", null);
		
		while (cursor.moveToNext()) {
			int courId = cursor.getInt(cursor.getColumnIndex("courId"));
			String courName = cursor.getString(cursor.getColumnIndex("courName"));
			byte[] courImg = cursor.getBlob(cursor.getColumnIndex("courImg"));
			String classRoom = cursor.getString(cursor.getColumnIndex("classRoom"));
			String classNum = cursor.getString(cursor.getColumnIndex("classNum"));
			String teacher = cursor.getString(cursor.getColumnIndex("teacher"));
			
			Course course = new Course(String.valueOf(courId), courName, courImg, classRoom, classNum, teacher);
			courList.add(course);
		}
		
		return courList;
	}
	
	//将图片转为byte[]
	public byte[] bitmapToBytes(Context context,int id){
		 ByteArrayOutputStream baos = new ByteArrayOutputStream();
	     Bitmap bitmap = ((BitmapDrawable) context.getResources().getDrawable(id)).getBitmap();
	     bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
	     return baos.toByteArray();
	}
	
	//将Byte[]转为Bitmap
	public Bitmap chageToBitmap(byte[] in)
	{
		Bitmap bmpout = BitmapFactory.decodeByteArray(in, 0, in.length);
	    return bmpout;
	}
}
