package com.pdk.pdkgiko.activity;

import android.os.Bundle;
import android.widget.Button;

import com.pdk.pdkgiko.R;
import com.pdk.pdkgiko.base.BaseActivity;

/**
 * Created by Administrator on 2018/6/1.
 */

public class MediaPlayActivity extends BaseActivity {
    private Button mBt;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_mediaplay;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        mBt = (Button) findViewById(R.id.bt_recorder);
    }


}
