package com.hao.colorweather.utils;

import android.content.Context;
import android.view.View;
import android.view.animation.RotateAnimation;

import com.hao.colorweather.BaseApplication;
import com.hao.colorweather.MainActivity;
import com.hao.colorweather.R;

import java.util.Random;

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
public class ImageUtils {


    private static int default_index=8;
    public static int getImageId(String code) {
        if (code.equals("100")) {//晴
            return R.mipmap.c100;
        } else if (code.equals("101")) {//多云
            return R.mipmap.c101;
        } else if (code.equals("102")) {//少云
            return R.mipmap.c102;
        } else if (code.equals("103")) {//晴间多云
            return R.mipmap.c103;
        } else if (code.equals("104")) {//阴
            return R.mipmap.c104;
        } else if (code.equals("200")) {//有风
            return R.mipmap.c200;
        } else if (code.equals("201")) {//平静
            return R.mipmap.c201;
        } else if (code.equals("202")) {//微风
            return R.mipmap.c202;
        } else if (code.equals("203")) {//和风
            return R.mipmap.c202;
        } else if (code.equals("204")) {//清风
            return R.mipmap.c202;
        } else if (code.equals("205")) {//强风/劲风
            return R.mipmap.c205;
        } else if (code.equals("206")) {//疾风
            return R.mipmap.c205;
        } else if (code.equals("207")) {//大风
            return R.mipmap.c205;
        } else if (code.equals("208")||code.equals("209")||code.equals("210")||code.equals("211")||code.equals("212")||code.equals("213")) {
            return R.mipmap.c208;//烈风，风暴，狂爆风，飓风，龙卷风，热带风暴
        } else if (code.equals("300")) {//阵雨
            return R.mipmap.c300;
        }else if (code.equals("301")) {//强阵雨
            return R.mipmap.c301;
        } else if (code.equals("302")) {//雷阵雨
            return R.mipmap.c302;
        }else if (code.equals("303")) {//强雷阵雨
            return R.mipmap.c303;
        } else if (code.equals("304")) {//雷阵雨伴有冰雹
            return R.mipmap.c304;
        } else if (code.equals("305")) {//小雨
            return R.mipmap.c305;
        } else if (code.equals("306")) {//中雨
            return R.mipmap.c306;
        } else if (code.equals("307")) {//大雨
            return R.mipmap.c307;
        } else if (code.equals("308")) {//极端降雨
            return R.mipmap.c308;
        } else if (code.equals("309")) {//毛毛雨/细雨
            return R.mipmap.c309;
        } else if (code.equals("310")) {//暴雨
            return R.mipmap.c310;
        } else if (code.equals("311")) {//大暴雨
            return R.mipmap.c311;
        } else if (code.equals("312")) {//特大暴雨
            return R.mipmap.c312;
        } else if (code.equals("313")) {//冻雨
            return R.mipmap.c313;
        } else if (code.equals("400")) {//小雪
            return R.mipmap.c400;
        } else if (code.equals("401")) {//中雪
            return R.mipmap.c401;
        } else if (code.equals("402")) {//大雪
            return R.mipmap.c402;
        } else if (code.equals("403")) {//暴雪
            return R.mipmap.c403;
        } else if (code.equals("404")) {//雨夹雪
            return R.mipmap.c404;
        } else if (code.equals("405")) {//雨雪天气
            return R.mipmap.c405;
        } else if (code.equals("406")) {//阵雨夹雪
            return R.mipmap.c406;
        } else if (code.equals("407")) {//阵雪
            return R.mipmap.c407;
        } else if (code.equals("500")) {//薄雾
            return R.mipmap.c500;
        } else if (code.equals("501")) {//雾
            return R.mipmap.c501;
        }else if (code.equals("502")) {//霾
            return R.mipmap.c502;
        } else if (code.equals("503")) {//扬沙
            return R.mipmap.c500;
        } else if (code.equals("504")) {//浮尘
            return R.mipmap.c504;
        } else if (code.equals("507")) {//沙尘暴
            return R.mipmap.c507;
        } else if (code.equals("508")) {//强沙尘暴
            return R.mipmap.c508;
        } else if (code.equals("900")) {//热
            return R.mipmap.c900;
        } else if (code.equals("901")) {//冷
            return R.mipmap.c901;
        } else {
            return R.mipmap.c999;//未知
        }


    }


    private static int[] bgColors ={
            R.color.bg_red,R.color.bg_pink,R.color.bg_purple,R.color.bg_deep_purple,
            R.color.bg_indigo,R.color.bg_blue,R.color.bg_light_blue,R.color.bg_cyan,
            R.color.bg_teal,R.color.bg_green,R.color.bg_light_green,R.color.bg_lime,
            R.color.bg_amber,R.color.bg_orange,R.color.bg_deep_orange,
            R.color.bg_brown,R.color.bg_grey,R.color.bg_blue_grey};

    /**
     * 根据主题设置背景颜色
     * @param fl
     */
    public static void setDrawerBg(View fl) {
        int colorMode = spUtil.getInt(BaseApplication.getApplication(), "colorMode", 0);
        if (colorMode == 0) {
            int themeid = spUtil.getInt(BaseApplication.getApplication(), "theme", -1);
            if (themeid != -1) {
                fl.setBackgroundResource(bgColors[themeid]);
            } else {
                fl.setBackgroundResource(bgColors[default_index]);
            }
        }else if(colorMode==1){
            //每日颜色模式
            int dayTheme = spUtil.getInt(BaseApplication.getApplication(), "dayTheme", default_index);
            fl.setBackgroundResource(bgColors[dayTheme]);
        }else if(colorMode==2){
            //每次模式
            fl.setBackgroundResource(bgColors[random]);
        }
    }


    
    private static int[] dialogColors ={
            R.color.action_red,R.color.action_pink,R.color.action_purple,R.color.action_deep_purple,
            R.color.action_indigo,R.color.action_blue,R.color.action_light_blue,R.color.action_cyan,
            R.color.action_teal,R.color.action_green,R.color.action_light_green,R.color.action_lime,
            R.color.action_amber,R.color.action_orange,R.color.action_deep_orange,
            R.color.action_brown,R.color.action_grey,R.color.action_blue_grey};
    /**
     * 根据主题设置dialog颜色
     * @param bar
     * @param btn1
     * @param btn2
     */
    public static void setDialogColor(View bar,View btn1,View btn2){
        int colorMode = spUtil.getInt(BaseApplication.getApplication(), "colorMode", 0);
        if(colorMode==0){
            //手动模式
            int themeid = spUtil.getInt(BaseApplication.getApplication(), "theme", -1);
            if ( themeid != -1) {
                bar.setBackgroundResource(dialogColors[themeid]);
                btn1.setBackgroundResource(dialogColors[themeid]);
                btn2.setBackgroundResource(dialogColors[themeid]);
            }else{
                bar.setBackgroundResource(dialogColors[default_index]);
                btn1.setBackgroundResource(dialogColors[default_index]);
                btn2.setBackgroundResource(dialogColors[default_index]);
            }
        }else if(colorMode==1){
            //每日模式
            int dayTheme = spUtil.getInt(BaseApplication.getApplication(), "dayTheme", default_index);
            bar.setBackgroundResource(dialogColors[dayTheme]);
            btn1.setBackgroundResource(dialogColors[dayTheme]);
            btn2.setBackgroundResource(dialogColors[dayTheme]);
        }else if(colorMode==2){
            //每次模式
            bar.setBackgroundResource(dialogColors[random]);
            btn1.setBackgroundResource(dialogColors[random]);
            btn2.setBackgroundResource(dialogColors[random]);
        }

    }

    /**
     * 根据主题返回actionbar颜色
     * @return
     */
    public static int getBarColor() {
        int colorMode = spUtil.getInt(BaseApplication.getApplication(), "colorMode", 0);
        if (colorMode == 0) {
            //手动模式
            int themeid = spUtil.getInt(BaseApplication.getApplication(), "theme", -1);
            if (themeid != -1) {
                return dialogColors[themeid];
            } else {
                return dialogColors[default_index];
            }
        }else if(colorMode==1){
            //每日模式
            int dayTheme = spUtil.getInt(BaseApplication.getApplication(), "dayTheme", default_index);
            return dialogColors[dayTheme];
        }else{
            //每次模式
            return dialogColors[random];
        }

    }


    private static int[] themes ={
            R.style.RedTheme,R.style.PinkTheme,R.style.PurpleTheme,R.style.DeepPurpleTheme,
            R.style.IndigoTheme,R.style.BlueTheme,R.style.LightBlueTheme,R.style.CyanTheme,
            R.style.TealTheme,R.style.GreenTheme,R.style.LightGreenTheme,R.style.LimeTheme,
            R.style.AmberTheme,R.style.OrangeTheme,R.style.DeepOrangeTheme,
            R.style.BrownTheme,R.style.GreyTheme,R.style.BlueGreyTheme};

    /**
     * 手动模式设置用户的主题
     */
    public static void setUserTheme(Context context){
        int themeid = spUtil.getInt(context, "theme", -1);
        if ( themeid != -1) {
            context.setTheme(themes[themeid]);
        }else{
            context.setTheme(themes[default_index]);

        }
    }


    public static int random;
    /**
     * 每次随机模式设置用户主题
     * @param context
     */
    public static void setRandomThemeOfMain(Context context){
        Random ran = new Random();
        random = ran.nextInt(themes.length);
        context.setTheme(themes[random]);
    }


    public static void setRandomThemeOfOther(Context context){
        context.setTheme(themes[random]);
    }

    /**
     * 每日随机模式设置主题
     * @param context
     */
    public static void setEveryDayTheme(Context context){
        String noteDate = spUtil.getString(context, "date", "");
        String nowDate = MyTextUtils.getNowDate();
        if(!nowDate.equals(noteDate)){//是新的一天
            Random random = new Random();
            int ran = random.nextInt(themes.length);
            context.setTheme(themes[ran]);
            spUtil.setInt(context, "dayTheme", ran);//记录今天的主题
            spUtil.setString(context,"date",nowDate);//更新记录的时间
        }else{
            //不是新的一天
            int dayTheme = spUtil.getInt(context, "dayTheme", default_index);
            context.setTheme(themes[dayTheme]);
        }
    }



    public static int getPM25Image(String aqi){
        int a = Integer.parseInt(aqi);
        if(a<=50){
            return R.mipmap.aqi_verygood;
        }else if(a<=100){
            return R.mipmap.aqi_good;
        }else{
            return R.mipmap.aqi_bad;
        }
    }
}


