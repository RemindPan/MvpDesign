package com.tiny.mvp.injector.modules;

import android.content.Context;

import com.tiny.mvp.AndroidApplication;
import com.tiny.mvp.local.table.DaoSession;
import com.tiny.mvp.rxbus.RxBus;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 *
 * Application Module
 */
@Module
public class ApplicationModule {

    private final AndroidApplication mApplication;
    private final DaoSession mDaoSession;
    private final RxBus mRxBus;

    public ApplicationModule(AndroidApplication application, DaoSession daoSession, RxBus rxBus) {
        mApplication = application;
        mDaoSession = daoSession;
        mRxBus = rxBus;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return mApplication.getApplication();
    }

    @Provides
    @Singleton
    RxBus provideRxBus() {
        return mRxBus;
    }

    @Provides
    @Singleton
    DaoSession provideDaoSession() {
        return mDaoSession;
    }
}
