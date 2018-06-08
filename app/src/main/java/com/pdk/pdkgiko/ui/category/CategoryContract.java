package com.pdk.pdkgiko.ui.category;

import com.pdk.pdkgiko.base.BasePresetener;
import com.pdk.pdkgiko.base.BaseView;
import com.pdk.pdkgiko.bean.CategoryResult;

import java.util.List;

/**
 * Created by uatql90533 on 2018/4/8.
 */
public interface CategoryContract {

    interface ICategoryView extends BaseView {
        void getCategoryItemsFail(String failMessage);

        void setCategoryItems(List<CategoryResult.ResultsBean> data);

        void addCategoryItems(List<CategoryResult.ResultsBean> data);

        void showSwipeLoading();

        void hideSwipLoading();

        void setLoding();

        String getCategoryName();

        void setNoMore();

    }

    interface ICategoryPresenter extends BasePresetener {
        void getCategroyItems(boolean isRefresh);

    }

}
