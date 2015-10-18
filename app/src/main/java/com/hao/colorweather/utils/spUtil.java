package com.hao.colorweather.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
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
public class spUtil {
	/**
	 * �õ�boolean����ֵ
	 * @param context��������
	 * @param key��������
	 * @param defValue��Ĭ��ֵ
	 * @return
	 */
	public static Boolean getBoolean(Context context,String key,boolean defValue){
		SharedPreferences sp = context.getSharedPreferences("config", context.MODE_PRIVATE);
		return sp.getBoolean(key, defValue);
	}
	/**
	 * ����Boolean��������
	 * @param context��������
	 * @param key��������
	 * @param value������ֵ
	 */
	public static void setBoolean(Context context,String key,boolean value){
		SharedPreferences sp = context.getSharedPreferences("config", context.MODE_PRIVATE);
		Editor editor = sp.edit();
		editor.putBoolean(key, value);
		editor.commit();
	}
	/**
	 * �õ�String����ֵ
	 * @param context��������
	 * @param key��������
	 * @param defValue��Ĭ��ֵ
	 * @return
	 */
	public static String getString(Context context,String key,String defValue){
		SharedPreferences sp = context.getSharedPreferences("config", context.MODE_PRIVATE);
		return sp.getString(key, defValue);
	}
	/**
	 * ����Stringֵ
	 * @param context��������
	 * @param key��������
	 * @param value������ֵ
	 */
	public static void setString(Context context,String key,String value){
		SharedPreferences sp = context.getSharedPreferences("config", context.MODE_PRIVATE);
		Editor editor = sp.edit();
		editor.putString(key, value);
		editor.commit();
	}
	/**
	 * �õ�int����ֵ
	 * @param context��������
	 * @param key��������
	 * @param defValue��Ĭ��ֵ
	 * @return
	 */
	public static int getInt(Context context,String key,int defValue){
		SharedPreferences sp = context.getSharedPreferences("config", context.MODE_PRIVATE);
		return sp.getInt(key, defValue);
	}
	/**
	 * ����intֵ
	 * @param context��������
	 * @param key��������
	 * @param value������ֵ
	 */
	public static void setInt(Context context,String key,int value){
		SharedPreferences sp = context.getSharedPreferences("config", context.MODE_PRIVATE);
		Editor editor = sp.edit();
		editor.putInt(key, value);
		editor.commit();
	}
}
