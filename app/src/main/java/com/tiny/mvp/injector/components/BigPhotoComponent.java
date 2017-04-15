package com.tiny.mvp.injector.components;

import com.tiny.mvp.injector.PerActivity;
import com.tiny.mvp.injector.modules.BigPhotoModule;
import com.tiny.mvp.module.photo.bigphoto.BigPhotoActivity;

import dagger.Component;

/**
 *
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = BigPhotoModule.class)
public interface BigPhotoComponent {
    void inject(BigPhotoActivity activity);
}
