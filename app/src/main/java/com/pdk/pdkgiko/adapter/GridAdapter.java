package com.pdk.pdkgiko.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.pdk.pdkgiko.R;

import java.util.List;

/**
 * Created by Administrator on 2018/6/8.
 */

public class GridAdapter extends BaseAdapter {
    private Context mContext;
    private List<String> stringList;
    private boolean isShowIv_;

    public GridAdapter(Context context, List<String> list) {
        this.mContext = context;
        this.stringList = list;
    }

    @Override
    public int getCount() {
        return stringList.size();
    }

    @Override
    public Object getItem(int position) {
        return stringList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = View.inflate(mContext, R.layout.item_grid, null);
            viewHolder.iv_content = (ImageView) convertView.findViewById(R.id.iv_content);
            viewHolder.iv_ = (ImageView) convertView.findViewById(R.id.iv_);
            viewHolder.tv_content = (TextView) convertView.findViewById(R.id.tv_content);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tv_content.setText(stringList.get(position));
        viewHolder.iv_content.setBackgroundResource(R.mipmap.ic_launcher);
        viewHolder.iv_.setVisibility(View.VISIBLE);
        viewHolder.iv_.setImageResource(R.mipmap.ic_remove);
        viewHolder.iv_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stringList.remove(position);
                notifyDataSetChanged();
            }
        });

        return convertView;
    }

    static class ViewHolder {
        ImageView iv_content, iv_;
        TextView tv_content;

    }

    public void setIsShowIv_(boolean isShowIv) {
        this.isShowIv_ = isShowIv;
        notifyDataSetChanged();
    }
}
