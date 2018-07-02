package org.mike.jerry.launcher;


public class Main {
	
public static void main(String[] args) throws Exception {
		
		//load class path
		ClassPath classPath = new ClassPath();
		Thread.currentThread().setContextClassLoader(classPath.createClassLoader());
		
		Class<?> startupClass = 
				Thread.currentThread().getContextClassLoader().loadClass("org.mike.jerry.launcher.Bootstrap");
		Object instance = startupClass.newInstance();
		startupClass.getMethod("launch").invoke(instance);
		
	}
	
}
