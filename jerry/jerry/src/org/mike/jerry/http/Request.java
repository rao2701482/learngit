package org.mike.jerry.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletInputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.mike.jerry.exception.ServerException;
import org.mike.jerry.io.EndPoint;
import org.mike.jerry.util.StringUtil;

/**
 * Request entity
 */
public class Request implements HttpServletRequest {
	
	String method; // request method
	
	String requestURI;// resource url
	
	String version; // http version
	
	String characterEncoding;
	
	Map<String,String> headers;// http headers
	
	HashMap<String,String> parameters; //query parameter
	
	String queryString;
	
	EndPoint endp;
	private String protocol = HttpVersions.HTTP_1_1;
	private BufferedReader reader;
	
	ServletInputStream in;
	
	//setter and getter
	@Override
	public Object getAttribute(String attr) {
		return null;
	}
	
	@Override
	public Enumeration<?> getAttributeNames() {
		return null;
	}
	
	@Override
	public String getCharacterEncoding() {
		return characterEncoding;
	}
	
	@Override
	public int getContentLength() {
		int len = 0;
		try{
			len = Integer.parseInt(parameters.get(HttpHeaders.CONTENT_LENGTH));
		}catch(ServerException e) {}
		
		return  len;
	}
	
	@Override
	public String getContentType() {
		return parameters.get(HttpHeaders.CONTENT_TYPE);
	}
	
	@Override
	public ServletInputStream getInputStream() throws IOException {
		return in;
	}
	
	@Override
	public String getLocalAddr() {
		return endp == null ? null : endp.getLocalAddr();
	}
	@Override
	public String getLocalName() {
		return endp == null ? null : endp.getLocalHost();
	}
	@Override
	public int getLocalPort() {
		return endp == null ? -1 : endp.getLocalPort();
	}
	@Override
	public Locale getLocale() {
		return Locale.getDefault();
	}
	@Override
	public Enumeration<?> getLocales() {
		return null;
	}
	@Override
	public String getParameter(String key) {
		return parameters.get(key);
	}
	@Override
	public Map<String,String> getParameterMap() {
		return parameters;
	}
	@Override
	public Enumeration<String> getParameterNames() {
		return Collections.enumeration(parameters.keySet());
	}
	@Override
	public String[] getParameterValues(String arg0) {
		return null;
	}
	@Override
	public String getProtocol() {
		return protocol;
	}
	@Override
	public BufferedReader getReader() throws IOException {
		//define encoding charset
		String encoding = getCharacterEncoding();
		if (encoding == null)
            encoding = StringUtil.__ISO_8859_1;
		
		if(reader == null){
			reader = new BufferedReader(new InputStreamReader(in, encoding)){
				@Override
                public void close() throws IOException {
                    in.close();
                }
			};
		}
		return reader;
	}
	@Override
	public String getRealPath(String arg0) {
		return null;
	}
	
	@Override
	public String getRemoteAddr() {
		return endp == null ? null : endp.getRemoteAddr();
	}
	
	@Override
	public String getRemoteHost() {
		return endp == null ? null : endp.getRemoteHost();
	}
	@Override
	public int getRemotePort() {
		return endp == null ? -1 : endp.getRemotePort();
	}
	
	@Override
	public RequestDispatcher getRequestDispatcher(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String getScheme() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String getServerName() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public int getServerPort() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public boolean isSecure() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public void removeAttribute(String arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setAttribute(String arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void setCharacterEncoding(String arg0)
			throws UnsupportedEncodingException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public String getAuthType() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getContextPath() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Cookie[] getCookies() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public long getDateHeader(String arg0) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public String getHeader(String key) {
		return headers.get(key);
	}
	
	@Override
	public Enumeration<String> getHeaderNames() {
		return Collections.enumeration(headers.keySet());
	}
	
	@Override
	public Enumeration<String> getHeaders(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public int getIntHeader(String arg0) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public String getMethod() {
		return method;
	}
	@Override
	public String getPathInfo() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getPathTranslated() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getQueryString() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getRemoteUser() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getRequestURI() {
		return requestURI;
	}
	@Override
	public StringBuffer getRequestURL() {
		return null;
	}
	@Override
	public String getRequestedSessionId() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getServletPath() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public HttpSession getSession() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public HttpSession getSession(boolean arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Principal getUserPrincipal() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean isRequestedSessionIdFromCookie() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean isRequestedSessionIdFromURL() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean isRequestedSessionIdFromUrl() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean isRequestedSessionIdValid() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean isUserInRole(String arg0) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	
}
