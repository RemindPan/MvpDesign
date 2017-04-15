package com.tiny.mvp.injector.components;

import com.tiny.mvp.injector.PerFragment;
import com.tiny.mvp.injector.modules.VideoCompleteModule;
import com.tiny.mvp.module.manage.download.complete.VideoCompleteFragment;

import dagger.Component;

/**
 *
 * video 缓存完成 Component
 */
@PerFragment
@Component(dependencies = ApplicationComponent.class, modules = VideoCompleteModule.class)
public interface VideoCompleteComponent {
    void inject(VideoCompleteFragment fragment);
}
