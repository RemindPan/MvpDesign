package com.tiny.mvp.injector.modules;

import com.tiny.mvp.adapter.VideoLoveAdapter;
import com.tiny.mvp.injector.PerFragment;
import com.tiny.mvp.local.table.DaoSession;
import com.tiny.mvp.module.base.ILocalPresenter;
import com.tiny.mvp.module.manage.love.video.LoveVideoFragment;
import com.tiny.mvp.module.manage.love.video.LoveVideoPresenter;
import com.tiny.mvp.rxbus.RxBus;
import com.tiny.recycler.adapter.BaseQuickAdapter;

import dagger.Module;
import dagger.Provides;

/**
 *
 * Video收藏
 */
@Module
public class LoveVideoModule {

    private final LoveVideoFragment mView;

    public LoveVideoModule(LoveVideoFragment view) {
        this.mView = view;
    }

    @PerFragment
    @Provides
    public ILocalPresenter providePresenter(DaoSession daoSession, RxBus rxBus) {
        return new LoveVideoPresenter(mView, daoSession.getVideoInfoDao(), rxBus);
    }

    @PerFragment
    @Provides
    public BaseQuickAdapter provideAdapter() {
        return new VideoLoveAdapter(mView.getContext());
    }
}
