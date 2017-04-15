package com.tiny.mvp.injector.components;

import com.tiny.mvp.injector.PerFragment;
import com.tiny.mvp.injector.modules.BeautyListModule;
import com.tiny.mvp.module.photo.beauty.BeautyListFragment;

import dagger.Component;

/**
 * 美图 PerFragment
 */
@PerFragment
@Component(dependencies = ApplicationComponent.class, modules = BeautyListModule.class)
public interface BeautyListComponent {
    void inject(BeautyListFragment fragment);
}
