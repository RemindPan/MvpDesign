package com.tiny.mvp.injector.components;

import android.app.Activity;

import com.tiny.mvp.injector.PerActivity;
import com.tiny.mvp.injector.modules.ActivityModule;

import dagger.Component;

/**
 *
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    Activity getActivity();
}
