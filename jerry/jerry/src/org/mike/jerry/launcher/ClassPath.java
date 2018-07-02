package org.mike.jerry.launcher;

import java.io.File;
import java.io.FilenameFilter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * lib/*jar file loader
 */
public class ClassPath {
	
	public ClassLoader createClassLoader() throws MalformedURLException {
		
		File commonLibDir = new File("lib");
		
		File[] repositories = commonLibDir.listFiles(new FilenameFilter() {
			public boolean accept(File dir, String name) {
				return name.endsWith(".jar")?true : false;
			}
		});
		
		URL[] urls = new URL[repositories.length];
		for(int i=0,size=repositories.length;i<size;i++){
			urls[i] = new URL(repositories[i].toURI().toString());
		}
		ClassLoader parent = Thread.currentThread().getContextClassLoader();
	    if (parent == null){
	    	parent = ClassPath.class.getClassLoader();
	    	if (parent == null) {
	    		parent = ClassLoader.getSystemClassLoader();
	    	}
	    }
		return new URLClassLoader(urls, parent);
	}
}
