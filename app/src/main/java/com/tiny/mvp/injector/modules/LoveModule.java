package com.tiny.mvp.injector.modules;

import com.tiny.mvp.adapter.ViewPagerAdapter;
import com.tiny.mvp.injector.PerActivity;
import com.tiny.mvp.module.manage.love.LoveActivity;

import dagger.Module;
import dagger.Provides;

/**
 * 收藏 Module
 */
@Module
public class LoveModule {

    private final LoveActivity mView;

    public LoveModule(LoveActivity view) {
        this.mView = view;
    }

    @PerActivity
    @Provides
    public ViewPagerAdapter provideViewPagerAdapter() {
        return new ViewPagerAdapter(mView.getSupportFragmentManager());
    }

}
