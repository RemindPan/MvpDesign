package com.tiny.mvp.injector.components;

import com.tiny.mvp.injector.PerFragment;
import com.tiny.mvp.injector.modules.LovePhotoModule;
import com.tiny.mvp.module.manage.love.photo.LovePhotoFragment;

import dagger.Component;

/**
 *
 * 图片收藏界面 Component
 */
@PerFragment
@Component(dependencies = ApplicationComponent.class, modules = LovePhotoModule.class)
public interface LovePhotoComponent {
    void inject(LovePhotoFragment fragment);
}
