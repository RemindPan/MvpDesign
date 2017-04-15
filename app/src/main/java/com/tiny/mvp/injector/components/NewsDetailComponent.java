package com.tiny.mvp.injector.components;

import com.tiny.mvp.injector.PerActivity;
import com.tiny.mvp.injector.modules.NewsDetailModule;
import com.tiny.mvp.module.news.detail.NewsDetailActivity;

import dagger.Component;

/**
 *
 * 新闻详情 Component
 */
@Deprecated
@PerActivity
@Component(modules = NewsDetailModule.class)
public interface NewsDetailComponent {
    void inject(NewsDetailActivity activity);
}
