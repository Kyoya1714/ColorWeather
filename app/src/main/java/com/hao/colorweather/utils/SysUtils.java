package com.hao.colorweather.utils;



import android.content.Context;
import android.widget.Toast;

import com.hao.colorweather.BaseApplication;
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
public class SysUtils {

	private static Toast mToast;

	public static void showToast( String msg) {
		if (mToast == null) {
			mToast = Toast.makeText(BaseApplication.getApplication(), "", Toast.LENGTH_LONG);
		}
		mToast.setText(msg);
		mToast.show();
	}
	
	

	/** 
     * �����ֻ��ķֱ��ʴ� dip �ĵ�λ ת��Ϊ px(����) 
     */  
    public static int dip2px(Context context, float dpValue) {  
        final float scale = context.getResources().getDisplayMetrics().density;  
        return (int) (dpValue * scale + 0.5f);  
    }  
  
    /** 
     * �����ֻ��ķֱ��ʴ� px(����) �ĵ�λ ת��Ϊ dp 
     */  
    public static int px2dip(Context context, float pxValue) {  
        final float scale = context.getResources().getDisplayMetrics().density;  
        return (int) (pxValue / scale + 0.5f);  
    }  
    
    /**
	 * ��runnable�����ύ�����߳�����
	 * @param runnable
	 */
	public static void runOnUiThread(Runnable runnable){
		//�����߳�����
		if(android.os.Process.myTid()== BaseApplication.getMainTid()){//�жϵ�ǰ�߳��Ƿ������߳�id
			runnable.run();
		}else{
			//�������,��ȡ���߳�handler
			//handler.post(runnable)
			BaseApplication.getHandler().post(runnable);
		}
	}
	

}
