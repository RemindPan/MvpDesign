package com.tiny.mvp.injector.components;

import com.tiny.mvp.injector.PerFragment;
import com.tiny.mvp.injector.modules.VideoMainModule;
import com.tiny.mvp.module.video.main.VideoMainFragment;

import dagger.Component;

/**
 *
 * 视频主界面 Component
 */
@PerFragment
@Component(dependencies = ApplicationComponent.class, modules = VideoMainModule.class)
public interface VideoMainComponent {
    void inject(VideoMainFragment fragment);
}
