package com.tiny.mvp.injector.components;

import com.tiny.mvp.injector.PerFragment;
import com.tiny.mvp.injector.modules.VideoCacheModule;
import com.tiny.mvp.module.manage.download.cache.VideoCacheFragment;

import dagger.Component;

/**
 *
 * video缓存Component
 */
@PerFragment
@Component(dependencies = ApplicationComponent.class, modules = VideoCacheModule.class)
public interface VideoCacheComponent {
    void inject(VideoCacheFragment fragment);
}
