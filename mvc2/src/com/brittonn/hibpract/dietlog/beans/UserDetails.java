package com.brittonn.hibpract.dietlog.beans;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Entity;

@Entity
@Table(name = "userdetails")
public class UserDetails {

	private String username;
	private String pwhash;
	private String emailaddr;
	
	@Id
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPwhash() {
		return pwhash;
	}
	public void setPwhash(String pwhash) {
		this.pwhash = pwhash;
	}
	public String getEmailaddr() {
		return emailaddr;
	}
	public void setEmailaddr(String emailaddr) {
		this.emailaddr = emailaddr;
	}
	
}
