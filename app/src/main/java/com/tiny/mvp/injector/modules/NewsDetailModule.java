package com.tiny.mvp.injector.modules;

import com.tiny.mvp.adapter.RelatedNewsAdapter;
import com.tiny.mvp.injector.PerActivity;
import com.tiny.mvp.module.base.IBasePresenter;
import com.tiny.mvp.module.news.detail.NewsDetailActivity;
import com.tiny.mvp.module.news.detail.NewsDetailPresenter;
import com.tiny.recycler.adapter.BaseQuickAdapter;

import dagger.Module;
import dagger.Provides;

/**
 *
 * 新闻详情 Module
 */
@Deprecated
@Module
public class NewsDetailModule {

    private final String mNewsId;
    private final NewsDetailActivity mView;

    public NewsDetailModule(NewsDetailActivity view, String newsId) {
        mNewsId = newsId;
        mView = view;
    }

    @PerActivity
    @Provides
    public BaseQuickAdapter provideRelatedAdapter() {
        return new RelatedNewsAdapter(mView);
    }

    @PerActivity
    @Provides
    public IBasePresenter providePresenter() {
        return new NewsDetailPresenter(mNewsId, mView);
    }
}
