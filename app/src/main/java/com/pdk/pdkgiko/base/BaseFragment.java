package com.pdk.pdkgiko.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by uatql90533 on 2017/12/13.
 */

public abstract class BaseFragment extends Fragment {

//    public Unbinder unbinder;

    /**
     * 获取布局ID
     *
     * @return
     */
    protected abstract int getContentViewLayoutID();

    /**
     * 初始化界面
     */
    protected abstract void initView(View view);


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (getContentViewLayoutID() != 0) {
            return inflater.inflate(getContentViewLayoutID(), container, false);
        } else {

            return super.onCreateView(inflater, container, savedInstanceState);
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        unbinder = ButterKnife.bind(this, view);
        initView(view);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        unbinder.unbind();
    }
}
