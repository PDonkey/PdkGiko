package com.pdk.pdkgiko.adapter;

import android.content.Context;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.pdk.pdkgiko.R;
import com.pdk.pdkgiko.bean.CategoryResult;
import com.pdk.pdkgiko.utils.Utils;

import java.util.List;

/**
 * Created by uatql90533 on 2018/4/10.
 */
public class CategoryAdapter extends BaseQuickAdapter<CategoryResult.ResultsBean, BaseViewHolder> {
    private List<CategoryResult.ResultsBean> resultList;
    private Context mContext;

    public CategoryAdapter(Context context, @Nullable List<CategoryResult.ResultsBean> data) {
        super(R.layout.item_category, data);
        this.mContext = context;
        this.resultList=data;
    }

    @Override
    protected void convert(BaseViewHolder viewHolder, CategoryResult.ResultsBean item) {
        if (item != null) {
            viewHolder.setText(R.id.category_item_desc, item.desc == null ? "unknown" : item.desc)
                    .setText(R.id.category_item_author, item.who == null ? "unknown" : item.who)
                    .setText(R.id.category_item_time, Utils.dateFormat(item.publishedAt))
                    .setText(R.id.category_item_src, item.source == null ? "unknown" : item.source);
//            Glide.with(mContext).load(item.images.get(0) + "?imageView2/0/w/190")
//                    .placeholder(R.mipmap.image_default)
//                    .error(R.mipmap.image_default)
//                    .into((ImageView) viewHolder.getView(R.id.category_item_img));

        }
    }
}
