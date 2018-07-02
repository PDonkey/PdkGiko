package com.pdk.pdkgiko.activity;

import android.os.Bundle;
import android.widget.GridView;
import android.widget.TextView;

import com.pdk.pdkgiko.R;
import com.pdk.pdkgiko.adapter.GridChildAdapter;
import com.pdk.pdkgiko.adapter.GridGroupAdapter;
import com.pdk.pdkgiko.base.BaseActivity;
import com.pdk.pdkgiko.bean.GroupAppBean;
import com.pdk.pdkgiko.widegt.DragGridView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Administrator on 2018/6/8.
 */

public class GridActivity extends BaseActivity implements GridGroupAdapter.DataChangeListener, GridChildAdapter.GroupAppStatusChangeListener {
    private DragGridView mGridView;
    private GridView mGridViewAll;
    private TextView mTextView;
    //    private List<String> childList;
//    private List<String> groupList;
    private List<GroupAppBean.AppBean> childList;
    private List<GroupAppBean.AppBean> groupList;
    //    private List<GroupAppBean.AppBean> appBeanList;
    private GridChildAdapter gridChildAdapter;
    private GridGroupAdapter gridGroupAdapter;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_gridview;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        mGridView = (DragGridView) findViewById(R.id.grid_home);
        mGridViewAll = (GridView) findViewById(R.id.grid_all);
        mTextView = (TextView) findViewById(R.id.tv_editor);
        groupList = new ArrayList<>();
        childList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {

        }
        for (int i = 0; i < 10; i++) {
//            groupList.add("item" + i);
            groupList.add(new GroupAppBean.AppBean(false, "item" + i));
        }
//        for (int i = 0; i < 10; i++) {
//            childList.add("item" + i);
//        }
        gridChildAdapter = new GridChildAdapter(this, childList);
        gridGroupAdapter = new GridGroupAdapter(this, groupList, childList);
        mGridView.setAdapter(gridChildAdapter);
        mGridViewAll.setAdapter(gridGroupAdapter);
        gridChildAdapter.setAppStatusChangeListener(this);
        gridGroupAdapter.setmDataChangeListener(this);
        mGridView.setOnItemChanageListener(new DragGridView.OnItemChanageListener() {
            @Override
            public void onChange(int from, int to) {
                if (from < to) {
                    for (int i = from; i < to; i++) {
                        Collections.swap(childList, i, i + 1);
                    }
                } else if (from > to) {
                    for (int i = from; i > to; i--) {
                        Collections.swap(childList, i, i - 1);
                    }
                }
                gridChildAdapter.notifyDataSetChanged();
            }
        });

    }


    @Override
    public void addChildData(int position, GroupAppBean.AppBean data) {
        childList.add(childList.size(), data);
        gridChildAdapter.notifyDataSetChanged();
    }

    @Override
    public void removeChildData(int position, GroupAppBean.AppBean data) {
        childList.remove(data);
        gridChildAdapter.notifyDataSetChanged();
    }


    @Override
    public void appStatusChange(boolean isChange, GroupAppBean.AppBean data) {
        if (isChange) {
            for (int i = 0; i < groupList.size(); i++) {
                if (groupList.get(i) == data) {
                    groupList.get(i).setInclude(false);
                    gridGroupAdapter.notifyDataSetChanged();
                }
            }
        }
    }
}
