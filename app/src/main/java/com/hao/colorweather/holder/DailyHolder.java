package com.hao.colorweather.holder;


import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.hao.colorweather.BaseApplication;
import com.hao.colorweather.R;
import com.hao.colorweather.domain.Weather;
import com.hao.colorweather.utils.ImageUtils;
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
public class DailyHolder extends BaseHolder<Weather>{
    private final String TAG="DailyHolder";
    private ListView lv;
    private ArrayList<Weather.Daily> dailyList;


    @Override
    public View initView() {
        View v = View.inflate(BaseApplication.getApplication(), R.layout.daily_item,null);
        lv = (ListView) v.findViewById(R.id.lv);
        return v;
    }

    @Override
    public void refreshView(Weather data) {
        dailyList = data.result.get(0).daily_forecast;

        lv.setAdapter(new DaliyAdapter());

    }


    class DaliyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return dailyList.size()-1;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup viewGroup) {
            View view = null;
            Holder holder;
            if(convertView==null){
                view = View.inflate(BaseApplication.getApplication(),R.layout.daily_lv,null);
                holder = new Holder();
                holder.week = (TextView) view.findViewById(R.id.week);
                holder.date = (TextView) view.findViewById(R.id.date);
                holder.icon = (ImageView) view.findViewById(R.id.icon);
                holder.temp = (TextView) view.findViewById(R.id.temp);
                view.setTag(holder);
            }else {
                view = convertView;
                holder = (Holder) view.getTag();
            }
            Weather.Daily daily = dailyList.get(position + 1);
            holder.week.setText(MyTextUtils.getWeekOfDate(new Date(), position + 1));//设置星期
            holder.date.setText(MyTextUtils.getNextDate(position+1));
            holder.icon.setImageResource(ImageUtils.getImageId(daily.cond.code_d));//设置天气图标
            holder.temp.setText(daily.tmp.min+"~"+daily.tmp.max+"℃");//设置温度

            return view;
        }
    }



    static class Holder{
        TextView week;
        TextView date;
        ImageView icon;
        TextView temp;

    }
    }

