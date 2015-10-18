package com.hao.colorweather.domain;

import android.text.style.UpdateAppearance;

import java.util.ArrayList;

/**
 * ==================================================
 * 版    权：
 * <p/>
 * 作    者：钟旺浩
 * <p/>
 * 版    本：1.0
 * <p/>
 * 日    期：2015/9/28 15:09
 * ===================================================
 */
public class Weather {
    public ArrayList<Result> result;

    /**
     *信息的全部内容
     */
    public class Result{
        public Aqi aqi;
        public CityBasic basic;
        public Now now;
        public ArrayList<Daily> daily_forecast;
        public ArrayList<Hourly> hourly_forecast;
        public String status;
        public Suggestion suggestion;
    }

    /**
     * 城市基本信息
     */
    public class CityBasic{
        public String city;//城市名称
        public String cnty;//城市国家
        public String id;//城市id
        /**
         * 城市纬度
         */
        public String lat;
        /**
         * 城市经度
         */
        public String lon;
        public Update update;


    }
    public class Update{
        public String loc;//当地时间
        public String utc;//更新时间
    }

//======================================================
    /**
     * 空气质量
     */
    public class Aqi{
        public AqiCity city;

    }

    /**
     * 城市空气质量
     */
    public class AqiCity{
        public String aqi;
        public String co;
        public String no2;
        public String o3;
        public String pm10;
        public String pm25;
        public String qlty;
        public String so2;
    }

//==============================================
    /**
     * 实况天气
     */
    public class Now{
        public Cond cond;//天气状况
        public String fl;//体感温度
        public String hum;//相对湿度（%）
        public String pcpm;//降水量（mm）
        public String pres;//气压
        public String tmp;//温度
        public String vis;//能见度（km）
        public Wind wind;//风力风向
    }

    /**
     * 天气状况
     */
    public class Cond{
        /**
         * 天气状况图片代码
         */
        public String code;
        /**
         * 天气状况描述
         */
        public String txt;
    }

    /**
     * 风力风向
     */
    public class Wind{
        public String deg;//风向（360度）
        public String dir;//风向
        public String sc;//风力
        public String spd;//风速（kmph）
    }

    //================================================
    /**
     * 未来7天天气
     */
    public class Daily{
        public Astro astro;//未来日出日落
        public DailyCond cond;//未来7天天气状况
        public String date;//时间
        public String hum;//相对湿度（%）
        public String pcpn;//降水量（mm）
        public String pop;//降水概率
        public String pres;//气压
        public String vis;//能见度
        public  Temp tmp;//温度

    }

    /**
     * 未来日出日落
     */
    public class Astro{
        public String sr;//日出时间
        public String ss;//日落时间
    }

    /**
     * 未来7天天气状况未来7天天气状况
     */
    public class DailyCond{
        public String code_d;//白天天气状况图片
        public String code_n;//晚上天气状况图片
        public String txt_d;//白天天气描述
        public String txt_n;//晚上天气描述
    }
    public class Temp{
        public String max;//最高温度
        public String min;//最低温度
    }
//=============================================

    /**
     * 未来3小时天气状况
     */
    public class Hourly{
        public String date;//2015-07-02 01:00", //时间
        public String hum;//相对湿度（%）
        public String pop;//降水概率
        public String pres; //气压
        public String tmp;//温度
        public HourWind wind;
    }

    public class HourWind{
        public String deg;//风向（360度）
        public String dir; //风向
        public String sc; //风力
        public String spd;//风速（kmph）
    }
//=====================================

    /**
     * 指数
     */
    public class Suggestion{
        public Uv uv;
        public Comf comf;
        public Drsg drsg;
        public Flu flu;
        public Sport sport;
        public Trav trav;
    }

    /**
     * 紫外线
     */
    public class Uv{
        public String brf;//紫外线强度
        public String txt;//描述
    }

    /**
     * 舒适度指数
     */
    public class Comf{
        public String brf;
        public String txt;
    }

    /**
     * 穿衣
     */
    public class Drsg{
        public String brf;
        public String txt;
    }

    /**
     * 感冒
     */
    public class Flu{
        public String brf;
        public String txt;
    }

    /**
     * 运动
     */
    public class Sport{
        public String brf;
        public String txt;
    }

    /**
     * 旅游
     */
    public class Trav{
        public String brf;
        public String txt;
    }
}
