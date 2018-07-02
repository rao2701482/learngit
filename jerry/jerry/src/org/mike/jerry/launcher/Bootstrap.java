package org.mike.jerry.launcher;

import org.mike.jerry.server.Server;
import org.mike.jerry.server.SocketConnector;
import org.mike.jerry.util.SvrCfgUtil;

public class Bootstrap {
	
	/**
	 * configuration setting and start server
	 */
	public void launch() {
		//config server
		Server svr = new Server();
		
		svr.setConnector(new SocketConnector());
		svr.setPort(SvrCfgUtil.getInt(SvrCfgUtil.SERVER_PORT));
		svr.setResourceBase(SvrCfgUtil.getString(SvrCfgUtil.CONTEXT_DOC_BASE));
		
		svr.setMinPoolSize(SvrCfgUtil.getInt(SvrCfgUtil.SERVER_THREADPOOL_MIN_SIZE));
		svr.setMaxPoolSize(SvrCfgUtil.getInt(SvrCfgUtil.SERVER_THREADPOOL_MAX_SIZE));
		
		svr.start();
	}
	
}
