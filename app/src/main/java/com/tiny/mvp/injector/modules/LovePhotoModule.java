package com.tiny.mvp.injector.modules;

import com.tiny.mvp.adapter.BeautyPhotosAdapter;
import com.tiny.mvp.injector.PerFragment;
import com.tiny.mvp.local.table.DaoSession;
import com.tiny.mvp.module.base.ILocalPresenter;
import com.tiny.mvp.module.manage.love.photo.LovePhotoPresenter;
import com.tiny.mvp.module.manage.love.photo.LovePhotoFragment;
import com.tiny.mvp.rxbus.RxBus;
import com.tiny.recycler.adapter.BaseQuickAdapter;

import dagger.Module;
import dagger.Provides;

/**
 *
 * 图片收藏界面 Module
 */
@Module
public class LovePhotoModule {

    private final LovePhotoFragment mView;

    public LovePhotoModule(LovePhotoFragment view) {
        this.mView = view;
    }

    @PerFragment
    @Provides
    public ILocalPresenter providePresenter(DaoSession daoSession, RxBus rxBus) {
        return new LovePhotoPresenter(mView, daoSession.getBeautyPhotoInfoDao(), rxBus);
    }

    @PerFragment
    @Provides
    public BaseQuickAdapter provideAdapter() {
        return new BeautyPhotosAdapter(mView);
    }
}
