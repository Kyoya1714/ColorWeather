package com.hao.colorweather.holder;


import android.graphics.Typeface;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.hao.colorweather.BaseApplication;
import com.hao.colorweather.R;
import com.hao.colorweather.domain.Weather;
import com.hao.colorweather.utils.MyTextUtils;

import java.util.ArrayList;
import java.util.Date;
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
public class HourHolder extends BaseHolder<Weather>{
    private final String TAG="HourHolder";

    private TableRow tr;
    private TableLayout tb;
    private TextView week;


    @Override
    public View initView() {
        View v = View.inflate(BaseApplication.getApplication(), R.layout.hour_item,null);
        tr = (TableRow) v.findViewById(R.id.tr);
        tb = (TableLayout) v.findViewById(R.id.tb_hour);
        week = (TextView) v.findViewById(R.id.week);
        return v;
    }

    @Override
    public void refreshView(Weather data) {
        week.setText(MyTextUtils.getWeekOfDate(new Date(),0));//设置星期
        ArrayList<Weather.Hourly> hourly_forecast = data.result.get(0).hourly_forecast;
        tr.removeAllViews();
        //int quantity = hourly_forecast.size() > 4 ? 4 : hourly_forecast.size();
        for (int i=0;i<hourly_forecast.size();i++){
                View tr_view = View.inflate(BaseApplication.getApplication(), R.layout.tr, null);
              //  LinearLayout ll = (LinearLayout) tr_view.findViewById(R.id.ll);
                TextView tv_time = (TextView) tr_view.findViewById(R.id.tv_time);
                TextView tv_temp = (TextView) tr_view.findViewById(R.id.tv_temp);
                TextView tv_hum = (TextView) tr_view.findViewById(R.id.tv_hum);
                TextView tv_pop = (TextView) tr_view.findViewById(R.id.tv_pop);
                tv_time.setText(" "+MyTextUtils.splitTime(hourly_forecast.get(i).date)+"时");//时间
                tv_temp.setText(" "+hourly_forecast.get(i).tmp+"℃");//温度
                tv_hum.setText(hourly_forecast.get(i).hum+"%");//相对湿度
                tv_pop.setText(hourly_forecast.get(i).pop + "%");//降水率

                tv_temp.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));//加粗
                tv_hum.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));//加粗
                tv_pop.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));//加粗
                tr.addView(tr_view);
            }

        }

    }

