package com.pdk.pdkgiko.utils;

import android.view.View;

import java.util.Calendar;

/**
 * Created by pdk on 2017/12/8.
 * 一个很好的防止快速点击执行多次的情况
 */

public abstract class MyClickListener implements View.OnClickListener {
    private static final int MIN_CLICK_DELY_TIME=1000;
    private long lastClickTime=0;
    private int id=-1;

    @Override
    public void onClick(View v) {
        long currentTime= Calendar.getInstance().getTimeInMillis();
        int mId=v.getId();
        if (id!=mId) {
            id=mId;
            lastClickTime=currentTime;
            onNotDouleClick(v);
            return;
        }
        if(currentTime-lastClickTime>MIN_CLICK_DELY_TIME){
            lastClickTime=currentTime;
            onNotDouleClick(v);
        }
    }

    protected  abstract void onNotDouleClick(View view);
}
