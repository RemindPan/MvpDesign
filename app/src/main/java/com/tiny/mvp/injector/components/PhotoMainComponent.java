package com.tiny.mvp.injector.components;

import com.tiny.mvp.injector.PerFragment;
import com.tiny.mvp.injector.modules.PhotoMainModule;
import com.tiny.mvp.module.photo.main.PhotoMainFragment;

import dagger.Component;

/**
 *
 * 图片 Component
 */
@PerFragment
@Component(dependencies = ApplicationComponent.class, modules = PhotoMainModule.class)
public interface PhotoMainComponent {
    void inject(PhotoMainFragment fragment);
}
