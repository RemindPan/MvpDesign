package com.tiny.mvp.injector.components;

import com.tiny.mvp.injector.PerActivity;
import com.tiny.mvp.injector.modules.SpecialModule;
import com.tiny.mvp.module.news.special.SpecialActivity;

import dagger.Component;

/**
 *
 * 专题 Component
 */
@PerActivity
@Component(modules = SpecialModule.class)
public interface SpecialComponent {
    void inject(SpecialActivity activity);
}
