package com.shop;

public class User {
	private String username;
	private String userpass;
	
	User(String username,String userpass){
		this.username = username;
		this.userpass = userpass;
	}
	
	public void setUsername(String username){
		this.username = username;
	}
	
	public void setUserpass(String userpass){
		this.userpass = userpass;
	}
	
	public String getUsername(){
		return username;
	}
	
	public String getUserpass(){
		return userpass;
	}
	
}
