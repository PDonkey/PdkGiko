package com.pdk.pdkgiko.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by uatql90533 on 2017/12/11.
 */

public class Utils {
    private static Context context;

    public Utils() {
        throw new UnsupportedOperationException("you can't instantiate me");
    }

    /**
     * 初始化工具类
     *
     * @param context
     */
    public static void init(Context context) {
        Utils.context = context.getApplicationContext();

    }

    /**
     * 获取ApplicationContext
     *
     * @return ApplicationContext
     */
    public static Context getContext() {
        if (context != null) {
            return context;
        }
        throw new NullPointerException("you should init first");
    }


    /**
     * 使用浏览器打开链接
     *
     * @param context
     * @param content
     */
    public static void openLink(Context context, String content) {
        Uri uri = Uri.parse(content);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        context.startActivity(intent);
    }

    public static String dateFormat(String timestamp) {
        if (timestamp == null) {
            return "unknown";
        }
        @SuppressLint("SimpleDateFormat") SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SS");
        @SuppressLint("SimpleDateFormat") SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
            Date date = inputFormat.parse(timestamp);
            return outputFormat.format(date);
        } catch (ParseException e) {
            return "unknown";
        }
    }

}
