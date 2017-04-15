package com.tiny.mvp.injector.modules;

import com.tiny.mvp.adapter.SpecialAdapter;
import com.tiny.mvp.injector.PerActivity;
import com.tiny.mvp.module.base.IBasePresenter;
import com.tiny.mvp.module.news.special.SpecialActivity;
import com.tiny.mvp.module.news.special.SpecialPresenter;
import com.tiny.recycler.adapter.BaseQuickAdapter;

import dagger.Module;
import dagger.Provides;

/**
 *
 * 专题 Module
 */
@Module
public class SpecialModule {

    private final SpecialActivity mView;
    private final String mSpecialId;

    public SpecialModule(SpecialActivity view, String specialId) {
        mView = view;
        mSpecialId = specialId;
    }

    @PerActivity
    @Provides
    public IBasePresenter provideSpecialPresent() {
        return new SpecialPresenter(mView, mSpecialId);
    }

    @PerActivity
    @Provides
    public BaseQuickAdapter provideSpecialAdapter() {
        return new SpecialAdapter(mView);
    }
}
