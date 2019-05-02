package com.man.entity;

public class User {
	
	private String userId;
	private String account;
	private String password;
	
	private String school;
	private String nickName;
	
	private String userName;
	private String telPhone;
	private String address;
	private double money;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getTelPhone() {
		return telPhone;
	}
	public void setTelPhone(String telPhone) {
		this.telPhone = telPhone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	
	public User() {
		super();
	}
	
	public User(String account, String password) {
		super();
		this.account = account;
		this.password = password;
	}
	
	public User(String userId, String account, String password, String school,
			String nickName, String userName, String telPhone, String address,
			double money) {
		super();
		this.userId = userId;
		this.account = account;
		this.password = password;
		this.school = school;
		this.nickName = nickName;
		this.userName = userName;
		this.telPhone = telPhone;
		this.address = address;
		this.money = money;
	}
	
	@Override
	public String toString() {
		return "User [userId=" + userId + ", account=" + account
				+ ", password=" + password + ", school=" + school
				+ ", nickName=" + nickName + ", userName=" + userName
				+ ", telPhone=" + telPhone + ", address=" + address
				+ ", money=" + money + "]";
	}
	
	
	
    
	

}
