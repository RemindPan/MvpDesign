package com.tiny.mvp.injector.modules;

import com.tiny.mvp.adapter.ViewPagerAdapter;
import com.tiny.mvp.injector.PerFragment;
import com.tiny.mvp.local.table.DaoSession;
import com.tiny.mvp.module.base.IRxBusPresenter;
import com.tiny.mvp.module.photo.main.PhotoMainFragment;
import com.tiny.mvp.module.photo.main.PhotoMainPresenter;
import com.tiny.mvp.rxbus.RxBus;

import dagger.Module;
import dagger.Provides;

/**
 *
 * 图片主界面 Module
 */
@Module
public class PhotoMainModule {

    private final PhotoMainFragment mView;

    public PhotoMainModule(PhotoMainFragment view) {
        mView = view;
    }

    @PerFragment
    @Provides
    public ViewPagerAdapter provideViewPagerAdapter() {
        return new ViewPagerAdapter(mView.getChildFragmentManager());
    }

    @PerFragment
    @Provides
    public IRxBusPresenter providePhotosPresenter(DaoSession daoSession, RxBus rxBus) {
        return new PhotoMainPresenter(mView, daoSession.getBeautyPhotoInfoDao(), rxBus);
    }
}
