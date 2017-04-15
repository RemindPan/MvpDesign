package com.tiny.mvp.injector.modules;

import com.tiny.mvp.adapter.BaseVideoDLAdapter;
import com.tiny.mvp.adapter.VideoCacheAdapter;
import com.tiny.mvp.injector.PerFragment;
import com.tiny.mvp.local.table.DaoSession;
import com.tiny.mvp.module.base.IRxBusPresenter;
import com.tiny.mvp.module.manage.download.cache.VideoCacheFragment;
import com.tiny.mvp.module.manage.download.cache.VideoCachePresenter;
import com.tiny.mvp.rxbus.RxBus;

import dagger.Module;
import dagger.Provides;

/**
 *
 * video缓存Module
 */
@Module
public class VideoCacheModule {

    private final VideoCacheFragment mView;

    public VideoCacheModule(VideoCacheFragment view) {
        this.mView = view;
    }

    @PerFragment
    @Provides
    public IRxBusPresenter providePresenter(DaoSession daoSession, RxBus rxBus) {
        return new VideoCachePresenter(mView, daoSession.getVideoInfoDao(), rxBus);
    }

    @PerFragment
    @Provides
    public BaseVideoDLAdapter provideAdapter(RxBus rxBus) {
        return new VideoCacheAdapter(mView.getContext(), rxBus);
    }
}
