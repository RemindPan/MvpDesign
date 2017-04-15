package com.tiny.mvp.injector.modules;

import com.tiny.mvp.adapter.PhotoListAdapter;
import com.tiny.mvp.injector.PerFragment;
import com.tiny.mvp.module.base.IBasePresenter;
import com.tiny.mvp.module.photo.news.PhotoNewsFragment;
import com.tiny.mvp.module.photo.news.PhotoNewsPresenter;
import com.tiny.recycler.adapter.BaseQuickAdapter;

import dagger.Module;
import dagger.Provides;

/**
 *
 * 图片新闻列表 Module
 */
@Module
public class PhotoNewsModule {

    private final PhotoNewsFragment mView;

    public PhotoNewsModule(PhotoNewsFragment view) {
        this.mView = view;
    }

    @PerFragment
    @Provides
    public IBasePresenter providePresenter() {
        return new PhotoNewsPresenter(mView);
    }

    @PerFragment
    @Provides
    public BaseQuickAdapter provideAdapter() {
        return new PhotoListAdapter(mView.getContext());
    }
}
