package com.tiny.mvp.injector.components;

import com.tiny.mvp.injector.PerFragment;
import com.tiny.mvp.injector.modules.NewsListModule;
import com.tiny.mvp.module.news.newslist.NewsListFragment;

import dagger.Component;

/**
 *
 * 新闻列表 Component
 */
@PerFragment
@Component(dependencies = ApplicationComponent.class, modules = NewsListModule.class)
public interface NewsListComponent {
    void inject(NewsListFragment fragment);
}
