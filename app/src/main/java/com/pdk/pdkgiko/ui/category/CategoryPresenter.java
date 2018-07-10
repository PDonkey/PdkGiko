package com.pdk.pdkgiko.ui.category;

import com.google.gson.Gson;
import com.pdk.pdkgiko.GlobalConfig;
import com.pdk.pdkgiko.bean.CategoryResult;
import com.pdk.pdkgiko.net.NetWork;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by uatql90533 on 2018/4/8.
 */
public class CategoryPresenter implements CategoryContract.ICategoryPresenter {
    private CategoryContract.ICategoryView mICategoryView;
    private int mPage = 1;
    private Disposable disposable = null;

    public CategoryPresenter(CategoryContract.ICategoryView iCategoryView) {
        mICategoryView = iCategoryView;
    }
     /*
     *
     */

    @Override
    public void getCategroyItems(final boolean isRefresh) {
        if (isRefresh) {
            mPage = 1;
            mICategoryView.showSwipeLoading();
        } else {
            mPage++;
        }

        NetWork.getGankApi()
                .getCategoryData(mICategoryView.getCategoryName(), GlobalConfig.CATEGORY_COUNT, mPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CategoryResult>() {


                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onNext(CategoryResult categoryResult) {

                        if (categoryResult != null && !categoryResult.error) {
                            if (categoryResult.results == null || categoryResult.results.size() == 0) {
                                mICategoryView.getCategoryItemsFail("获取数据为空！");
                            } else {
                                if (isRefresh) {
                                    String s = new Gson().toJson(categoryResult);
                                    mICategoryView.setCategoryItems(categoryResult.results);
                                    mICategoryView.hideSwipLoading();
//                                    mICategoryView.setLoding();
                                } else {
                                    mICategoryView.addCategoryItems(categoryResult.results);
                                }
                                if (categoryResult.results.size() < GlobalConfig.CATEGORY_COUNT) {
                                    mICategoryView.setNoMore();
                                }
                            }
                        } else {
                            mICategoryView.getCategoryItemsFail("获取数据失败！");
                        }

                    }


                    @Override
                    public void onError(Throwable e) {
                        mICategoryView.hideSwipLoading();
                        mICategoryView.getCategoryItemsFail(mICategoryView.getCategoryName() + "列表数据获取失败！");
                    }

                    @Override
                    public void onComplete() {

                    }


                });

    }

    @Override
    public void subscribe() {
        getCategroyItems(true);
    }

    @Override
    public void unSubscribe() {
        if (disposable != null) {
            disposable.dispose();
        }
    }
}
