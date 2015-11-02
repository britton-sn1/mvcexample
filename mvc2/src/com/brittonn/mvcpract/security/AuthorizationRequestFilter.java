package com.brittonn.mvcpract.security;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import org.springframework.beans.factory.annotation.Autowired;

@Provider
public class AuthorizationRequestFilter implements ContainerRequestFilter {

	@Context
	private ResourceInfo resourceInfo;

	@Autowired
	private MySecurityProvider securityProvider;

	@Override
	public void filter(ContainerRequestContext requestContext) {

		Annotation[] annotations = resourceInfo.getResourceMethod().getDeclaredAnnotations();
		if (Arrays.asList(annotations).stream().filter(a -> a.annotationType() == AutorizationNotRequired.class)
				.count() > 0)
			return;

		try {

			String token = requestContext.getHeaderString(MySecurityProvider.TOKEN_HEADER);
			String user = requestContext.getHeaderString(MySecurityProvider.USER_HEADER);
			
			if (token == null || token.trim().length() == 0 || user == null || user.trim().length() == 0) {
				throw new UserNotAutenticatedException();
			}
			
			String role =  "*" ;
			securityProvider.checkSecurityToken(user, token, role);
			return;
		} catch (UserNotAutenticatedException nae) {

			requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).entity("Not authorized").build());
		} catch (SessionTimedOutException ste) {
			requestContext.abortWith(Response.status(Response.Status.REQUEST_TIMEOUT).entity("Session timed out").build());
		}

	}
	
	// Needed for junit test
	public void setResourceInfo(ResourceInfo resourceInfo) {
		this.resourceInfo = resourceInfo;
	}
}