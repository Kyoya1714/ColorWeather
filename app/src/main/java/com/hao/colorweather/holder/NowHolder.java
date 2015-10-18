package com.hao.colorweather.holder;


import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hao.colorweather.BaseApplication;
import com.hao.colorweather.R;
import com.hao.colorweather.domain.Weather;
import com.hao.colorweather.utils.ImageUtils;
import com.hao.colorweather.utils.MyTextUtils;

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
public class NowHolder extends BaseHolder<Weather> {

    private TextView tv_temp;
    private TextView tv_txt;
    private ImageView iv_tip;
    private TextView temp_min;
    private TextView temp_max;
    private TextView time;
    private RelativeLayout rl_aqi;
    private TextView tv_aqi;
    private ImageView iv_aqi;

    @Override
    public View initView() {
        View view =View.inflate(BaseApplication.getApplication(), R.layout.now_item,null);
        tv_temp = (TextView) view.findViewById(R.id.tv_temp);
        temp_min = (TextView) view.findViewById(R.id.temp_min);
        temp_max = (TextView) view.findViewById(R.id.temp_max);
        time = (TextView) view.findViewById(R.id.time);
        tv_txt = (TextView) view.findViewById(R.id.tv_txt);
        iv_tip = (ImageView) view.findViewById(R.id.iv_tip);
        rl_aqi = (RelativeLayout) view.findViewById(R.id.rl_aqi);
        tv_aqi = (TextView) view.findViewById(R.id.tv_aqi);
        iv_aqi = (ImageView) view.findViewById(R.id.iv_aqi);

        return view;
    }

    @Override
    public void refreshView(Weather weather) {
        Weather.Daily daily = weather.result.get(0).daily_forecast.get(0);
        Weather.Now now = weather.result.get(0).now;
        Weather.Aqi aqi = weather.result.get(0).aqi;
        iv_tip.setBackgroundResource(ImageUtils.getImageId(now.cond.code));
        tv_temp.setText(now.tmp + "℃");//实时温度
        temp_min.setText(daily.tmp.min+"℃  /  ");//最低温度
        temp_max.setText(daily.tmp.max + "℃");
        tv_temp.getPaint().setFakeBoldText(true);//字体加粗
        tv_txt.setText(now.cond.txt);//天气状况
        time.setText("今天 "+ MyTextUtils.splitFaBuTime(weather.result.get(0).basic.update.loc) + "发布");
        if(aqi!=null){
            Weather.AqiCity city = aqi.city;
            rl_aqi.setVisibility(View.VISIBLE);
            iv_aqi.setBackgroundResource(ImageUtils.getPM25Image(city.aqi));
            tv_aqi.setText("空气"+city.qlty);
        }else {
            rl_aqi.setVisibility(View.GONE);
        }
    }
}
