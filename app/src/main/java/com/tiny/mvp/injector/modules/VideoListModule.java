package com.tiny.mvp.injector.modules;

import com.tiny.mvp.adapter.VideoListAdapter;
import com.tiny.mvp.injector.PerFragment;
import com.tiny.mvp.module.base.IBasePresenter;
import com.tiny.mvp.module.video.list.VideoListFragment;
import com.tiny.mvp.module.video.list.VideoListPresenter;
import com.tiny.recycler.adapter.BaseQuickAdapter;

import dagger.Module;
import dagger.Provides;

/**
 *
 * video列表
 */
@Module
public class VideoListModule {

    private final VideoListFragment mView;
    private final String mVideoId;

    public VideoListModule(VideoListFragment view, String videoId) {
        this.mView = view;
        this.mVideoId = videoId;
    }

    @PerFragment
    @Provides
    public IBasePresenter providePresenter() {
        return new VideoListPresenter(mView, mVideoId);
    }

    @PerFragment
    @Provides
    public BaseQuickAdapter provideAdapter() {
        return new VideoListAdapter(mView.getContext());
    }
}
