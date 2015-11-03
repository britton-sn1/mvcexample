package com.brittonn.mvcpract.springmvc;

public class RequestNewPasswordForm {

	private String username;
	private String emailaddr;
	
	public String getEmailaddr() {
		return emailaddr;
	}
	public void setEmailaddr(String emailaddr) {
		this.emailaddr = emailaddr;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
}
