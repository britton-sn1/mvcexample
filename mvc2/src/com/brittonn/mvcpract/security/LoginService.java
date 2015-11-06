package com.brittonn.mvcpract.security;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Path("login")
@Component

public class LoginService {

	@Autowired
	private MySecurityProvider securityProvider;
	
	@Context
    private ResourceInfo resourceInfo;

	@AutorizationNotRequired
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response doLogin(LoginDetails loginDetails) {
		
		String [] roles = {"*"};
		String token;
		try {
			token = securityProvider.autheticate(loginDetails.getName(), loginDetails.getPassword(), roles);
		} catch(UserNotAutenticatedException nae) {
			return  Response.ok("Not authorized").status(Response.Status.UNAUTHORIZED).build();
			
		}
		
		ResponseBuilder responseBuilder = Response.ok("authenticated").status(200);
		responseBuilder.header(MySecurityProvider.TOKEN_HEADER, token);
		responseBuilder.header(MySecurityProvider.USER_HEADER, loginDetails.getName());
		
		return responseBuilder.build();
	}
}
