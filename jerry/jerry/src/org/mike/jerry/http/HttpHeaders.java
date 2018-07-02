package org.mike.jerry.http;


public class HttpHeaders {
	//private static final HashMap<String,String> bufferMap=new HashMap<String,String>();
	
	/** General Fields.
     */
    public final static String 
        CONNECTION= "Connection",
        CACHE_CONTROL= "Cache-Control",
        DATE= "Date";
	
    /** Entity Fields.
     */
    public final static String ALLOW= "Allow",
        CONTENT_ENCODING= "Content-Encoding",
        CONTENT_LANGUAGE= "Content-Language",
        CONTENT_LENGTH= "Content-Length",
        CONTENT_LOCATION= "Content-Location",
        CONTENT_TYPE= "Content-Type",
        EXPIRES= "Expires",
        LAST_MODIFIED= "Last-Modified";
    
	/** Request Fields.
     */
	public final static String 
		ACCEPT= "Accept",
	    ACCEPT_ENCODING= "Accept-Encoding",
	    ACCEPT_LANGUAGE= "Accept-Language",
	    HOST= "Host",
	    USER_AGENT= "User-Agent",
		IF_MODIFIED_SINCE= "If-Modified-Since",
        IF_NONE_MATCH= "If-None-Match",
        IF_RANGE= "If-Range",
        IF_UNMODIFIED_SINCE= "If-Unmodified-Since",
        KEEP_ALIVE= "Keep-Alive",
		REFERER= "Referer",
        X_FORWARDED_FOR= "X-Forwarded-For",
        X_FORWARDED_PROTO= "X-Forwarded-Proto",
        X_FORWARDED_SERVER= "X-Forwarded-Server",
        X_FORWARDED_HOST= "X-Forwarded-Host";
	    
	/**
	 * Response Fields
	 */
	 public final static String ACCEPT_RANGES= "Accept-Ranges",
		AGE= "Age",
		ETAG= "ETag",
		LOCATION= "Location",
		PROXY_AUTHENTICATE= "Proxy-Authenticate",
		RETRY_AFTER= "Retry-After",
		SERVER= "Server",
		SERVLET_ENGINE= "Servlet-Engine",
		VARY= "Vary",
		WWW_AUTHENTICATE= "WWW-Authenticate";
	
    /** Other Fields.
     */
    public final static String COOKIE= "Cookie",
        SET_COOKIE= "Set-Cookie",
        SET_COOKIE2= "Set-Cookie2",
        MIME_VERSION= "MIME-Version",
        IDENTITY= "identity";
    
    /*static{
    	//General Fields
    	bufferMap.put(CONNECTION, null);
    	//Entity Fields
    	bufferMap.put(ALLOW, null);
    	bufferMap.put(CONTENT_ENCODING, null);
    	bufferMap.put(CONTENT_LANGUAGE, null);
    	bufferMap.put(CONTENT_LENGTH, null);
    	bufferMap.put(CONTENT_LOCATION, null);
    	bufferMap.put(CONTENT_TYPE, null);
    	bufferMap.put(EXPIRES, null);
    	bufferMap.put(LAST_MODIFIED, null);
    	//Request Fields
    	bufferMap.put(ACCEPT, null);
    	bufferMap.put(ACCEPT_ENCODING, null);
    	bufferMap.put(ACCEPT_LANGUAGE, null);
    	bufferMap.put(HOST, null);
    	bufferMap.put(USER_AGENT, null);
    	//Response Fields
    	
    	
    }*/
    
}
