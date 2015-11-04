package com.brittonn.mvcpract.security;

import com.brittonn.mvcpract.springmvc.RequestNewPasswordForm;

public interface MySecurityProvider {
	
	static final String TOKEN_HEADER = "X-Auth-Token";
	static final String USER_HEADER = "X-Auth-User";
	
	/**
	 * Authenticate the user and return a security token if the user has been authenticated.   
	 * 
	 * @param user
	 * @param password
	 * @param requestedUserRoles
	 * 
	 * @return a security token
	 */
	String autheticate(String user, String password, String [] requestedUserRoles) throws UserNotAutenticatedException;
	void checkSecurityToken(String user, String token, String role) throws UserNotAutenticatedException, SessionTimedOutException;
	void sendNewPassword(RequestNewPasswordForm requestNewPasswordForm) throws PasswordNotSentException;

}
