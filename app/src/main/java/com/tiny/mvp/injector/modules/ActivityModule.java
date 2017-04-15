package com.tiny.mvp.injector.modules;

import android.app.Activity;

import com.tiny.mvp.injector.PerActivity;

import dagger.Module;
import dagger.Provides;

/**
 *
 * Activity Module
 */
@Module
public class ActivityModule {

    private final Activity mActivity;

    public ActivityModule(Activity activity) {
        mActivity = activity;
    }

    @PerActivity
    @Provides
    Activity getActivity() {
        return mActivity;
    }
}
