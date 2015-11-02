package com.brittonn.mvcpract.mock;

import org.springframework.stereotype.Component;

import com.brittonn.mvcpract.security.MySecurityProvider;
import com.brittonn.mvcpract.security.SessionTimedOutException;
import com.brittonn.mvcpract.security.UserNotAutenticatedException;

@Component
public class MockMySecurityProvider implements MySecurityProvider {

	static public final String VALID_USER = "validuser"; 
	static public final String INVALID_USER = "invaliduser";
	static public final String VALID_PASSWORD = "validpassword";
	static public final String INVALID_PASSWORD = "invalidpassword";
	static public final String VALID_TOKEN = "validtoken";
	static public final String INVALID_TOKEN = "invalidtoken";
	
	@Override
	public String autheticate(String user, String password, String[] requestedUserRoles)
			throws UserNotAutenticatedException {
		if(! (VALID_USER.equals(user) && VALID_PASSWORD.equals(password))) {
			throw new UserNotAutenticatedException();
		}
		
		return VALID_TOKEN;
	}

	@Override
	public void checkSecurityToken(String user, String token, String role)
			throws UserNotAutenticatedException, SessionTimedOutException {

		if(! (VALID_USER.equals(user) && VALID_TOKEN.equals(token))) {
			throw new UserNotAutenticatedException();
		}

	}

}
