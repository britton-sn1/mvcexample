package com.brittonn.mvcpract.junit.rest;

import static org.junit.Assert.*;

import javax.ws.rs.core.Response;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.brittonn.mvcpract.mock.MockContainerRequestContext;
import com.brittonn.mvcpract.mock.MockMySecurityProvider;
import com.brittonn.mvcpract.mock.MockResourceInfo;
import com.brittonn.mvcpract.security.AuthorizationRequestFilter;
import com.brittonn.mvcpract.security.MySecurityProvider;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/autorizationFilterTestsAppContext.xml" })
public class AuthorizationRequestFilterTest {

	@Autowired 
	private AuthorizationRequestFilter authorizationRequestFilter;
	
	@Autowired
	private MockContainerRequestContext mockContainerRequestContext;
	
	@Autowired
	private MockResourceInfo mockResourceInfo; 

	@Before
	public void setup() {
		authorizationRequestFilter.setResourceInfo(mockResourceInfo);
	}
	
	@Test
	public void test() {
		autthorizationNotRequired();
		autthorizationRequiredNoHeaders();
		autthorizationRequiredNoTokenHeaderValidUserHeader();
		autthorizationRequiredNoTokenHeaderInvalidUserHeader();
		autthorizationRequiredValidTokenHeaderNoUserHeader();
		autthorizationRequiredValidTokenHeaderInvalidUserHeader();
		autthorizationRequiredValidTokenHeaderValidUserHeader();
		
	}

	private void autthorizationRequiredValidTokenHeaderValidUserHeader() {
		runAuthenticationRequiredAuthroizedTest(MockMySecurityProvider.VALID_TOKEN, MockMySecurityProvider.VALID_USER);
	}

	private void autthorizationRequiredValidTokenHeaderInvalidUserHeader() {
		runAuthenticationRequiredNotAuthroizedTest(MockMySecurityProvider.VALID_TOKEN, MockMySecurityProvider.INVALID_USER);
		
	}

	private void autthorizationRequiredValidTokenHeaderNoUserHeader() {
		runAuthenticationRequiredNotAuthroizedTest(MockMySecurityProvider.VALID_TOKEN, null);
	}

	private void autthorizationRequiredNoTokenHeaderInvalidUserHeader() {
		runAuthenticationRequiredNotAuthroizedTest(null, MockMySecurityProvider.INVALID_USER);
		
	}

	private void autthorizationRequiredNoTokenHeaderValidUserHeader() {
		runAuthenticationRequiredNotAuthroizedTest(null, MockMySecurityProvider.VALID_USER);
		
	}

	private void autthorizationRequiredNoHeaders() {
		runAuthenticationRequiredNotAuthroizedTest(null, null);
		
	}

	private void autthorizationNotRequired() {
		mockContainerRequestContext.reset();
		mockResourceInfo.setAutorizationRequired(false);
		authorizationRequestFilter.filter(mockContainerRequestContext);
		Response response = mockContainerRequestContext.getResponse();
		assertNull(response);
	}

	private void runAuthenticationRequiredNotAuthroizedTest(String token, String user) {
		runAuthenticationRequiredTest(token,user,401);
	}

	private void runAuthenticationRequiredAuthroizedTest(String token, String user) {
		runAuthenticationRequiredTest(token,user,-1);
	}
	
	private void runAuthenticationRequiredTest(String token, String user, int responseStatus) {
		mockContainerRequestContext.reset();
		if(token != null) mockContainerRequestContext.addHeader(MySecurityProvider.TOKEN_HEADER, token);
		if(user != null)mockContainerRequestContext.addHeader(MySecurityProvider.USER_HEADER, user);
		mockResourceInfo.setAutorizationRequired(true);
		authorizationRequestFilter.filter(mockContainerRequestContext);
		
		Response response = mockContainerRequestContext.getResponse();
		if(responseStatus != -1) {
			assertNotNull(response);
			assertTrue(response.getStatus() == responseStatus); 
		}
		else
			assertNull(response);
	}


}
