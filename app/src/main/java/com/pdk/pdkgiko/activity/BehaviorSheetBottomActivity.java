package com.pdk.pdkgiko.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.pdk.pdkgiko.R;
import com.pdk.pdkgiko.base.BaseActivity;

/**
 * Created by lqf on 2018/7/26.
 */

public class BehaviorSheetBottomActivity extends BaseActivity {
    private Button btBehaviorSheetBottom;
    private Button btBehaviorBottomDialog;
    private BottomSheetDialog bottomSheetDialog;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_behavior_bottom;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

        btBehaviorSheetBottom = (Button) findViewById(R.id.bt_behavior_sheet_bottom);
        btBehaviorBottomDialog = (Button) findViewById(R.id.bt_behavior_bottom_dialog);
        View behaviorView = findViewById(R.id.frame_view);
        //获取BottomSheetBehavior
        BottomSheetBehavior sheetBehavior = BottomSheetBehavior.from(behaviorView);
        //下滑的时候是否可以隐藏
        sheetBehavior.setHideable(true);
        btBehaviorSheetBottom.setOnClickListener(v -> {
            if (sheetBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {
                sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            } else {
                sheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
            }

        });

        btBehaviorBottomDialog.setOnClickListener(v -> {
            showDialogBottom();
        });
    }

    private void showDialogBottom() {
        if (bottomSheetDialog == null) {
            bottomSheetDialog = new BottomSheetDialog(this);
            View view = LayoutInflater.from(this).inflate(R.layout.bottom_sheet_item_dialog, null);
            bottomSheetDialog.setContentView(view);
            bottomSheetDialog.setCancelable(true);
            bottomSheetDialog.setCanceledOnTouchOutside(true);
            //解决用手指下滑隐藏dialog后再次调用show方法不能弹出的问题
            View androidView = bottomSheetDialog.getDelegate().findViewById(android.support.design.R.id.design_bottom_sheet);
            BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from(androidView);
            bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
                @Override
                public void onStateChanged(@NonNull View bottomSheet, int newState) {
                    if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                        bottomSheetDialog.dismiss();
                        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    }
                }

                @Override
                public void onSlide(@NonNull View bottomSheet, float slideOffset) {

                }
            });
            bottomSheetDialog.show();

        } else {
            bottomSheetDialog.show();
        }
    }
}
