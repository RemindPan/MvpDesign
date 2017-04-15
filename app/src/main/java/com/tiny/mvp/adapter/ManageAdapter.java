package com.tiny.mvp.adapter;

import android.content.Context;

import com.tiny.mvp.R;
import com.tiny.mvp.local.table.NewsTypeInfo;
import com.tiny.recycler.adapter.BaseQuickAdapter;
import com.tiny.recycler.adapter.BaseViewHolder;

import java.util.List;

/**
 * 管理界面适配器
 */
public class ManageAdapter extends BaseQuickAdapter<NewsTypeInfo> {

    public ManageAdapter(Context context) {
        super(context);
    }

    public ManageAdapter(Context context, List<NewsTypeInfo> data) {
        super(context, data);
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.adapter_manage;
    }

    @Override
    protected void convert(BaseViewHolder holder, NewsTypeInfo item) {
        holder.setText(R.id.tv_channel_name, item.getName());
    }
}
