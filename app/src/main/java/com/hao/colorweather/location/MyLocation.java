package com.hao.colorweather.location;

import android.os.SystemClock;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.hao.colorweather.BaseApplication;
import com.hao.colorweather.domain.AllCity;
import com.hao.colorweather.utils.SysUtils;
import com.hao.colorweather.utils.ThreadManager;
import com.hao.colorweather.utils.spUtil;

/**
 * ==================================================
 * 版    权：
 * <p/>
 * 作    者：钟旺浩
 * <p/>
 * 版    本：1.0
 * <p/>
 * 日    期：2015/10/17 22:27
 * ===================================================
 */
public class MyLocation{
    public LocationClient mLocationClient = null;
    public BDLocationListener myListener = new MyLocationListener();
    public LocationSuccessListener successListener;
    public MyLocation(){
        SysUtils.showToast("正在获取位置...");
        mLocationClient = new LocationClient(BaseApplication.getApplication());     //声明LocationClient类
        mLocationClient.registerLocationListener(myListener);    //注册监听函数

    }

    public class MyLocationListener implements BDLocationListener {
        @Override
        public void onReceiveLocation(BDLocation bdLocation) {

            mLocationClient.stop();
            String locationCity = bdLocation.getCity();

            if(locationCity==null){
                SysUtils.showToast("定位失败");
                if(successListener!=null){
                    String noteCity = spUtil.getString(BaseApplication.getApplication(), "city", "北京");
                    successListener.locationSuccess(noteCity);
                }
                return;
            }
            locationCity = locationCity.replace("市", "");//去掉市字
            //遍历定位到的城市是否在已经存在的城市中
            for(String useCity:AllCity.citys){
                if(locationCity.equals(useCity)){
                    if(successListener!=null){
                       // SysUtils.showToast("当前城市：" + locationCity);
                        spUtil.setString(BaseApplication.getApplication(),"city",useCity);
                        successListener.locationSuccess(useCity);
                        return;
                    }
                }
            }

            //没有在已存在的城市则获取原来存入的城市
            if(successListener!=null){
                String noteCity = spUtil.getString(BaseApplication.getApplication(), "city", "北京");
                successListener.locationSuccess(noteCity);
            }


        }
    }

    public void initLocation(){
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy
        );//可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        option.setCoorType("bd09ll");//可选，默认gcj02，设置返回的定位结果坐标系
        int span=1000;
        option.setScanSpan(span);//可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
        option.setIsNeedAddress(true);//可选，设置是否需要地址信息，默认不需要
        option.setOpenGps(true);//可选，默认false,设置是否使用gps
        //option.setLocationNotify(true);//可选，默认false，设置是否当gps有效时按照1S1次频率输出GPS结果
        //option.setIsNeedLocationDescribe(true);//可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        //option.setIsNeedLocationPoiList(true);//可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        option.setIgnoreKillProcess(false);//可选，默认false，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认杀死
        option.SetIgnoreCacheException(false);//可选，默认false，设置是否收集CRASH信息，默认收集
        option.setEnableSimulateGps(false);//可选，默认false，设置是否需要过滤gps仿真结果，默认需要
        mLocationClient.setLocOption(option);
        mLocationClient.start();
    }



    /**
     *获取成功的接口
     */
    public interface LocationSuccessListener{
        public void locationSuccess(String city);
    }

    /**
     * 设置监听
     * @param listener
     */
    public void setSuccessListener(LocationSuccessListener listener){
        this.successListener =listener;
    }
}
