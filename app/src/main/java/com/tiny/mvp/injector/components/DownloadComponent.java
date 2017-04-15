package com.tiny.mvp.injector.components;

import com.tiny.mvp.injector.PerActivity;
import com.tiny.mvp.injector.modules.DownloadModule;
import com.tiny.mvp.module.manage.download.DownloadActivity;

import dagger.Component;

/**
 *
 * video下载 Component
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = DownloadModule.class)
public interface DownloadComponent {
    void inject(DownloadActivity activity);
}
