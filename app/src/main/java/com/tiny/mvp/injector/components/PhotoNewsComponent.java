package com.tiny.mvp.injector.components;

import com.tiny.mvp.injector.PerFragment;
import com.tiny.mvp.injector.modules.PhotoNewsModule;
import com.tiny.mvp.module.photo.news.PhotoNewsFragment;

import dagger.Component;

/**
 *
 * 图片新闻列表 Component
 */
@PerFragment
@Component(dependencies = ApplicationComponent.class, modules = PhotoNewsModule.class)
public interface PhotoNewsComponent {
    void inject(PhotoNewsFragment fragment);
}
