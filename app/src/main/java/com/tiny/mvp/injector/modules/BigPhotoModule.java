package com.tiny.mvp.injector.modules;

import com.tiny.mvp.adapter.PhotoPagerAdapter;
import com.tiny.mvp.injector.PerActivity;
import com.tiny.mvp.local.table.BeautyPhotoInfo;
import com.tiny.mvp.local.table.DaoSession;
import com.tiny.mvp.module.base.ILocalPresenter;
import com.tiny.mvp.module.photo.bigphoto.BigPhotoActivity;
import com.tiny.mvp.module.photo.bigphoto.BigPhotoPresenter;
import com.tiny.mvp.rxbus.RxBus;

import java.util.List;

import dagger.Module;
import dagger.Provides;

/**
 *
 * 大图 Module
 */
@PerActivity
@Module
public class BigPhotoModule {

    private final BigPhotoActivity mView;
    private List<BeautyPhotoInfo> mPhotoList;

    public BigPhotoModule(BigPhotoActivity view, List<BeautyPhotoInfo> photoList) {
        this.mView = view;
        this.mPhotoList = photoList;
    }

    @PerActivity
    @Provides
    public ILocalPresenter providePresenter(DaoSession daoSession, RxBus rxBus) {
        return new BigPhotoPresenter(mView, daoSession.getBeautyPhotoInfoDao(), mPhotoList, rxBus);
    }

    @PerActivity
    @Provides
    public PhotoPagerAdapter provideAdapter() {
        return new PhotoPagerAdapter(mView);
    }

}
