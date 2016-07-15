package com.linkinghack.SellingSystem.meta;

public class User {
	private String userName;
	private int userType;
	
	public User(){}
	public User(String userName,int userType){
		this.userName = userName;
		this.userType = userType;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getUserType() {
		return userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
	}
	
	
}
