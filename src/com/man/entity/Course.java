package com.man.entity;

import java.util.Arrays;

public class Course {
	private String courId;
	private String courName;
	private byte[] courImg;
	private String classRoom;
	private String classNum;
	private String teacher;
	public String getCourId() {
		return courId;
	}
	public void setCourId(String courId) {
		this.courId = courId;
	}
	public String getCourName() {
		return courName;
	}
	public void setCourName(String courName) {
		this.courName = courName;
	}
	public byte[] getCourImg() {
		return courImg;
	}
	public void setCourImg(byte[] courImg) {
		this.courImg = courImg;
	}
	public String getClassRoom() {
		return classRoom;
	}
	public void setClassRoom(String classRoom) {
		this.classRoom = classRoom;
	}
	public String getClassNum() {
		return classNum;
	}
	public void setClassNum(String classNum) {
		this.classNum = classNum;
	}
	public String getTeacher() {
		return teacher;
	}
	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}
	public Course(String courId, String courName, byte[] courImg,
			String classRoom, String classNum, String teacher) {
		super();
		this.courId = courId;
		this.courName = courName;
		this.courImg = courImg;
		this.classRoom = classRoom;
		this.classNum = classNum;
		this.teacher = teacher;
	}
	@Override
	public String toString() {
		return "Course [courId=" + courId + ", courName=" + courName
				+ ", courImg=" + Arrays.toString(courImg) + ", classRoom="
				+ classRoom + ", classNum=" + classNum + ", teacher=" + teacher
				+ "]";
	}
	
	

}
