package com.tiny.mvp.injector.components;

import com.tiny.mvp.injector.PerActivity;
import com.tiny.mvp.injector.modules.ChannelModule;
import com.tiny.mvp.module.news.channel.ChannelActivity;

import dagger.Component;

/**
 *
 * 管理 Component
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ChannelModule.class)
public interface ManageComponent {
    void inject(ChannelActivity activity);
}
