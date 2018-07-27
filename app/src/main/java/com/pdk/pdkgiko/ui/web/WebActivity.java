package com.pdk.pdkgiko.ui.web;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.widget.ScrollView;

import com.pdk.pdkgiko.R;
import com.pdk.pdkgiko.base.BaseActivity;
import com.pdk.pdkgiko.utils.AppScreenUtil;
import com.pdk.pdkgiko.utils.StatusBarUtil;
import com.pdk.pdkgiko.widegt.MyScrollView;

/**
 * Created by lqf on 2018/7/16.
 */

public class WebActivity extends BaseActivity {

    @Override
    protected int getContentViewLayoutID() {
        StatusBarUtil.setTranslucent(this);
        return R.layout.activity_web;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }


}
