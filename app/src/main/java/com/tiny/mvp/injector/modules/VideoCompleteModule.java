package com.tiny.mvp.injector.modules;

import com.tiny.mvp.adapter.BaseVideoDLAdapter;
import com.tiny.mvp.adapter.VideoCompleteAdapter;
import com.tiny.mvp.injector.PerFragment;
import com.tiny.mvp.local.table.DaoSession;
import com.tiny.mvp.module.base.IRxBusPresenter;
import com.tiny.mvp.module.manage.download.complete.VideoCompleteFragment;
import com.tiny.mvp.module.manage.download.complete.VideoCompletePresenter;
import com.tiny.mvp.rxbus.RxBus;

import dagger.Module;
import dagger.Provides;

/**
 *
 * video 缓存完成 Module
 */
@Module
public class VideoCompleteModule {

    private final VideoCompleteFragment mView;

    public VideoCompleteModule(VideoCompleteFragment view) {
        this.mView = view;
    }

    @PerFragment
    @Provides
    public IRxBusPresenter providePresenter(DaoSession daoSession, RxBus rxBus) {
        return new VideoCompletePresenter(mView, daoSession.getVideoInfoDao(), rxBus);
    }

    @PerFragment
    @Provides
    public BaseVideoDLAdapter provideAdapter(RxBus rxBus) {
        return new VideoCompleteAdapter(mView.getContext(), rxBus);
    }
}
