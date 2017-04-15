package com.tiny.mvp.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.tiny.mvp.R;
import com.tiny.mvp.local.table.VideoInfo;
import com.tiny.mvp.utils.DefIconFactory;
import com.tiny.mvp.utils.ImageLoader;
import com.tiny.recycler.adapter.BaseQuickAdapter;
import com.tiny.recycler.adapter.BaseViewHolder;

/**
 *
 * Video收藏适配器
 */
public class VideoLoveAdapter extends BaseQuickAdapter<VideoInfo> {

    public VideoLoveAdapter(Context context) {
        super(context);
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.adapter_video_love;
    }

    @Override
    protected void convert(BaseViewHolder holder, final VideoInfo item) {
        ImageView ivThumb = holder.getView(R.id.iv_thumb);
        ImageLoader.loadFitCenter(mContext, item.getCover(), ivThumb, DefIconFactory.provideIcon());
        holder.setText(R.id.tv_title, item.getTitle())
                .setText(R.id.tv_desc, item.getSectiontitle());
    }
}
