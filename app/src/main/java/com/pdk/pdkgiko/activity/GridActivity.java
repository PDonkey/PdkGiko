package com.pdk.pdkgiko.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.pdk.pdkgiko.R;
import com.pdk.pdkgiko.adapter.GridAdapter;
import com.pdk.pdkgiko.adapter.GridAllAdapter;
import com.pdk.pdkgiko.base.BaseActivity;
import com.pdk.pdkgiko.widegt.DragGridView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Administrator on 2018/6/8.
 */

public class GridActivity extends BaseActivity {
    private DragGridView mGridView;
    private GridView mGridViewAll;
    private TextView mTextView;
    private List<String> childList;
    private List<String> groupList;
    private GridAdapter gridAdapter;
    private GridAllAdapter gridAllAdapter;

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
            groupList.add("item" + i);
        }
        for (int i = 0; i < 10; i++) {
            childList.add("item" + i);
        }
        gridAdapter = new GridAdapter(this, childList);
        gridAllAdapter = new GridAllAdapter(this, groupList);
        mGridView.setAdapter(gridAdapter);
        mGridViewAll.setAdapter(gridAllAdapter);
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
                gridAdapter.notifyDataSetChanged();
            }
        });

//        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                if (position == parent.getChildCount() - 1) {
//                    addDatas();
//                }
//            }
//        });
    }

    public void addDatas() {
        childList.add("item");
        gridAdapter.notifyDataSetChanged();
    }
}
