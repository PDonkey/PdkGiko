package com.pdk.pdkgiko.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by uatql90533 on 2018/3/21.
 */
public class MyBroadcastReceiver extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "received MyBroadcastReceiver", Toast.LENGTH_SHORT).show();
        abortBroadcast();//截断广播
    }
}
