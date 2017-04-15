package com.tiny.mvp.injector.components;

import com.tiny.mvp.injector.PerActivity;
import com.tiny.mvp.injector.modules.NewsArticleModule;
import com.tiny.mvp.module.news.article.NewsArticleActivity;

import dagger.Component;

/**
 *
 * 新闻详情 Component
 */
@PerActivity
@Component(modules = NewsArticleModule.class)
public interface NewsArticleComponent {
    void inject(NewsArticleActivity activity);
}
