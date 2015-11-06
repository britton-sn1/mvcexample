package com.brittonn.mvcpract.springmvc;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.brittonn.mvcpract.security.MySecurityProvider;
import com.brittonn.mvcpract.security.SessionTimedOutException;
import com.brittonn.mvcpract.security.UserNotAutenticatedException;

@Component
public class LoggedInInterceptor extends HandlerInterceptorAdapter {
	
	@Autowired 
	MySecurityProvider securityProvider;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		String path = request.getRequestURI();
		if(path.endsWith("/autheticate")) {
			return true; // Forward on to the login service
		}
		
		
		String user = null;
		String token = null;
		
		Cookie [] cookies = request.getCookies();
		for(Cookie cookie : cookies) {
			if(cookie.getName().equals(MySecurityProvider.USER_HEADER)) {
				user = cookie.getValue();
			} else if (cookie.getName().equals(MySecurityProvider.TOKEN_HEADER)) {
				token = cookie.getValue();
			}
				
		}
		
		
		if(token == null || token.trim().length() == 0 || user == null || user.trim().length() == 0) {
			response.setStatus(401);
			response.getOutputStream().write("<HTML><HEAD></HEAD><BODY>Not Authorized</BODY></HTML".getBytes());
			return false;
		}
		
		try {
			securityProvider.checkSecurityToken(user ,  token, "*");
		} catch (SessionTimedOutException stoe) {
			response.setStatus(401);
			response.getOutputStream().write("<HTML><HEAD></HEAD><BODY>Session timed out <a href=\"/mcvpract\">login</a> </BODY></HTML".getBytes());
			return false;
		} catch (UserNotAutenticatedException nae) {
			response.setStatus(401);
			response.getOutputStream().write("<HTML><HEAD></HEAD><BODY>Not Authorized <a href=\"/mcvpract\">login</a></BODY></HTML".getBytes());
			return false;
		}
		
		
		return true;
	}

}
