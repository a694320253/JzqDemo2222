package com.huitu.jzqdemo.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.huitu.jzqdemo.R;
import com.huitu.jzqdemo.bean.Bean;

import java.util.List;

/**
 * Created by Administrator on 2018/2/13.
 */

public class PullToRefreshAdapter extends BaseQuickAdapter<Bean, BaseViewHolder> {
    private Context context;

    public PullToRefreshAdapter(@LayoutRes int layoutResId, @Nullable List<Bean> data, Context context) {
        super(layoutResId, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, Bean item) {
        helper.setText(R.id.name, item.getUserRealname());
        Glide.with(context).load(item.getUserImg()).into((ImageView) helper.getView(R.id.profile_image));
    }
}
