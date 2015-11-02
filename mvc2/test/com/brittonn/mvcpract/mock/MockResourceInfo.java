package com.brittonn.mvcpract.mock;

import java.lang.reflect.Method;

import javax.ws.rs.container.ResourceInfo;

import org.springframework.stereotype.Component;

import com.brittonn.mvcpract.security.AutorizationNotRequired;

@Component
public class MockResourceInfo implements ResourceInfo {

	private boolean autorizationRequired;
	
	@AutorizationNotRequired
	public void autorizationNotRequeuired() {
	}
	
	public void autorizationRequeuired() {
	}
	
	
	@Override
	public Method getResourceMethod() {
		try {
			
		return autorizationRequired ?
			this.getClass().getMethod("autorizationRequeuired", null) :
				this.getClass().getMethod("autorizationNotRequeuired", null);
		} catch(Exception e) {
			
		}
		
		return null;
	}

	@Override
	public Class<?> getResourceClass() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isAutorizationRequired() {
		return autorizationRequired;
	}

	public void setAutorizationRequired(boolean autorizationRequired) {
		this.autorizationRequired = autorizationRequired;
	}

}
