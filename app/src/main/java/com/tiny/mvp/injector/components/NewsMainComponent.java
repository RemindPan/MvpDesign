package com.tiny.mvp.injector.components;

import com.tiny.mvp.injector.PerFragment;
import com.tiny.mvp.injector.modules.NewsMainModule;
import com.tiny.mvp.module.news.main.NewsMainFragment;

import dagger.Component;

/**
 *
 * 主页 Component
 */
@PerFragment
@Component(dependencies = ApplicationComponent.class, modules = NewsMainModule.class)
public interface NewsMainComponent {
    void inject(NewsMainFragment fragment);
}
