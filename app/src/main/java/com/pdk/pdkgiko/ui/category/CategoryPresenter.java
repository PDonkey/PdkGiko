package com.pdk.pdkgiko.ui.category;

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

    public CategoryPresenter(CategoryContract.ICategoryView iCategoryView) {
        mICategoryView = iCategoryView;
    }
    /**
     * All the life , she has seen
     * All the meaner side of me
     * They took away the prophet's dream
     * For a profit on the street
     * Now she's stronger than you know
     * A heart of steel starts to grow
     *
     * All his life , he's been told
     * He'll be nothing when he's old
     * All the kick's and all the blows
     * He will never let it show
     * Cause he's stronger than you know
     * A heart of steel starts to grow
     * When you've been fighting for it all your life
     * You've been struggling to make things right
     * That's how a superhero learns to fly
     * (Every day,every hour, Turn the pain into power)
     * When you've been fighting for it all your life
     * You've been working every day and night
     * That's how a superhero learns to fly
     * All the hurt , all the lies
     * All the tears that they cry
     * When the moment is just right
     * You'll see fire in their eyes
     * Cause they're stronger than you know
     * A heart of steel starts to gorw
     *
     *
     *
     *
     *
     *
     *
     */

    @Override
    public void getCategroyItems(final boolean isRefresh) {
        if (isRefresh) {
            mPage = 1;
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

                    }

                    @Override
                    public void onNext(CategoryResult categoryResult) {
                        if (categoryResult != null && !categoryResult.error) {
                            if (categoryResult.results == null || categoryResult.results.size() == 0) {
                                mICategoryView.getCategoryItemsFail("获取数据为空！");
                            } else {
                                if (isRefresh) {
                                    mICategoryView.setCategoryItems(categoryResult.results);
                                    mICategoryView.hideSwipLoading();
                                    mICategoryView.setLoding();
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

    }

    @Override
    public void unSubscribe() {

    }
}
