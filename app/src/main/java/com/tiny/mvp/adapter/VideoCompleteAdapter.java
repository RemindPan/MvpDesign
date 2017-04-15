package com.tiny.mvp.adapter;

import android.content.Context;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;

import com.tiny.mvp.R;
import com.tiny.mvp.local.table.VideoInfo;
import com.tiny.mvp.module.video.fullscreen.VideoFullscreenActivity;
import com.tiny.mvp.module.video.player.VideoPlayerActivity;
import com.tiny.mvp.rxbus.RxBus;
import com.tiny.mvp.utils.DefIconFactory;
import com.tiny.mvp.utils.ImageLoader;
import com.tiny.mvp.utils.StringUtils;
import com.tiny.recycler.adapter.BaseViewHolder;

/**
 *
 * video 缓存完成适配器
 */
public class VideoCompleteAdapter extends BaseVideoDLAdapter {

    public VideoCompleteAdapter(Context context, RxBus rxBus) {
        super(context, rxBus);
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.adapter_video_complete;
    }

    @Override
    protected void convert(final BaseViewHolder holder, final VideoInfo item) {
        ImageView ivThumb = holder.getView(R.id.iv_thumb);
        ImageLoader.loadFitCenter(mContext, item.getCover(), ivThumb, DefIconFactory.provideIcon());
        holder.setText(R.id.tv_size, StringUtils.convertStorageNoB(item.getTotalSize()))
                .setText(R.id.tv_title, item.getTitle());
        // 根据是否为编辑模式来进行处理
        final CheckBox cbDelete = holder.getView(R.id.cb_delete);
        if (mIsEditMode) {
            cbDelete.setVisibility(View.VISIBLE);
            cbDelete.setChecked(mSparseItemChecked.get(holder.getAdapterPosition()));
        } else {
            cbDelete.setVisibility(View.GONE);
            cbDelete.setChecked(false);
        }
        cbDelete.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                _handleCheckedChanged(holder.getAdapterPosition(), isChecked);
            }
        });
        holder.getView(R.id.tv_show_detail).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mIsEditMode) {
                    cbDelete.setChecked(!cbDelete.isChecked());
                } else {
                    VideoPlayerActivity.launch(mContext, item);
                }
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mIsEditMode) {
                    cbDelete.setChecked(!cbDelete.isChecked());
                } else {
                    VideoFullscreenActivity.launch(mContext, item);
                }
            }
        });
    }
}
