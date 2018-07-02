package org.mike.jerry.servlet;

import java.lang.reflect.Method;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mike.jerry.exception.UnsupportedHttpMethodException;
import org.mike.jerry.http.HttpMethod;
import org.mike.jerry.http.Response;
import org.mike.jerry.webapp.WebInfConfiguration;
import org.mike.jerry.webapp.xml.WebXMLContext;

/**
 * Handle servelt request
 */
public class ServletHandler {
//	private static final Logger log = Logger.getLogger(ServletHandler.class);
	
	//scan webapp configure
	private ClassLoader webappClassLoader = WebInfConfiguration.getWebAppClassLoader();
	private Map<String, String> servletUrlMappings;
	
	public ServletHandler() {
		
		//read web.xml servelt identify
		WebXMLContext xmlCtx = new WebXMLContext();
		servletUrlMappings = xmlCtx.getServletUrlMappings();
		
	}
	
	/**
	 * dispatch to match servelt mapping. <br/>
	 * return null if no servelt mapping
	 * @param url
	 * @return
	 * @throws NoSuchMethodException 
	 * @throws SecurityException 
	 */
	public HttpServletResponse dispatchServlet(String url, HttpServletRequest req, HttpMethod method) 
		throws UnsupportedHttpMethodException, Exception {
		
		String className = servletUrlMappings.get(url);
		if(className == null){
			return null;
		}
		
		HttpServletResponse resp = new Response();
		
		try {
			Class<?> clazz = webappClassLoader.loadClass(className);
			Object instance = clazz.newInstance();
			Method clazzMethod ;
			
			if(method == HttpMethod.GET){
				clazzMethod = clazz.getDeclaredMethod("doGet", 
						new Class[] {HttpServletRequest.class, HttpServletResponse.class });
			}else if(method == HttpMethod.POST) {
				clazzMethod = clazz.getDeclaredMethod("doPost", 
						new Class[] {HttpServletRequest.class, HttpServletResponse.class });
			}else {
				throw new UnsupportedHttpMethodException();
			}
			clazzMethod.setAccessible(true);
			clazzMethod.invoke(instance, new Object[]{req, resp});
			
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return resp;
	}
	
}
