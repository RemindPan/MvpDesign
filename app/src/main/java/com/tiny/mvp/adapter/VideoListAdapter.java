package com.tiny.mvp.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.tiny.mvp.R;
import com.tiny.mvp.local.table.VideoInfo;
import com.tiny.mvp.module.video.player.VideoPlayerActivity;
import com.tiny.mvp.utils.DefIconFactory;
import com.tiny.mvp.utils.ImageLoader;
import com.tiny.recycler.adapter.BaseQuickAdapter;
import com.tiny.recycler.adapter.BaseViewHolder;

import java.util.List;

/**
 *
 */

public class VideoListAdapter extends BaseQuickAdapter<VideoInfo> {

    public VideoListAdapter(Context context) {
        super(context);
    }

    public VideoListAdapter(Context context, List<VideoInfo> data) {
        super(context, data);
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.adapter_video_list;
    }

    @Override
    protected void convert(final BaseViewHolder holder, final VideoInfo item) {
        final ImageView ivPhoto = holder.getView(R.id.iv_photo);
        ImageLoader.loadFitCenter(mContext, item.getCover(), ivPhoto, DefIconFactory.provideIcon());
        holder.setText(R.id.tv_title, item.getTitle());
        holder.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VideoPlayerActivity.launch(mContext, item);
            }
        });
    }

}
