package com.tiny.mvp.injector.modules;

import com.tiny.mvp.adapter.ManageAdapter;
import com.tiny.mvp.injector.PerActivity;
import com.tiny.mvp.local.table.DaoSession;
import com.tiny.mvp.module.news.channel.ChannelActivity;
import com.tiny.mvp.module.news.channel.ChannelPresenter;
import com.tiny.mvp.module.news.channel.IChannelPresenter;
import com.tiny.mvp.rxbus.RxBus;
import com.tiny.recycler.adapter.BaseQuickAdapter;

import dagger.Module;
import dagger.Provides;

/**
 *
 * 管理
 */
@Module
public class ChannelModule {

    private final ChannelActivity mView;

    public ChannelModule(ChannelActivity view) {
        mView = view;
    }

    @Provides
    public BaseQuickAdapter provideManageAdapter() {
        return new ManageAdapter(mView);
    }

    @PerActivity
    @Provides
    public IChannelPresenter provideManagePresenter(DaoSession daoSession, RxBus rxBus) {
        return new ChannelPresenter(mView, daoSession.getNewsTypeInfoDao(), rxBus);
    }
}
