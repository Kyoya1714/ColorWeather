package com.hao.colorweather.holder;

import android.view.View;

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
public abstract class BaseHolder<T> {
	private T data;
	private View convertView;
	public BaseHolder() {
		convertView = initView();
		convertView.setTag(this);
	}
	public void setData(T data){
		this.data = data;
		refreshView(this.data);
	}
	/**
	 * 当holder显示的时候显示什么样子
	 * @return
	 */
	public abstract View initView();
	/**
	 * 当setData方法调用的时候会调用该方法，根据数据显示界面
	 * @param data
	 */
	public abstract void refreshView(T data);

	/**
	 * 当holder中的view对象显示到界面上的时候调用
	 * @return
	 */
	public View getConvertView() {
		return convertView;
	}
}
