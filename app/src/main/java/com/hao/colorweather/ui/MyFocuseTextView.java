package com.hao.colorweather.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewDebug.ExportedProperty;
import android.widget.TextView;
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
public class MyFocuseTextView extends TextView {

	public MyFocuseTextView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	public MyFocuseTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public MyFocuseTextView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	@ExportedProperty(category = "focus")
	public boolean isFocused() {
		// TODO Auto-generated method stub
		return true;
	}
	
}
