package com.pdk.pdkgiko.net;

import com.pdk.pdkgiko.net.api.GankApi;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by uatql90533 on 2017/12/11.
 */

public class NetWork {
//    public static String baseUrl = "http://gank.io/api/";
    private static GankApi gankApi;
    private static OkHttpClient okHttpClient = new OkHttpClient();

    public static GankApi getGankApi() {
        if (gankApi == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl("http://gank.io/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
            gankApi = retrofit.create(GankApi.class);
        }
        return gankApi;
    }
}
