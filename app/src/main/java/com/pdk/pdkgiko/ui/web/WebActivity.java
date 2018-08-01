package com.pdk.pdkgiko.ui.web;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import com.pdk.pdkgiko.R;
import com.pdk.pdkgiko.base.BaseActivity;
import com.pdk.pdkgiko.utils.AppScreenUtil;
import com.pdk.pdkgiko.utils.StatusBarUtil;
import com.pdk.pdkgiko.widegt.MyScrollView;

import cn.sharesdk.onekeyshare.OnekeyShare;

/**
 * Created by lqf on 2018/7/16.
 */

public class WebActivity extends BaseActivity implements View.OnClickListener{
    private AppBarLayout appBarLayout;
    private Toolbar mToolbar;
    private TextView mTitle;
    private ImageView mImageShare;
    private ProgressBar progressBar;
    private WebView webView;

    @Override
    protected int getContentViewLayoutID() {
//        StatusBarUtil.setTranslucent(this);
        return R.layout.activity_web;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        appBarLayout = (AppBarLayout) findViewById(R.id.appbar_web);
        mToolbar = (Toolbar) findViewById(R.id.toolbar_web);
        mTitle = (TextView) findViewById(R.id.tv_webtitle);
        mImageShare = (ImageView) findViewById(R.id.img_share);
        progressBar = (ProgressBar) findViewById(R.id.progressbar_web);
        webView = (WebView) findViewById(R.id.webview);
        mImageShare.setOnClickListener(this);
        initWebView();

    }

    @Override
    protected void initData() {
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mTitle.setText(TextUtils.isEmpty(getIntent().getStringExtra("WebTitle")) ? "null" : getIntent().getStringExtra("WebTitle"));
        webView.loadUrl(getIntent().getStringExtra("WebUrl"));
    }

    private void initWebView() {
        WebSettings webSettings = webView.getSettings();
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setAppCacheEnabled(true);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webSettings.setSupportZoom(true);

        webView.setWebChromeClient(new MyWebChrome());
        webView.setWebViewClient(new MyWebClient());

    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            finish();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_share:
                showShare();
                break;
        }
    }
    private void showShare() {
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();

        // title标题，微信、QQ和QQ空间等平台使用
        oks.setTitle("title");
        // titleUrl QQ和QQ空间跳转链接
        oks.setTitleUrl("http://sharesdk.cn");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("我是分享文本");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
//        oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url在微信、微博，Facebook等平台中使用
        oks.setUrl("http://sharesdk.cn");
        // comment是我对这条分享的评论，仅在人人网使用
        oks.setComment("我是测试评论文本");
        // 启动分享GUI
        oks.show(this);
    }

    private class MyWebChrome extends WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            progressBar.setVisibility(View.VISIBLE);
            progressBar.setProgress(newProgress);

        }
    }

    private class MyWebClient extends WebViewClient {
        @Override
        public void onPageFinished(WebView view, String url) {
            progressBar.setVisibility(View.GONE);
        }
    }

}
