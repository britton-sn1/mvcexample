package com.brittonn.mvcpract.security;

public class LoginDetails {

	private String name;
	private String password;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = hash(password);
	}
	private static String hash(String pwd) {
		// TODO hash password
		return pwd;
	}
	
	
}
