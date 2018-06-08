package com.pdk.pdkgiko.widegt;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.pdk.pdkgiko.R;

/**
 * Created by lqf on 2018/6/1.
 */

public class DialogManager {
    private Dialog mDialog;
    private Context mContext;
    private RelativeLayout mRlStart;
    private TextView mTvStart;
    private RelativeLayout mRlCancel;
    private TextView mTvCancel;

    public DialogManager(Context context) {
        this.mContext = context;
    }

    public void showRecordDialog() {
        mDialog = new Dialog(mContext, R.style.Theme_audioDialog);
        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_manager, null);
        mDialog.setContentView(view);
        mRlStart = (RelativeLayout) view.findViewById(R.id.rl_start);
        mTvStart = (TextView) view.findViewById(R.id.tv_start);
        mRlCancel = (RelativeLayout) view.findViewById(R.id.rl_cancel);
        mTvCancel = (TextView) view.findViewById(R.id.tv_cancel);
        mDialog.show();
    }

    /**
     * 设置正在录音的dialog界面
     */
    public void recording() {
        if (mDialog != null && mDialog.isShowing()) {
            mRlStart.setVisibility(View.VISIBLE);
            mRlCancel.setVisibility(View.GONE);
            mTvStart.setVisibility(View.VISIBLE);
            mTvCancel.setVisibility(View.GONE);
            mRlCancel.setBackground(mContext.getResources().getDrawable(R.drawable.yuyin_voice_1));
            mTvStart.setText(R.string.up_for_cancel);
        }
    }

    /**
     * 准备取消发送的dialog界面
     */
    public void cancleRrcoring() {
        if (mDialog != null && mDialog.isShowing()) {
            mRlStart.setVisibility(View.GONE);
            mRlCancel.setVisibility(View.VISIBLE);
            mTvStart.setVisibility(View.GONE);
            mTvCancel.setVisibility(View.VISIBLE);
            mRlCancel.setBackground(mContext.getResources().getDrawable(R.drawable.yuyin_cancel));
            mTvCancel.setText(R.string.want_to_cancle);
        }
    }

    /**
     * 按录音按钮时间过短
     */
    public void recordTooShort() {
        if (mDialog != null && mDialog.isShowing()) {
            mRlStart.setVisibility(View.GONE);
            mRlCancel.setVisibility(View.VISIBLE);
            mTvStart.setVisibility(View.GONE);
            mTvCancel.setVisibility(View.VISIBLE);
            mRlCancel.setBackground(mContext.getResources().getDrawable(R.drawable.yuyin_gantanhao));
            mTvCancel.setText(R.string.time_too_short);
        }
    }

    /**
     * 隐藏dialog
     */
    public void dismissDialog() {
        if (mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
            mDialog = null;
        }
    }

    /**
     * 设置说话时背景图标的轮播
     *
     * @param level
     */
    public void updateVoiceLevel(int level) {
        if (mDialog != null && mDialog.isShowing()) {
            //通过level来找到指定的
            int resId = mContext.getResources().getIdentifier("yuyin_voice_" + level
                    , "drawable", mContext.getPackageName());
            mRlStart.setBackgroundResource(resId);
        }
    }

    public TextView getmTvStart() {
        return mTvStart;
    }

    public void setmTvStart(TextView mTvStart) {
        this.mTvStart = mTvStart;
    }
}
