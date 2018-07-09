package com.pdk.pdkgiko.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.pdk.pdkgiko.R;
import com.pdk.pdkgiko.adapter.CategoryAdapter;
import com.pdk.pdkgiko.base.BaseFragment;
import com.pdk.pdkgiko.bean.CategoryResult;
import com.pdk.pdkgiko.ui.category.CategoryContract;
import com.pdk.pdkgiko.ui.category.CategoryPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by uatql90533 on 2017/12/13.
 */

public class CategoryFragment extends BaseFragment implements CategoryContract.ICategoryView, BaseQuickAdapter.RequestLoadMoreListener, SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.OnItemClickListener {
    public static final String CATEGROY_NAME = "CategroyFragment.CATEGROY_NAME";
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;

    private String categroyName;
    private CategoryContract.ICategoryPresenter iCategoryPresenter;

    private CategoryAdapter categoryAdapter;
    private List<CategoryResult.ResultsBean> resultBeanList = new ArrayList<>();

    private static final int TOTAL_COUNTER = 10;

    private static final int PAGE_SIZE = 6;
    private int mCurrentCounter = 0;

    public static CategoryFragment newInstance(String categroyName) {
        CategoryFragment categoryFragment = new CategoryFragment();
        Bundle bundle = new Bundle();
        bundle.putString(CATEGROY_NAME, categroyName);
        categoryFragment.setArguments(bundle);
        return categoryFragment;
    }

    @Override
    protected int getContentViewLayoutID() {

        return R.layout.fragment_categroy;
    }

    @Override
    protected void initView(View view) {
        iCategoryPresenter = new CategoryPresenter(this);
        categroyName = getArguments().getString(CATEGROY_NAME);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipefresh_layout);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycleview_);
        swipeRefreshLayout.setOnRefreshListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        categoryAdapter = new CategoryAdapter(getActivity(), resultBeanList);
        recyclerView.setAdapter(categoryAdapter);
        iCategoryPresenter.subscribe();
        categoryAdapter.setOnLoadMoreListener(this, recyclerView);
        mCurrentCounter = categoryAdapter.getData().size();
    }

    @Override
    public void getCategoryItemsFail(String failMessage) {
        if (getUserVisibleHint()) {
            Toast.makeText(getActivity(), failMessage, Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (iCategoryPresenter!=null) {
            iCategoryPresenter.unSubscribe();
        }
    }

    @Override
    public void setCategoryItems(List<CategoryResult.ResultsBean> data) {
        resultBeanList.clear();
        resultBeanList.addAll(data);
        categoryAdapter.notifyDataSetChanged();

    }

    @Override
    public void addCategoryItems(List<CategoryResult.ResultsBean> data) {
        categoryAdapter.addData(data);
        categoryAdapter.loadMoreComplete();
    }

    @Override
    public void showSwipeLoading() {
        swipeRefreshLayout.setRefreshing(true);

    }

    @Override
    public void hideSwipLoading() {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public String getCategoryName() {
        String sss = categroyName;
        Log.d("CategoryNmae---->", categroyName);
        return this.categroyName;
    }

    @Override
    public void setNoMore() {
        categoryAdapter.loadMoreEnd(true);
    }

    @Override
    public void onRefresh() {
        iCategoryPresenter.getCategroyItems(true);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {


    }

    @Override
    public void onLoadMoreRequested() {
        if (categoryAdapter.getData().size() < PAGE_SIZE) {
            categoryAdapter.loadMoreEnd(true);
        } else {
            iCategoryPresenter.getCategroyItems(false);
        }

    }
}
