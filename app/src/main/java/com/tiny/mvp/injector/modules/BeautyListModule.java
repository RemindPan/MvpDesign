package com.tiny.mvp.injector.modules;

import com.tiny.mvp.adapter.BeautyPhotosAdapter;
import com.tiny.mvp.injector.PerFragment;
import com.tiny.mvp.module.base.IBasePresenter;
import com.tiny.mvp.module.photo.beauty.BeautyListFragment;
import com.tiny.mvp.module.photo.beauty.BeautyListPresenter;
import com.tiny.recycler.adapter.BaseQuickAdapter;

import dagger.Module;
import dagger.Provides;

/**
 *
 * 美图 Module
 */
@Module
public class BeautyListModule {

    private final BeautyListFragment mView;

    public BeautyListModule(BeautyListFragment view) {
        this.mView = view;
    }

    @PerFragment
    @Provides
    public IBasePresenter providePresenter() {
        return new BeautyListPresenter(mView);
    }

    @PerFragment
    @Provides
    public BaseQuickAdapter provideAdapter() {
        return new BeautyPhotosAdapter(mView.getContext());
    }
}
