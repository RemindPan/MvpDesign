package com.tiny.mvp.injector.components;

import com.tiny.mvp.injector.PerFragment;
import com.tiny.mvp.injector.modules.LoveVideoModule;
import com.tiny.mvp.module.manage.love.video.LoveVideoFragment;

import dagger.Component;

/**
 *
 * Video收藏 Component
 */
@PerFragment
@Component(dependencies = ApplicationComponent.class, modules = LoveVideoModule.class)
public interface LoveVideoComponent {
    void inject(LoveVideoFragment fragment);
}
