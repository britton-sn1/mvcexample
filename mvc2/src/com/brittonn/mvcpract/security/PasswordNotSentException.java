package com.brittonn.mvcpract.security;

public class PasswordNotSentException extends Exception {

	private static final long serialVersionUID = -4045544757620898751L;

	public PasswordNotSentException(Exception e) {
		super(e);
	}

	public PasswordNotSentException(String msg) {
		super(msg);
	}

}
