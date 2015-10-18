package com.hao.colorweather.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hao.colorweather.BaseApplication;
import com.hao.colorweather.MainActivity;
import com.hao.colorweather.R;
import com.hao.colorweather.domain.Weather;
import com.hao.colorweather.global.Global;
import com.hao.colorweather.utils.ImageUtils;
import com.hao.colorweather.utils.MyTextUtils;
import com.hao.colorweather.ProductActivity;
import com.hao.colorweather.utils.SysUtils;
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
public class DrawerFragment extends Fragment implements View.OnClickListener{


    private RelativeLayout switchCity;
    private View view;
    private EditText et_city;
    private Button ok;
    private Button cancel;
    private AlertDialog dialog;
    private MainActivity mActivity;
    private TextView bar;
    private TextView location;
    private TextView city;
    private RelativeLayout share;
    private RelativeLayout product;
    private RelativeLayout beauty;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = View.inflate(BaseApplication.getApplication(), R.layout.drawer_item, null);

        initView();
        initListener();
        return view;
    }


    private void initView() {
        location = (TextView) view.findViewById(R.id.location);
        city = (TextView) view.findViewById(R.id.city);
        switchCity = (RelativeLayout) view.findViewById(R.id.switchCity);
        share = (RelativeLayout) view.findViewById(R.id.share);
        product = (RelativeLayout) view.findViewById(R.id.product);
        beauty = (RelativeLayout) view.findViewById(R.id.beauty);
        city.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));//加粗
        mActivity = (MainActivity) getActivity();

    }
    private void initListener() {
        switchCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCityDialog();
            }
        });
        share.setOnClickListener(this);
        product.setOnClickListener(this);
        beauty.setOnClickListener(this);
    }

    /**
     * 提供给activity设置数据
     * @param weather
     */
    public void setData(Weather weather){
        String lat = MyTextUtils.splitJingWei(weather.result.get(0).basic.lat);//纬度
        String lon = MyTextUtils.splitJingWei(weather.result.get(0).basic.lon);//经度
        location.setText("纬度:" + lat + "   经度:" + lon);
    }

    /**
     * 弹出对话框
     */
    protected void showCityDialog() {
        View view = View.inflate(mActivity, R.layout.dialog_put_city, null);
        et_city = (EditText) view.findViewById(R.id.et_city);
        ok = (Button) view.findViewById(R.id.ok);
        cancel = (Button) view.findViewById(R.id.cancel);
        bar = (TextView) view.findViewById(R.id.bar);
        ImageUtils.setDialogColor(bar, ok, cancel);
        AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();

            }
        });
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String city = et_city.getText().toString().trim();
                if (TextUtils.isEmpty(city)) {
                    SysUtils.showToast("城市不能为空");
                    return;
                }
                dialog.dismiss();
                mActivity.closeDrawer();//关闭抽屉
                mActivity.getDataFromServer(city);//更新数据
            }
        });
        dialog = builder.create();
        dialog.setView(view, 0, 0, 0, 0);
        dialog.show();
    }





    /**
     * 点击事件处理
     * @param view
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.share:
                shareApp();//分享应用
                break;
            case R.id.product:
                startProductActivity();//打开产品信息页面
                break;
            case R.id.beauty:;
                showBeautyDialog();
                break;
        }

    }

    /**
     * 分享应用
     */
    private void shareApp(){
        Intent intent = new Intent(Intent.ACTION_SEND);
        //intent.setAction("android.intent.action.SEND");
        //intent.setType("image/*");
        //intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, "Share");
        intent.putExtra(Intent.EXTRA_TEXT, Global.SHARETXT);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(Intent.createChooser(intent,"Color天气"));
    }

    /**
     * 打开产品信息页面
     */
    private void startProductActivity(){

        Intent intent = new Intent(BaseApplication.getApplication(), ProductActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);

    }

    /**
     * 显示主题模式选择对话框
     */
    private void showBeautyDialog(){

        AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
        builder.setTitle("模式选择");//设置标题
        final String[] items = new String[]{"自选主题","每日随机","每次随机"};
        builder.setSingleChoiceItems(items, spUtil.getInt(BaseApplication.getApplication(),"colorMode",0), new android.content.DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                spUtil.setInt(BaseApplication.getApplication(), "colorMode", which);
                if (which==1) {
                    spUtil.setString(BaseApplication.getApplication(), "date", MyTextUtils.getNowDate());
                }
            }
        });
        builder.setPositiveButton("确定", new android.content.DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                SysUtils.showToast("设置成功");
            }
        });
        builder.setNegativeButton("取消", null);
        builder.show();
    }
}
