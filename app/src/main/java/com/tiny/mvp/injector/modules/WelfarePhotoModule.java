package com.tiny.mvp.injector.modules;

import com.tiny.mvp.adapter.WelfarePhotoAdapter;
import com.tiny.mvp.injector.PerFragment;
import com.tiny.mvp.module.base.IBasePresenter;
import com.tiny.mvp.module.photo.welfare.WelfareListFragment;
import com.tiny.mvp.module.photo.welfare.WelfareListPresenter;
import com.tiny.recycler.adapter.BaseQuickAdapter;

import dagger.Module;
import dagger.Provides;

/**
 *
 * 福利图片界面 Module
 */
@Module
public class WelfarePhotoModule {

    private final WelfareListFragment mView;

    public WelfarePhotoModule(WelfareListFragment view) {
        this.mView = view;
    }

    @PerFragment
    @Provides
    public IBasePresenter providePresenter() {
        return new WelfareListPresenter(mView);
    }

    @PerFragment
    @Provides
    public BaseQuickAdapter provideAdapter() {
        return new WelfarePhotoAdapter(mView.getContext());
    }
}
