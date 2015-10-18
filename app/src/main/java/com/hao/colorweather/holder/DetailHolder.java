package com.hao.colorweather.holder;


import android.view.View;
import android.widget.TextView;

import com.hao.colorweather.BaseApplication;
import com.hao.colorweather.R;
import com.hao.colorweather.domain.Weather;

import java.util.ArrayList;

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
public class DetailHolder extends BaseHolder<Weather>{

    private TextView tv_pop;
    private TextView tv_wind;
    private TextView tv_shidu;
    private TextView tv_ziwaixian;
    private TextView tv_richu;
    private TextView tv_riluo;

    @Override
    public View initView() {
        View v = View.inflate(BaseApplication.getApplication(), R.layout.detail_item,null);
        tv_pop = (TextView) v.findViewById(R.id.tv_pop);
        tv_wind = (TextView) v.findViewById(R.id.tv_wind);
        tv_shidu = (TextView) v.findViewById(R.id.tv_shidu);
        tv_ziwaixian = (TextView) v.findViewById(R.id.tv_ziwaixian);
        tv_richu = (TextView) v.findViewById(R.id.tv_richu);
        tv_riluo = (TextView) v.findViewById(R.id.tv_riluo);
        return v;
    }

    @Override
    public void refreshView(Weather data) {
        Weather.Now now = data.result.get(0).now;
        Weather.Suggestion suggestion = data.result.get(0).suggestion;
        Weather.Daily daily = data.result.get(0).daily_forecast.get(0);//当天情况
        ArrayList<Weather.Hourly> hourly_forecast = data.result.get(0).hourly_forecast;//当天小时情况
        if(hourly_forecast!=null&&hourly_forecast.size()>0){
            tv_pop.setText("降雨率 "+hourly_forecast.get(0).pop+"%");//设置当天降雨概率
        }else{
            tv_pop.setText("降雨率 "+daily.pop+"%");//设置当天降雨概率
        }
        tv_wind.setText(now.wind.dir+" "+now.wind.sc+"级");//当天风力
        tv_shidu.setText("湿度 "+now.hum+"%");//当天湿度
        if (suggestion!=null) {
            tv_ziwaixian.setText("紫外线 "+suggestion.uv.brf);
        }else{
            tv_ziwaixian.setText("紫外线 "+"弱");
        }
        tv_richu.setText("日出 "+daily.astro.sr);
        tv_riluo.setText("日落 "+daily.astro.ss);

    }
}
