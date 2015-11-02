package com.brittonn.mvcpract.mock;

import java.io.InputStream;
import java.net.URI;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;

import org.springframework.stereotype.Component;

@Component
public class MockContainerRequestContext implements ContainerRequestContext {

	private Response response;
	private Map<String,String> headerMap = new HashMap<>();
	
	@Override
	public Object getProperty(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<String> getPropertyNames() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setProperty(String name, Object object) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeProperty(String name) {
		// TODO Auto-generated method stub

	}

	@Override
	public UriInfo getUriInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setRequestUri(URI requestUri) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setRequestUri(URI baseUri, URI requestUri) {
		// TODO Auto-generated method stub

	}

	@Override
	public Request getRequest() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getMethod() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setMethod(String method) {
		// TODO Auto-generated method stub

	}

	@Override
	public MultivaluedMap<String, String> getHeaders() {
		return null;
	}

	@Override
	public String getHeaderString(String name) {
		return headerMap.get(name);
	}

	@Override
	public Date getDate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Locale getLanguage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getLength() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public MediaType getMediaType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MediaType> getAcceptableMediaTypes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Locale> getAcceptableLanguages() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Cookie> getCookies() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasEntity() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public InputStream getEntityStream() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setEntityStream(InputStream input) {
		// TODO Auto-generated method stub

	}

	@Override
	public SecurityContext getSecurityContext() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setSecurityContext(SecurityContext context) {
		// TODO Auto-generated method stub

	}

	@Override
	public void abortWith(Response response) {
		this.response = response;
	}

	public void reset() {
		headerMap.clear();
		response = null;
	}
	
	public Response getResponse() {
		return response;
	}
	
	public void addHeader(String name, String value) {
		headerMap.put(name, value);
	}
}
