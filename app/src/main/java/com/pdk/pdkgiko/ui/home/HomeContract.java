package com.pdk.pdkgiko.ui.home;

import com.pdk.pdkgiko.base.BasePresetener;
import com.pdk.pdkgiko.base.BaseView;

import java.util.List;

/**
 * Created by uatql90533 on 2017/12/11.
 */

public interface HomeContract {

    interface IHomeView extends BaseView{
        void showBannerFail(String failMessage);
        void setBanner(List<String> imageUrl);
    }

    interface IHomePresenter extends BasePresetener{
        /**
         * 获取Banner轮播图数据
         */
        void getBannerData();
    }
}
