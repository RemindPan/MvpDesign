package com.tiny.mvp.injector.modules;

import com.tiny.mvp.injector.PerActivity;
import com.tiny.mvp.module.base.IBasePresenter;
import com.tiny.mvp.module.news.article.NewsArticleActivity;
import com.tiny.mvp.module.news.article.NewsArticlePresenter;

import dagger.Module;
import dagger.Provides;

/**
 *
 * 新闻详情 Module
 */
@Module
public class NewsArticleModule {

    private final String mNewsId;
    private final NewsArticleActivity mView;

    public NewsArticleModule(NewsArticleActivity view, String newsId) {
        mNewsId = newsId;
        mView = view;
    }

    @PerActivity
    @Provides
    public IBasePresenter providePresenter() {
        return new NewsArticlePresenter(mNewsId, mView);
    }

}
