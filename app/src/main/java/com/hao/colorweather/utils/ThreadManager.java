package com.hao.colorweather.utils;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * ==================================================
 * 版    权：
 * <p/>
 * 作    者：钟旺浩
 * <p/>
 * 版    本：1.0
 * <p/>
 * 日    期：2015/9/28 14:16
 * ===================================================
 */
public class ThreadManager {

	private ThreadManager(){
		
	}
	private static ThreadManager instance = new ThreadManager();
	private ThreadPoolProxy longPool;
	private ThreadPoolProxy shortPool;
	
	public static ThreadManager getInstance(){
		return instance;
	}
	/**
	 * cpu�ĺ���*2+1
	 * @return
	 */
	public synchronized ThreadPoolProxy createLongPool(){
		if(longPool==null){
			longPool = new ThreadPoolProxy(5, 5, 5000L);
		}
		return longPool;
	}
	
	public synchronized ThreadPoolProxy createShortPool(){
		if(shortPool==null){
			shortPool = new ThreadPoolProxy(3, 3, 5000L);
		}
		return shortPool;
	}
	
	
	
	/**
	 * �̳߳ش���
	 * @author Administrator
	 *
	 */
	public class ThreadPoolProxy{
		private ThreadPoolExecutor pool;
		private int corePoolSize;
		private int maximumPoolSize;
		private long keepAliveTime;
		public ThreadPoolProxy(int corePoolSize,int maximumPoolSize,long keepAliveTime){
			this.corePoolSize = corePoolSize;
			this.maximumPoolSize = maximumPoolSize;
			this.keepAliveTime =keepAliveTime;
		}
		
		public void excute(Runnable runnable){
			if(pool== null){
				//�����̳߳�
				/*
				 *1.�̳߳��������ٸ��߳�
				 *2.����Ŷ����ˣ����⿪���ٸ��߳���
				 *3.����̳߳���û��Ҫִ�е����� �����
				 * 4.ʱ�䵥λ
				 * 5.����̳߳��������̶߳��Ѿ����ˣ�ʣ�µ����� ��ʱ�浽LinkedBlockingDeque<Runnable>(10),10�Ŷӵ�����
				 */
				pool = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<Runnable>(10));
			}
			pool.execute(runnable);//�����̳߳أ�ִ���첽����
		}
		
		
		public void cancle(Runnable runnable){
			if(pool!=null && !pool.isShutdown() && !pool.isTerminated() ){
				pool.remove(runnable);//ȡ���첽����
			}
		}
	}
	
	
}
