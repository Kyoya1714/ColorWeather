package com.hao.colorweather;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import com.google.gson.Gson;
import com.hao.colorweather.domain.Weather;
import com.hao.colorweather.fragment.DrawerFragment;
import com.hao.colorweather.global.Global;
import com.hao.colorweather.holder.DailyHolder;
import com.hao.colorweather.holder.DetailHolder;
import com.hao.colorweather.holder.HourHolder;
import com.hao.colorweather.holder.NowHolder;
import com.hao.colorweather.location.MyLocation;
import com.hao.colorweather.utils.ImageUtils;
import com.hao.colorweather.utils.ThreadManager;
import com.hao.colorweather.utils.SysUtils;
import com.hao.colorweather.utils.spUtil;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
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
public class MainActivity extends AppCompatActivity implements  SwipeRefreshLayout.OnRefreshListener,MyLocation.LocationSuccessListener{

    Toolbar mToolbar;
    ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private String TAG = "MAINACITVITY";
    private ActionBar actionBar;
    private Weather weather;
    private SwipeRefreshLayout refresh;
    private FrameLayout draw_view;
    private DrawerLayout drawer;
    private FrameLayout fl_now;
    private NowHolder nowHolder;
    private DetailHolder detailHolder;
    private FrameLayout fl_detail;
    private HourHolder hourHolder;
    private FrameLayout fl_hour;
    private FrameLayout fl_daily;
    private DailyHolder dailyHolder;
    private DrawerFragment drawerFragment;
    private RelativeLayout welcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        int colorMode=spUtil.getInt(this,"colorMode",0);
        if(colorMode==0){
            //手动模式
            ImageUtils.setUserTheme(this);
        }else if(colorMode==1){
            //每日随机
            ImageUtils.setEveryDayTheme(this);
        }else if(colorMode ==2){
            //每次随机
            ImageUtils.setRandomThemeOfMain(this);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initDrawer();
        autoRefresh();

        Boolean isOneStart = spUtil.getBoolean(BaseApplication.getApplication(), "isOneStart", true);
        if(isOneStart){
            //第一次启动
            MyLocation location = new MyLocation();//获取位置
            location.setSuccessListener(this);//设置成功获取城市的监听
            location.initLocation();//开始定位
            spUtil.setBoolean(BaseApplication.getApplication(),"isOneStart",false);
        }else{
            String city = spUtil.getString(BaseApplication.getApplication(), "city", "北京");
            getDataFromServer(city);
        }


       ThreadManager.getInstance().createLongPool().excute(new Runnable() {
           @Override
           public void run() {
               SystemClock.sleep(1800);
               runOnUiThread(new Runnable() {
                   @Override
                   public void run() {
                       welcome.setVisibility(View.GONE);
                   }
               });
           }
       });


    }

    /**
     * 初始化控件
     */
    private void initView() {
        welcome = (RelativeLayout) findViewById(R.id.welcome);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        refresh = (SwipeRefreshLayout) findViewById(R.id.refresh);
        fl_now = (FrameLayout) findViewById(R.id.fl_now);
        fl_detail = (FrameLayout) findViewById(R.id.fl_detail);
        fl_hour = (FrameLayout) findViewById(R.id.fl_hour);
        fl_daily = (FrameLayout) findViewById(R.id.fl_daily);
        drawer = (DrawerLayout) findViewById(R.id.drawer);
        refresh.setOnRefreshListener(this);
        refresh.setColorSchemeResources(android.R.color.holo_blue_bright, android.R.color.holo_green_light,
                android.R.color.holo_orange_light, android.R.color.holo_red_light);
        //refresh.setColorScheme(getResources().getColor(android.R.color.holo_blue_bright),getResources().getColor(android.R.color.holo_green_light),getResources().getColor(android.R.color.holo_orange_light),getResources().getColor(android.R.color.holo_red_light));
        refresh.setDistanceToTriggerSync(80);// 设置手指在屏幕下拉多少距离会触发下拉刷新
        refresh.setProgressBackgroundColor(R.color.refresh_bg); // 设定下拉圆圈的背景
        refresh.setSize(SwipeRefreshLayout.DEFAULT); // 设置圆圈的大小


        //mToolbar.setLogo(R.mipmap.dingwei);
        String   city = spUtil.getString(BaseApplication.getApplication(),"city","");
        mToolbar.setTitle(city);// 标题的文字需在setSupportActionBar之前，不然会无效
        setSupportActionBar(mToolbar);
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        // actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);//设置actionbar模式有标签
        /* findView */
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        //1:上下文  2：抽屉   3：toolbar 4:打开描述   5：关闭描述
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        mDrawerLayout.setDrawerListener(mDrawerToggle);//设置抽屉的监听
        mDrawerToggle.syncState();//让toolbar和抽屉建立关系

    }






    /**
     * 从服务器获取数据
     */
    public void getDataFromServer(final String city) {

        final String httpUrl = Global.HTTPURL+city+Global.HTTPARGS;
        ThreadManager.getInstance().createLongPool().excute(new Runnable() {
            @Override
            public void run() {
                autoRefresh();
                SystemClock.sleep(500);
                HttpUtils httpUtils = new HttpUtils();
                httpUtils.send(HttpMethod.GET, httpUrl, new RequestCallBack<String>() {
                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        String result = responseInfo.result;
                        paserData(result,city);
                        refresh.setRefreshing(false);
                }
                @Override
                public void onFailure (HttpException e, String s){

                    SysUtils.showToast("网络异常");
                    refresh.setRefreshing(false);
                }
            }

            );
        }


    });
    }

    /**
     * 解析json数据
     * @param result
     */
    private void paserData(String result,String city) {
        Log.i(TAG,result);
        //判断字符串是否为空
        if(result.contains("unknown city")){
            SysUtils.showToast("输入城市有误");
            return;
        }
        if(!result.contains("ok")){
            SysUtils.showToast("更新失败");
            return;
        }
        result = result.replace("HeWeather data service 3.0", "result");//替换字符串
        Gson gson = new Gson();
        weather = gson.fromJson(result, Weather.class);
        spUtil.setString(BaseApplication.getApplication(), "city", city);
        mToolbar.setTitle(weather.result.get(0).basic.city);
        createSuccessView();
        setDrawerData();
        SysUtils.showToast("更新成功");
    }

    /**
     * 创建界面
     */
    private void createSuccessView(){
        //头部界面
        if(nowHolder==null){
            nowHolder = new NowHolder();
        }
        nowHolder.setData(weather);
        fl_now.removeAllViews();
        fl_now.addView(nowHolder.getConvertView());
        //第二个界面
        if(detailHolder==null){
            detailHolder = new DetailHolder();
        }
        detailHolder.setData(weather);
        fl_detail.removeAllViews();
        fl_detail.addView(detailHolder.getConvertView());

        //第三个界面  未来3小时天气
        if(weather.result.get(0).hourly_forecast.size()>0) {
            if (hourHolder == null) {
                hourHolder = new HourHolder();
            }
            hourHolder.setData(weather);
            fl_hour.removeAllViews();
            fl_hour.addView(hourHolder.getConvertView());
        }
        //第四个界面  未来七天天气
        if(weather.result.get(0).daily_forecast.size()>0){
            if(dailyHolder==null) {
                dailyHolder = new DailyHolder();
            }
            dailyHolder.setData(weather);
            fl_daily.removeAllViews();
            fl_daily.addView(dailyHolder.getConvertView());
        }
    }


    /**
     * 设置侧边栏数据
     */
    private void setDrawerData(){
        drawerFragment.setData(weather);
    }
    /**
     * 初始化抽屉
     */
    protected void initDrawer() {
        draw_view = (FrameLayout) findViewById(R.id.drawer_view);
        drawerFragment = new DrawerFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();//开启事务
        transaction.replace(R.id.drawer_view, drawerFragment);//把内容显示到帧布局
        transaction.commit();//提交事务
        ImageUtils.setDrawerBg(draw_view);//根据主题更改背景色
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
      //  Log.i(TAG, "onCreateOptionsMenu创建了");
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /**
     * 处理actionbar条目的点击事件
     *
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
       // Log.i(TAG, "条目被被点击了");
        switch (item.getItemId()) {
            case R.id.action_red:
                changeToTheme(MainActivity.this, 0);
                break;
            case R.id.action_pink:
                changeToTheme(MainActivity.this, 1);
                break;
            case R.id.action_purple:
                changeToTheme(MainActivity.this,2);
                break;
            case R.id.action_deep_purple:
                changeToTheme(MainActivity.this,3);
                break;
            case R.id.action_indigo_blue:
                changeToTheme(MainActivity.this,4);
                break;
            case R.id.action_blue:
                changeToTheme(MainActivity.this,5);
                break;
            case R.id.action_light_blue:
                changeToTheme(MainActivity.this,6);
                break;
            case R.id.action_cyan:
                changeToTheme(MainActivity.this,7);
                break;
            case R.id.action_teal:
                changeToTheme(MainActivity.this,8);
                break;
            case R.id.action_green:
                changeToTheme(MainActivity.this,9);
                break;
            case R.id.action_light_green:
                changeToTheme(MainActivity.this,10);
                break;
            case R.id.action_lime:
                changeToTheme(MainActivity.this,11);
                break;
            case R.id.action_amber:
                changeToTheme(MainActivity.this,12);
                break;
            case R.id.action_orange:
                changeToTheme(MainActivity.this,13);
                break;
            case R.id.action_deep_orange:
                changeToTheme(MainActivity.this,14);
                break;
            case R.id.action_brown:
                changeToTheme(MainActivity.this,15);
                break;
            case R.id.action_grey:
                changeToTheme(MainActivity.this,16);
                break;
            case R.id.action_bule_grey:
                changeToTheme(MainActivity.this,17);
                break;
            default:
                break;
        }
        return true;

    }

    /**
     * 改变主题颜色
     * @param activity ：上下文
     * @param theme
     */
    public static void changeToTheme(Activity activity, int theme) {
        spUtil.setInt(activity, "theme", theme);
        Intent intent = activity.getIntent();
        activity.overridePendingTransition(0, 0);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        activity.finish();
        //activity.clear();
        activity.overridePendingTransition(0, 0);
        activity.startActivity(intent);
        //activity.finish();
        //activity.startActivity(new Intent(activity, activity.getClass()));
    }

    /**
     * 关闭抽屉
     */
    public void closeDrawer() {
        drawer.closeDrawer(Gravity.LEFT);
    }
    @Override
    public void onRefresh() {
         getDataFromServer(spUtil.getString(BaseApplication.getApplication(), "city", "北京"));
    }

    /**
     * 自动显示下拉
     */
    public void autoRefresh(){
        refresh.post(new Runnable() {
            @Override
            public void run() {
                refresh.setRefreshing(true);
            }
        });
    }

    /**
     * 成功获取位置
     * @param city
     */
    @Override
    public void locationSuccess(String city) {
        getDataFromServer(city);
    }
}
