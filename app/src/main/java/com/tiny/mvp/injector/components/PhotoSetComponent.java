package com.tiny.mvp.injector.components;

import com.tiny.mvp.injector.PerActivity;
import com.tiny.mvp.injector.modules.PhotoSetModule;
import com.tiny.mvp.module.news.photoset.PhotoSetActivity;

import dagger.Component;

/**
 *
 * 图集 Component
 */
@PerActivity
@Component(modules = PhotoSetModule.class)
public interface PhotoSetComponent {
    void inject(PhotoSetActivity activity);
}
