package com.pdk.pdkgiko.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.pdk.pdkgiko.R;
import com.pdk.pdkgiko.bean.GroupAppBean;

import java.util.List;

/**
 * Created by Administrator on 2018/6/8.
 */

public class GridGroupAdapter extends BaseAdapter {
    private Context mContext;
    private List<GroupAppBean.AppBean> groupList;
    private List<GroupAppBean.AppBean> childList;
    private DataChangeListener mDataChangeListener;

    public GridGroupAdapter(Context context, List<GroupAppBean.AppBean> list, List<GroupAppBean.AppBean> childList) {
        this.mContext = context;
        this.groupList = list;
        this.childList = childList;
    }

    public void setmDataChangeListener(DataChangeListener mDataChangeListener) {

        this.mDataChangeListener = mDataChangeListener;

    }

    @Override
    public int getCount() {
        return groupList.size();
    }

    @Override
    public Object getItem(int position) {
        return groupList.get(position);
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
        viewHolder.tv_content.setText(groupList.get(position).getAppName());
        viewHolder.iv_content.setBackgroundResource(R.mipmap.ic_launcher);
        viewHolder.iv_.setVisibility(View.VISIBLE);
        ViewHolder finalViewHolder = viewHolder;
        if (groupList.get(position).isInclude()) {
            viewHolder.iv_.setImageResource(R.mipmap.ic_remove);
        } else {
            viewHolder.iv_.setImageResource(R.mipmap.ic_add);
        }
        viewHolder.iv_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDataChangeListener == null) {
                    return;
                }
                if (groupList.get(position).isInclude()) {
                    groupList.get(position).setInclude(false);
                    finalViewHolder.iv_.setImageResource(R.mipmap.ic_add);
                    mDataChangeListener.removeChildData(position, groupList.get(position));

                } else {
                    //添加到我的应用
                    groupList.get(position).setInclude(true);
                    finalViewHolder.iv_.setImageResource(R.mipmap.ic_remove);
                    mDataChangeListener.addChildData(position, groupList.get(position));

                }
            }
        });


        return convertView;
    }

    class ViewHolder {
        ImageView iv_content, iv_;
        TextView tv_content;

    }

    public interface DataChangeListener {//

        void addChildData(int position, GroupAppBean.AppBean data);

        void removeChildData(int position, GroupAppBean.AppBean data);
    }

}
