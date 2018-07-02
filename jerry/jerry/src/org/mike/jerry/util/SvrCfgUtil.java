package org.mike.jerry.util;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.FileConfiguration;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;
import org.apache.log4j.Logger;


public class SvrCfgUtil {
	public static final String SERVER_NAME = "M";
	/**
	 * properties key
	 */
	public static final String CONTEXT_DOC_BASE = "context.docBase";
	public static final String SERVER_PORT = "server.port";
	public static final String SERVER_THREADPOOL_MIN_SIZE = "server.threadpool.minSize";
	public static final String SERVER_THREADPOOL_MAX_SIZE = "server.threadpool.maxSize";
	
	
	private static final Logger log = Logger.getLogger(SvrCfgUtil.class);
	private static FileConfiguration fileConfiguration;
	
	static {
		try {
			fileConfiguration = new PropertiesConfiguration("conf/config.properties");
			fileConfiguration.setReloadingStrategy(new FileChangedReloadingStrategy());			
		} catch (ConfigurationException e) {
			log.error("load config.properties error !",e);
		}
	}

	public static String getString(String propertyName) {
		return fileConfiguration.getString(propertyName);
	}
	
	public static int getInt(String propertyName) {
		return fileConfiguration.getInt(propertyName);
	}
	
	public static boolean getBoolean(String propertyName) {
		return fileConfiguration.getBoolean(propertyName);
	}
}
