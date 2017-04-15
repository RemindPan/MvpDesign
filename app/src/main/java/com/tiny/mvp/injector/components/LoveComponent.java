package com.tiny.mvp.injector.components;

import com.tiny.mvp.injector.PerActivity;
import com.tiny.mvp.injector.modules.LoveModule;

import dagger.Component;

/**
 *
 * 收藏 Component
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = LoveModule.class)
public interface LoveComponent {
//    void inject(LoveActivity activity);
}
