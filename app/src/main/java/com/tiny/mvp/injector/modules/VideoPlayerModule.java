package com.tiny.mvp.injector.modules;

import com.tiny.mvp.injector.PerActivity;
import com.tiny.mvp.local.table.DaoSession;
import com.tiny.mvp.local.table.VideoInfo;
import com.tiny.mvp.module.video.player.IVideoPresenter;
import com.tiny.mvp.module.video.player.VideoPlayerActivity;
import com.tiny.mvp.module.video.player.VideoPlayerPresenter;
import com.tiny.mvp.rxbus.RxBus;

import dagger.Module;
import dagger.Provides;

/**
 *
 * Video Module
 */
@Module
public class VideoPlayerModule {

    private final VideoPlayerActivity mView;
    private final VideoInfo mVideoData;

    public VideoPlayerModule(VideoPlayerActivity view, VideoInfo videoData) {
        this.mView = view;
        this.mVideoData = videoData;
    }

    @PerActivity
    @Provides
    public IVideoPresenter providePresenter(DaoSession daoSession, RxBus rxBus) {
        return new VideoPlayerPresenter(mView, daoSession.getVideoInfoDao(), rxBus, mVideoData, daoSession.getDanmakuInfoDao());
    }

}
