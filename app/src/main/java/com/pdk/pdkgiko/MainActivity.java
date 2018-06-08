package com.pdk.pdkgiko;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.kekstudio.dachshundtablayout.DachshundTabLayout;
import com.pdk.pdkgiko.activity.GridActivity;
import com.pdk.pdkgiko.activity.TestActivity;
import com.pdk.pdkgiko.adapter.CommonViewPageAdapter;
import com.pdk.pdkgiko.base.BaseActivity;
import com.pdk.pdkgiko.bean.Picture;
import com.pdk.pdkgiko.fragment.CategoryFragment;
import com.pdk.pdkgiko.ui.home.HomeContract;
import com.pdk.pdkgiko.ui.home.HomePresenter;
import com.pdk.pdkgiko.utils.AppScreenUtil;
import com.pdk.pdkgiko.utils.MyClickListener;
import com.pdk.pdkgiko.utils.StatusBarUtil;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import java.util.List;

public class MainActivity extends BaseActivity implements HomeContract.IHomeView, OnBannerListener {
    private DrawerLayout drawerLayout;
    private CoordinatorLayout mMianCoorlayout;
    private AppBarLayout mMainAppbar;
    private ImageView mMainHeadImg;
    private Banner mBanner;
    private Toolbar mToolbar;
    private DachshundTabLayout mMainTab;
    private ViewPager mMainViewpage;
    private FloatingActionButton mMainFab;
    private NavigationView mNavView;


    private long mExitTime = 0;
    private HomePresenter homePresenter;
    String[] titles = {
            GlobalConfig.CATEGORY_NAME_APP,
            GlobalConfig.CATEGORY_NAME_ANDROID,
            GlobalConfig.CATEGORY_NAME_IOS,
            GlobalConfig.CATEGORY_NAME_FRONT_END,
            GlobalConfig.CATEGORY_NAME_RECOMMEND,
            GlobalConfig.CATEGORY_NAME_RESOURCE
    };

    @Override
    protected int getContentViewLayoutID() {
        StatusBarUtil.setTranslucent(this);
        return R.layout.activity_main;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

        homePresenter = new HomePresenter(this);
        drawerLayout = (DrawerLayout) findViewById(R.id.activity_main);
        mMianCoorlayout = (CoordinatorLayout) findViewById(R.id.mian_coorlayout);
        mMainAppbar = (AppBarLayout) findViewById(R.id.main_appbar);
        mMainHeadImg = (ImageView) findViewById(R.id.main_head_img);
        mBanner = (Banner) findViewById(R.id.main_banner);
        mToolbar = (Toolbar) findViewById(R.id.main_toolbar);
        mMainTab = (DachshundTabLayout) findViewById(R.id.main_tab);
        mMainViewpage = (ViewPager) findViewById(R.id.main_viewpage);
        mMainFab = (FloatingActionButton) findViewById(R.id.main_fab);
        mNavView = (NavigationView) findViewById(R.id.nav_view);
        setSupportActionBar(mToolbar);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {//4.4以上
            //设置toolbar 高度为80dp，适配状态栏
            ViewGroup.LayoutParams layoutParams = mToolbar.getLayoutParams();
            layoutParams.height = AppScreenUtil.dip2px(this, 80);
            mToolbar.setLayoutParams(layoutParams);
        }


        initDrawerLayout();

        mBanner.setIndicatorGravity(Gravity.CENTER);
        mBanner.setOnBannerListener(this);

        CommonViewPageAdapter commonViewPageAdapter = new CommonViewPageAdapter(getSupportFragmentManager(), titles);

        CategoryFragment appFragment = CategoryFragment.newInstance(titles[0]);
        CategoryFragment androidFragment = CategoryFragment.newInstance(titles[1]);
        CategoryFragment iosFragment = CategoryFragment.newInstance(titles[2]);
        CategoryFragment frontFragment = CategoryFragment.newInstance(titles[3]);
        CategoryFragment xiaFragment = CategoryFragment.newInstance(titles[4]);
        CategoryFragment tuoFragment = CategoryFragment.newInstance(titles[5]);
        commonViewPageAdapter.addFragment(appFragment);
        commonViewPageAdapter.addFragment(androidFragment);
        commonViewPageAdapter.addFragment(iosFragment);
        commonViewPageAdapter.addFragment(frontFragment);
        commonViewPageAdapter.addFragment(xiaFragment);
        commonViewPageAdapter.addFragment(tuoFragment);
        mMainViewpage.setAdapter(commonViewPageAdapter);
        mMainTab.setupWithViewPager(mMainViewpage);
        mMainViewpage.setCurrentItem(1);
        mMainViewpage.setOffscreenPageLimit(6);
        homePresenter.getBannerData();

    }

    @Override
    public void showBannerFail(String failMessage) {
        Toast.makeText(this, failMessage, Toast.LENGTH_LONG).show();
    }

    @Override
    public void setBanner(List<String> imageUrl) {
        mBanner.setImages(imageUrl).setImageLoader(new GlideImageLoader()).start();
    }


    @Override
    public void onBackPressed() {
        if ((System.currentTimeMillis() - mExitTime) > 2000) {
            Snackbar.make(drawerLayout, R.string.exit_toast, Toast.LENGTH_LONG).show();
            mExitTime = System.currentTimeMillis();
        } else {
            finish();
        }
    }

    /**
     * 初始化DrawerLayout
     */
    private void initDrawerLayout() {
        mNavView.inflateHeaderView(R.layout.layout_main_nav);
        View headView = mNavView.getHeaderView(0);
        headView.findViewById(R.id.ll_nav_homepage).setOnClickListener(listener);
        headView.findViewById(R.id.ll_nav_scan_address).setOnClickListener(listener);
        headView.findViewById(R.id.ll_nav_deedback).setOnClickListener(listener);
        headView.findViewById(R.id.ll_nav_exit).setOnClickListener(listener);
        headView.findViewById(R.id.ll_nav_donation).setOnClickListener(listener);


    }


    private MyClickListener listener = new MyClickListener() {
        @Override
        protected void onNotDouleClick(View view) {
            drawerLayout.closeDrawer(GravityCompat.START);
            switch (view.getId()) {
                case R.id.ll_nav_homepage:
                    Snackbar.make(drawerLayout, "主页", Toast.LENGTH_LONG).show();
                    break;
                case R.id.ll_nav_scan_address:
                    startActivity(new Intent(MainActivity.this, TestActivity.class));
                    Snackbar.make(drawerLayout, "关于我们", Toast.LENGTH_LONG).show();
                    break;
                case R.id.ll_nav_deedback:
                    startActivity(new Intent(MainActivity.this, GridActivity.class));
                    Snackbar.make(drawerLayout, "反馈", Toast.LENGTH_LONG).show();
                    break;
                case R.id.ll_nav_donation:
                    Snackbar.make(drawerLayout, "捐赠", Toast.LENGTH_LONG).show();
                    break;
                case R.id.ll_nav_exit:
                    Snackbar.make(drawerLayout, "退出", Toast.LENGTH_LONG).show();
                    break;

            }

        }
    };


    @Override
    public void OnBannerClick(int position) {
        Picture picture = homePresenter.getBannerPicList().get(position);
    }
}
