package org.mike.jerry.server.util.thread;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

public class ThreadPool {
	private static final Logger log = Logger.getLogger(ThreadPool.class);
	
	private int minPoolSize = 5;
	private int maxPoolSize = 50;
	/**
	 * unit: second, default is 5
	 */
	private int keepAliveTime = 5;
	
	ThreadPoolExecutor pool;
	
	public ThreadPool() {
		init();
	}
	
	public ThreadPool(int minPoolSize, int maxPoolSize) {
		this.minPoolSize = minPoolSize;
		this.maxPoolSize = maxPoolSize;
		init();
	}
	
	private void init() {
		LinkedBlockingQueue<Runnable> queue = new LinkedBlockingQueue<Runnable>();//扩展队列为0
		pool = new ThreadPoolExecutor(minPoolSize, maxPoolSize,
				keepAliveTime, TimeUnit.SECONDS, queue,new RejectedExecutionHandler() {
			@Override
			public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
				log.error("Server over load , connect is too much !!!");
			}
		});
	}
	
	public void dispatch(Runnable runnable) {
		pool.execute(runnable);
	}

	public void setMinPoolSize(int minPoolSize) {
		this.minPoolSize = minPoolSize;
	}

	public void setMaxPoolSize(int maxPoolSize) {
		this.maxPoolSize = maxPoolSize;
	}

	public void setKeepAliveTime(int keepAliveTime) {
		this.keepAliveTime = keepAliveTime;
	}
	
	
}
