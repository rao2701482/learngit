package org.mike.jerry.http;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Locale;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.mike.jerry.server.HttpOutputStream;
import org.mike.jerry.util.StringUtil;

public class Response implements HttpServletResponse {
	
	//private static final Logger log = Logger.getLogger(Response.class);
	
	private String httpVersion = "HTTP/1.1";
	private int status;
	private String characterEncoding;
	private String contentType;
	private int contentLength;
	
	private HashMap<String,String> headers = new HashMap<String, String>();

	private byte[] body;
	
	private HttpOutputStream outputStream = new HttpOutputStream();
	private PrintWriter writer;
	
	
	//setter and getter
	public String getHttpVersion() {
		return httpVersion;
	}

	public void setHttpVersion(String httpVersion) {
		this.httpVersion = httpVersion;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public HashMap<String, String> getHeaders() {
		return headers;
	}

	public byte[] getBody() {
		return body;
	}

	public void setBody(byte[] body) {
		this.body = body;
	}

	@Override
	public void flushBuffer() throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getBufferSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getCharacterEncoding() {
		return characterEncoding;
	}

	@Override
	public String getContentType() {
		return contentType;
	}

	@Override
	public Locale getLocale() {
		return Locale.getDefault();
	}

	@Override
	public ServletOutputStream getOutputStream() throws IOException {
		return outputStream;
	}

	@Override
	public PrintWriter getWriter() throws IOException {
		if (writer == null) {
            String encoding = characterEncoding;

            if (encoding == null){
                encoding = StringUtil.__ISO_8859_1;
                setCharacterEncoding(encoding);
            }
           writer = new PrintWriter(new OutputStreamWriter(outputStream, Charset.forName(characterEncoding)));
		}
		return writer;
	}

	@Override
	public boolean isCommitted() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resetBuffer() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setBufferSize(int arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setCharacterEncoding(String encode) {
		this.characterEncoding = encode;
	}

	@Override
	public void setContentLength(int length) {
		this.contentLength = length;
	}

	@Override
	public void setContentType(String type) {
		this.contentType = type;
	}

	@Override
	public void setLocale(Locale locale) {
		// TODO Auto-generated method stub
	}

	@Override
	public void addCookie(Cookie arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addDateHeader(String arg0, long arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addHeader(String arg0, String arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addIntHeader(String arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean containsHeader(String arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String encodeRedirectURL(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String encodeRedirectUrl(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String encodeURL(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String encodeUrl(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void sendError(int arg0) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendError(int arg0, String arg1) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendRedirect(String arg0) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setDateHeader(String arg0, long arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setHeader(String arg0, String arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setIntHeader(String arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setStatus(int arg0, String arg1) {
		// TODO Auto-generated method stub
		
	}
	public int getContentLength() {
		return contentLength;
	}
	
	
}
