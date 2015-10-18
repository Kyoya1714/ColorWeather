package com.hao.colorweather;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
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
public class BaseApplication extends Application {
	private static Application application;
	private static int mainTid;
	public static Handler handler;
	@Override
	public void onCreate() {
		super.onCreate();
		application = this;
		mainTid = android.os.Process.myTid();
		handler = new Handler();
	}
	
	public static Context getApplication(){
		return application;
	}

	public static int getMainTid() {
		return mainTid;
	}

	public static Handler getHandler() {
		return handler;
	}

	
	
}
