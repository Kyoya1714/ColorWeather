package com.hao.colorweather;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hao.colorweather.R;
import com.hao.colorweather.utils.ImageUtils;
import com.hao.colorweather.utils.spUtil;

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
public class ProductActivity extends AppCompatActivity implements View.OnClickListener
{

    private TextView tv_version;
    private RelativeLayout rl_title;
    private int themeid;
    private ImageView iv_back;

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
            ImageUtils.setRandomThemeOfOther(this);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        initView();
        initListener();
        initData();
    }



    private void initView() {
        rl_title = (RelativeLayout) findViewById(R.id.rl_title);
        rl_title.setBackgroundResource(ImageUtils.getBarColor());
        tv_version = (TextView) findViewById(R.id.version);
        iv_back = (ImageView) findViewById(R.id.iv_back);
    }

    private void initListener() {
        iv_back.setOnClickListener(this);
    }

    private void initData() {
        tv_version.setText("Color天气 V"+getAppVersion() );

    }


    @Override
    public void onBackPressed() {
        finish();
    }

    /**
     * 获取应用版本号
     * @return
     */
    private String  getAppVersion(){
        try {
            PackageManager packageManager = getPackageManager();   // getPackageName()是你当前类的包名，0代表是获取版本信息
            PackageInfo packInfo = null;
            packInfo = packageManager.getPackageInfo(getPackageName(),0);
           return packInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "";
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_back:
                finish();
            break;
        }
    }
}
