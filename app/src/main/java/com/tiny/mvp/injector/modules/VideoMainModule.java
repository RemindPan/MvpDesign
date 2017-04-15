package com.tiny.mvp.injector.modules;

import com.tiny.mvp.adapter.ViewPagerAdapter;
import com.tiny.mvp.injector.PerFragment;
import com.tiny.mvp.local.table.DaoSession;
import com.tiny.mvp.module.base.IRxBusPresenter;
import com.tiny.mvp.module.video.main.VideoMainFragment;
import com.tiny.mvp.module.video.main.VideoMainPresenter;
import com.tiny.mvp.rxbus.RxBus;

import dagger.Module;
import dagger.Provides;

/**
 *
 * 视频主界面 Module
 */
@Module
public class VideoMainModule {

    private final VideoMainFragment mView;

    public VideoMainModule(VideoMainFragment view) {
        mView = view;
    }

    @PerFragment
    @Provides
    public ViewPagerAdapter provideViewPagerAdapter() {
        return new ViewPagerAdapter(mView.getChildFragmentManager());
    }

    @PerFragment
    @Provides
    public IRxBusPresenter provideVideosPresenter(DaoSession daoSession, RxBus rxBus) {
        return new VideoMainPresenter(mView, daoSession.getVideoInfoDao(), rxBus);
    }
}
