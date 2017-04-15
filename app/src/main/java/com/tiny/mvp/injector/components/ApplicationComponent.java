package com.tiny.mvp.injector.components;

import android.content.Context;

import com.tiny.mvp.injector.modules.ApplicationModule;
import com.tiny.mvp.local.table.DaoSession;
import com.tiny.mvp.rxbus.RxBus;

import javax.inject.Singleton;

import dagger.Component;

/**
 *
 * Application Component
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

//    void inject(BaseActivity baseActivity);

    // provide
    Context getContext();
    RxBus getRxBus();
    DaoSession getDaoSession();
}
