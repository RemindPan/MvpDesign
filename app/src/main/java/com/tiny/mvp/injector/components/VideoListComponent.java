package com.tiny.mvp.injector.components;

import com.tiny.mvp.injector.PerFragment;
import com.tiny.mvp.injector.modules.VideoListModule;
import com.tiny.mvp.module.video.list.VideoListFragment;

import dagger.Component;

/**
 *
 */
@PerFragment
@Component(dependencies = ApplicationComponent.class, modules = VideoListModule.class)
public interface VideoListComponent {
    void inject(VideoListFragment fragment);
}
