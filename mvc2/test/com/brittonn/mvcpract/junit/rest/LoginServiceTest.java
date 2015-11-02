package com.brittonn.mvcpract.junit.rest;

import static org.junit.Assert.*;
import javax.ws.rs.core.Response;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.brittonn.mvcpract.mock.MockMySecurityProvider;
import com.brittonn.mvcpract.security.LoginDetails;
import com.brittonn.mvcpract.security.LoginService;
import com.brittonn.mvcpract.security.MySecurityProvider;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/loginServiceTestsAppContext.xml" })

public class LoginServiceTest {

	@Autowired
	private LoginService theService;
	
	@Test
	public void test() {
		testLoginOK();
		testBadPassword();
		testBadUserName();
	}

	private void testBadUserName() {
		LoginDetails loginForm = new LoginDetails();
		loginForm.setName(MockMySecurityProvider.INVALID_USER);
		loginForm.setPassword(MockMySecurityProvider.VALID_PASSWORD);
		Response response = theService.doLogin(loginForm);
		assertTrue(response.getStatus() == 401);
		
	}

	private void testBadPassword() {
		LoginDetails loginForm = new LoginDetails();
		loginForm.setName(MockMySecurityProvider.VALID_USER);
		loginForm.setPassword(MockMySecurityProvider.INVALID_PASSWORD);
		Response response = theService.doLogin(loginForm);
		assertTrue(response.getStatus() == 401);
	}

	private void testLoginOK() {
		LoginDetails loginForm = new LoginDetails();
		loginForm.setName(MockMySecurityProvider.VALID_USER);
		loginForm.setPassword(MockMySecurityProvider.VALID_PASSWORD);
		Response response = theService.doLogin(loginForm);
		assertTrue(response.getStatus() == 200);
		assertTrue(MockMySecurityProvider.VALID_TOKEN.equals(response.getHeaderString(MySecurityProvider.TOKEN_HEADER)));
		assertTrue(MockMySecurityProvider.VALID_USER.equals(response.getHeaderString(MySecurityProvider.USER_HEADER)));
	}

}
