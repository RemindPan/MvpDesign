package com.tiny.mvp.injector.modules;

import com.tiny.mvp.adapter.ViewPagerAdapter;
import com.tiny.mvp.injector.PerActivity;
import com.tiny.mvp.module.base.IRxBusPresenter;
import com.tiny.mvp.module.manage.download.DownloadActivity;
import com.tiny.mvp.module.manage.download.DownloadPresenter;
import com.tiny.mvp.rxbus.RxBus;

import dagger.Module;
import dagger.Provides;

/**
 *
 * video下载Module
 */
@Module
public class DownloadModule {

    private final DownloadActivity mView;

    public DownloadModule(DownloadActivity view) {
        mView = view;
    }

    @PerActivity
    @Provides
    public ViewPagerAdapter provideViewPagerAdapter() {
        return new ViewPagerAdapter(mView.getSupportFragmentManager());
    }

    @PerActivity
    @Provides
    public IRxBusPresenter provideVideosPresenter(RxBus rxBus) {
        return new DownloadPresenter(rxBus);
    }
}
