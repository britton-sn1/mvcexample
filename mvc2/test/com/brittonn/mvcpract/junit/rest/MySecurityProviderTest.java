package com.brittonn.mvcpract.junit.rest;

import static org.junit.Assert.*;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.brittonn.mvcpract.security.MySecurityProviderImpl;
import com.brittonn.mvcpract.security.SessionTimedOutException;
import com.brittonn.mvcpract.security.UserNotAutenticatedException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/securityProviderTestsAppContext.xml" })


public class MySecurityProviderTest {

	@Autowired
	MySecurityProviderImpl securityProvider;
	
	@Test
	public void test() {
		testGoodLogin();
		testBadPassword();
		testBaddUserName();
		testCheckGoodToken();
		testBadToken();
		testBadUser();
		testTimeout();
		
	}

	private void testGoodLogin() {
		String [] roles = { "*" };
		try {
			securityProvider.autheticate("admin", "Vytor101", roles  );
		} catch (UserNotAutenticatedException e) {
			 fail();
		}
		
	}

	private void testBadPassword() {
		String [] roles = { "*" };
		try {
			securityProvider.autheticate("admin", "HJKI", roles  );
		} catch (UserNotAutenticatedException e) {
			return; // passed
		}
		
		fail();
	}

	private void testBaddUserName() {
		String [] roles = { "*" };
		try {
			securityProvider.autheticate("admn", "Vytor101", roles  );
		} catch (UserNotAutenticatedException e) {
			return; // passed
		}
		
		fail();
	}

	private void testCheckGoodToken() {
		String [] roles = { "*" };
		String token;
		securityProvider.setSessionTimeout(5000);
		
		try {
			token = securityProvider.autheticate("admin", "Vytor101", roles  );
		} catch (UserNotAutenticatedException e) {
			 fail();
			 return;
		}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			fail();
			return;
		}

		try {
			securityProvider.checkSecurityToken("admin", token, "*");
		} catch (Exception e) {
			fail();
		}
	}

	private void testBadToken() {
		try {
			securityProvider.checkSecurityToken("admin", "badt token", "*");
		} catch (Exception e) {
			return; // passed;
		}
		fail();
		
	}

	private void testBadUser() {
		String [] roles = { "*" };
		String token;
		try {
			token = securityProvider.autheticate("admin", "Vytor101", roles  );
		} catch (UserNotAutenticatedException e) {
			 fail();
			 return;
		}
		
		try {
			securityProvider.checkSecurityToken("admn", token, "*");
		} catch (UserNotAutenticatedException e) {
			return; // test passed
		} catch (SessionTimedOutException e) {
			fail();
		}
		
		fail();
	}

	private void testTimeout() {
		String [] roles = { "*" };
		securityProvider.setSessionTimeout(500L);
		String token;
		try {
			token = securityProvider.autheticate("admin", "Vytor101", roles  );
		} catch (UserNotAutenticatedException e) {
			 fail();
			 return;
		}
		
		
		try {
			Thread.sleep(800);
		} catch (InterruptedException e) {
			fail();
			return;
		}
		
		try {
			securityProvider.checkSecurityToken("admin", token , "*"  );
		} catch (SessionTimedOutException e) {
			 return;
		} catch (UserNotAutenticatedException e) {
			fail();
		}
		fail();
		
	}

}
