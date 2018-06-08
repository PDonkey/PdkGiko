package com.pdk.pdkgiko.ui.home;


import android.util.Log;

import com.google.gson.Gson;
import com.pdk.pdkgiko.bean.CategoryResult;
import com.pdk.pdkgiko.bean.Picture;
import com.pdk.pdkgiko.net.NetWork;
import com.pdk.pdkgiko.ui.home.HomeContract.IHomePresenter;
import com.pdk.pdkgiko.ui.home.HomeContract.IHomeView;


import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by uatql90533 on 2017/12/11.
 */

public class HomePresenter implements IHomePresenter {
    private IHomeView mHomeView;
    private List<Picture> mPicList;

    public HomePresenter(IHomeView mHomeView) {
        this.mHomeView = mHomeView;
        mPicList = new ArrayList<>();

    }

    public List<Picture> getBannerPicList() {
        return this.mPicList;
    }

    @Override
    public void getBannerData() {
        NetWork.getGankApi()
                .getCategoryData("福利", 5, 1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CategoryResult>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d("HomePresenter-->", "ooooooooooooooooooooooooooooooooooooooooooooooo");
                    }

                    @Override
                    public void onNext(CategoryResult categoryResult) {
                        String s = new Gson().toJson(categoryResult);
                        if (categoryResult != null && categoryResult.results != null
                                && categoryResult.results.size() > 0) {
                            List<String> imgUrls = new ArrayList<String>();
                            for (CategoryResult.ResultsBean result : categoryResult.results) {
                                if (!result.url.isEmpty()) {
//                                    imgUrls.add(result.url);
                                    imgUrls.add("https://www.w3cschool.cn/attachments/image/20161031/1477900523962173.png");
                                }
                                Picture picture = new Picture();
                                picture.desc = result.desc;
//                                picture.url = result.url;
                                picture.url = "https://www.w3cschool.cn/attachments/image/20161031/1477900523962173.png";
                                mPicList.add(picture);
                            }
                            mHomeView.setBanner(imgUrls);
                        } else {
                            mHomeView.showBannerFail("Banner 图加载失败");
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        mHomeView.showBannerFail("Banner 图加载失败");
                    }

                    @Override
                    public void onComplete() {
                        Log.d("HomePresenter-->", "》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》");
                    }
                });


    }

    @Override
    public void subscribe() {
        getBannerData();
    }

    @Override
    public void unSubscribe() {
//        if (mSubscription != null) {
//            mSubscription.cancel();
//        }
    }
}
