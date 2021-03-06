package com.pdk.pdkgiko.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ScrollView;

import com.pdk.pdkgiko.R;
import com.pdk.pdkgiko.base.BaseActivity;
import com.pdk.pdkgiko.utils.AppScreenUtil;
import com.pdk.pdkgiko.utils.StatusBarUtil;
import com.pdk.pdkgiko.widegt.MyScrollView;

/**
 * Created by Administrator on 2018/7/26.
 */

public class ScrollActivity extends BaseActivity implements View.OnClickListener{
    private MyScrollView myScrollView;
    private Toolbar mToolbar;
    private Button mBottomsheetbehavior;
    private float headerHeight;//顶部高度
    private float minHeaderHeight;//顶部最低高度，即Bar的高度
    @Override
    protected int getContentViewLayoutID() {
        StatusBarUtil.setTranslucent(this);
        return R.layout.activity_scroll;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
//        //获取手机dpi
//        DisplayMetrics displayMetrics=getResources().getDisplayMetrics();
//        //横向分辨率
//        int width=displayMetrics.widthPixels;
//        //竖向分辨率
//        int height=displayMetrics.heightPixels;
//        //density屏幕密度
//        float density=displayMetrics.density;
//        //dpi=density*160
//        float dpi=density*160;
        mBottomsheetbehavior= (Button) findViewById(R.id.bt_01);
        mBottomsheetbehavior.setOnClickListener(this);
        myScrollView = (MyScrollView) findViewById(R.id.scrollView);
        mToolbar = (Toolbar) findViewById(R.id.behavior_toolbar);
        setSupportActionBar(mToolbar);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {//4.4以上
            //设置toolbar 高度为80dp，适配状态栏
            ViewGroup.LayoutParams layoutParams = mToolbar.getLayoutParams();
            layoutParams.height = AppScreenUtil.dip2px(this, 80);
            mToolbar.setLayoutParams(layoutParams);
        }
        mToolbar.setBackgroundColor(Color.argb(0, 236, 114, 60));
        initMeasure();
        myScrollView.setOnScrollChangedListener(new MyScrollView.OnScrollChangedListener() {
            @Override
            public void onScrollChanged(ScrollView who, int l, int t, int oldl, int oldt) {
                //Y轴偏移量
                float scrollY = who.getScrollY();

                //变化率
                float headerBarOffsetY = headerHeight - minHeaderHeight;//Toolbar与header高度的差值
                if (scrollY == 0) {
                    mToolbar.setBackgroundColor(Color.argb(0, 236, 114, 60));
                } else if (scrollY > 0) {
                    float offset = 1 - Math.max((headerBarOffsetY - scrollY) / headerBarOffsetY, 0f);
                    //Toolbar背景色透明度
                    mToolbar.setBackgroundColor(Color.argb((int) (offset * 255), 236, 114, 60));
                } else {
                    mToolbar.setBackgroundColor(Color.argb(0, 236, 114, 60));
                }


            }
        });
    }

    /**
     * 定义一个默认布局头高度和最低高度
     */
    private void initMeasure() {
        headerHeight = getResources().getDimension(R.dimen.dp_200);
        minHeaderHeight = getResources().getDimension(R.dimen.abc_action_bar_default_height_material);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_01:
                startActivity(new Intent(this,BehaviorSheetBottomActivity.class));
                break;
        }
    }
}
