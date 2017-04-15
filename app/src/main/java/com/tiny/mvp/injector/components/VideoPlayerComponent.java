package com.tiny.mvp.injector.components;

import com.tiny.mvp.injector.PerActivity;
import com.tiny.mvp.injector.modules.VideoPlayerModule;
import com.tiny.mvp.module.video.player.VideoPlayerActivity;

import dagger.Component;

/**
 *
 * Video Component
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = VideoPlayerModule.class)
public interface VideoPlayerComponent {
    void inject(VideoPlayerActivity activity);
}
