package com.brittonn.mvcpract.mock;

import java.io.InputStream;
import java.net.URI;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

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
		throw new UnsupportedOperationException();
	}

	@Override
	public Collection<String> getPropertyNames() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setProperty(String name, Object object) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void removeProperty(String name) {
		throw new UnsupportedOperationException();
	}

	@Override
	public UriInfo getUriInfo() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setRequestUri(URI requestUri) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setRequestUri(URI baseUri, URI requestUri) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Request getRequest() {
		throw new UnsupportedOperationException();
	}

	@Override
	public String getMethod() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setMethod(String method) {
		throw new UnsupportedOperationException();
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
		throw new UnsupportedOperationException();
	}

	@Override
	public Locale getLanguage() {
		throw new UnsupportedOperationException();
	}

	@Override
	public int getLength() {
		return 0;
	}

	@Override
	public MediaType getMediaType() {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<MediaType> getAcceptableMediaTypes() {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<Locale> getAcceptableLanguages() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Map<String, Cookie> getCookies() {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean hasEntity() {
		throw new UnsupportedOperationException();
	}

	@Override
	public InputStream getEntityStream() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setEntityStream(InputStream input) {
		throw new UnsupportedOperationException();
	}

	@Override
	public SecurityContext getSecurityContext() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setSecurityContext(SecurityContext context) {
		throw new UnsupportedOperationException();
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
