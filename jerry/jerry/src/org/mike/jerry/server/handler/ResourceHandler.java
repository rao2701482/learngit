package org.mike.jerry.server.handler;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import org.apache.log4j.Logger;
import org.mike.jerry.exception.UnsupportedHttpMethodException;
import org.mike.jerry.http.HttpHeaders;
import org.mike.jerry.http.HttpMethod;
import org.mike.jerry.http.HttpStatus;
import org.mike.jerry.http.MimeTypes;
import org.mike.jerry.http.Request;
import org.mike.jerry.http.Response;
import org.mike.jerry.resource.Resource;
import org.mike.jerry.server.HttpOutputStream;
import org.mike.jerry.servlet.ServletHandler;
import org.mike.jerry.util.IOUtil;
import org.mike.jerry.util.SvrCfgUtil;

/**
 * Handle request resource context
 */
public class ResourceHandler {
	private static final Logger log = Logger.getLogger(ResourceHandler.class);
	
	private static String resourceBase;
	private static ServletHandler servletHandler = new ServletHandler();
	
	/**
	 * ETage cache for 304
	 */
//	private static HashMap<String, String> eTagCache = new HashMap<String, String>();
	
	private static final SimpleDateFormat GMT_SDF = 
			new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss 'GMT'", Locale.US);
	
	public static Response handle(Request request) throws IOException {
		
		Response resp = null;
		
		String resUrl = request.getRequestURI();
		//default index.html page
		if(resUrl.equals("/")) {
			resUrl = "/index.html";
		}
		
		//check servelt first
		try {
			resp = (Response) servletHandler.dispatchServlet(resUrl, request, HttpMethod.getMethod(request.getMethod()));
		} catch (UnsupportedHttpMethodException e) {
			log.error("Unsupported method :  " + request.getMethod());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(resp == null) {
			resp = new Response();
			Resource resource = new Resource(new File(resourceBase + resUrl), resUrl);
			
			if( !resource.exists() ) {
				resp.setStatus(HttpStatus.NOT_FOUND_404);
				return resp;
			}
			
			//Last-Modified
			HashMap<String, String> headers = resp.getHeaders();
			String lastModified = GMT_SDF.format(new Date(resource.lastModified()));
			headers.put(HttpHeaders.LAST_MODIFIED, lastModified);
			
			//ETag check
			String ifnm = request.getHeader(HttpHeaders.IF_NONE_MATCH);
			String etag=resource.getWeakETag();
			if(ifnm != null && ifnm.equals(etag)){
				
				resp.setStatus(HttpStatus.NOT_MODIFIED_304);
				return resp;
			}else{
				headers.put(HttpHeaders.ETAG, etag);
			}
			//check resource type
			int postfixIdx = resUrl.lastIndexOf(".");
			if(postfixIdx > 0) {
				headers.put(HttpHeaders.CONTENT_TYPE,
						MimeTypes.getType(resUrl.substring(postfixIdx)) );
			}
			
			byte[] outBytes;
			
			FileInputStream in = new FileInputStream(resource.getFile());
			outBytes = IOUtil.readBytes(in);
			in.close();
			
			resp.setBody(outBytes);
			headers.put(HttpHeaders.CONTENT_LENGTH, String.valueOf(outBytes.length));
		}else {
			HashMap<String, String> headers = resp.getHeaders();
			String contentType = resp.getContentType();
			if(contentType == null) {
				String encoding = resp.getCharacterEncoding();
				if(encoding != null) {
					headers.put(HttpHeaders.CONTENT_TYPE, MimeTypes.TEXT_HTML.getType() + "; " + encoding);
				}
			}
			
			byte[] outBytes = ((HttpOutputStream) resp.getOutputStream()).getOut().toByteArray();
			
			resp.setBody(outBytes);
			headers.put(HttpHeaders.CONTENT_LENGTH, String.valueOf(outBytes.length));
		}
		configGeneralHeaders(resp);
		resp.setStatus(HttpStatus.OK_200);
		return resp;
	}
	
	private static void configGeneralHeaders(Response resp) {
		HashMap<String, String> headers = resp.getHeaders();
		headers.put(HttpHeaders.CONNECTION, "Keep-Alive");
		headers.put(HttpHeaders.SERVER, SvrCfgUtil.SERVER_NAME);
		headers.put(HttpHeaders.DATE, GMT_SDF.format(new Date()));
		headers.put(HttpHeaders.CACHE_CONTROL, "private");
	}
	
	//setter and getter
	public static void setResourceBase(String resourceBase) {
		ResourceHandler.resourceBase = resourceBase;
	}

	public static String getResourceBase() {
		return resourceBase;
	}
	
}
