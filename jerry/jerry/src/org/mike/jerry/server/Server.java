package org.mike.jerry.server;

import org.apache.log4j.Logger;
import org.mike.jerry.server.handler.ResourceHandler;
import org.mike.jerry.server.util.thread.ThreadPool;

/**
 * Server Main
 */
public class Server {
	
	private static final Logger log = Logger.getLogger(Server.class);
	
	private SocketConnector connector;
	private int port;
	
	private int minPoolSize = 5;
	private int maxPoolSize = 50;
	
	/**
	 * start svr
	 * @throws Exception 
	 */
	public void start(){
		try {
			//config threadpool
			connector.setThreadPool(new ThreadPool(minPoolSize, maxPoolSize));
		
			//open service 
			connector.open(port);
			connector.accept();
		} catch (Exception e) {
			log.error("Start Server Error : " + e.getLocalizedMessage());
		}
	}
	
	public SocketConnector getConnector() {
		return connector;
	}

	public void setConnector(SocketConnector connector) {
		this.connector = connector;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public void setResourceBase(String resourceBase){
		ResourceHandler.setResourceBase(resourceBase);
	}

	public int getMinPoolSize() {
		return minPoolSize;
	}

	public void setMinPoolSize(int minPoolSize) {
		this.minPoolSize = minPoolSize;
	}

	public int getMaxPoolSize() {
		return maxPoolSize;
	}

	public void setMaxPoolSize(int maxPoolSize) {
		this.maxPoolSize = maxPoolSize;
	}
	
}
