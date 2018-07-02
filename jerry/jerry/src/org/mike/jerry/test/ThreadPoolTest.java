package org.mike.jerry.test;

import org.mike.jerry.server.util.thread.ThreadPool;

public class ThreadPoolTest {

	public static void main(String[] args) {
		
		ThreadPool pool = new ThreadPool();
		pool.setMinPoolSize(5);
		pool.setMaxPoolSize(5);
		
		for(int i=0;i<10;i++){
			pool.dispatch(new Thread(){
				public void run(){
					System.out.println(Thread.currentThread().getName());
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
		}

	}

}
