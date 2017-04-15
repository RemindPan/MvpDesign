package com.tiny.mvp.injector.components;

import com.tiny.mvp.injector.PerFragment;
import com.tiny.mvp.injector.modules.WelfarePhotoModule;
import com.tiny.mvp.module.photo.welfare.WelfareListFragment;

import dagger.Component;

/**
 *
 * 福利图片界面 Component
 */
@PerFragment
@Component(dependencies = ApplicationComponent.class, modules = WelfarePhotoModule.class)
public interface WelfarePhotoComponent {
    void inject(WelfareListFragment fragment);
}
