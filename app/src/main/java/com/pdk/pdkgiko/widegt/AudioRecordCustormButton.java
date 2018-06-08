package com.pdk.pdkgiko.widegt;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;

/**
 * Created by lqf on 2018/6/1.
 */

@SuppressLint("AppCompatCustomView")
public class AudioRecordCustormButton extends Button {
    private Context mContext;
    public AudioRecordCustormButton(Context context) {
        this(context, null);
    }

    public AudioRecordCustormButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext=context;
    }

}
