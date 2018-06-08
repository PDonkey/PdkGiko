package com.pdk.pdkgiko.net.api;

import com.pdk.pdkgiko.bean.CategoryResult;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by uatql90533 on 2017/12/11.
 */

public interface GankApi {
    /**
     * 根据category获取干货
     *
     * @param category
     * @param count
     * @param page
     * @return
     */
    @GET("data/{category}/{count}/{page}")
    Observable<CategoryResult> getCategoryData(@Path("category")String category, @Path("count")int count, @Path("page")int page);



//    @GET("data/{category}/{count}/{page}")
//    Observable<CategoryResult> getCategoryData(@Path("category")String category, @Path("count")int count, @Path("page")int page);
}
