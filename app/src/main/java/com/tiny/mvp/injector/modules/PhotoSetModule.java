package com.tiny.mvp.injector.modules;

import com.tiny.mvp.injector.PerActivity;
import com.tiny.mvp.module.base.IBasePresenter;
import com.tiny.mvp.module.news.photoset.PhotoSetActivity;
import com.tiny.mvp.module.news.photoset.PhotoSetPresenter;

import dagger.Module;
import dagger.Provides;

/**
 *
 * 图集 Module
 */
@Module
public class PhotoSetModule {

    private final PhotoSetActivity mView;
    private final String mPhotoSetId;

    public PhotoSetModule(PhotoSetActivity view, String photoSetId) {
        mView = view;
        mPhotoSetId = photoSetId;
    }

    @PerActivity
    @Provides
    public IBasePresenter providePhotoSetPresenter() {
        return new PhotoSetPresenter(mView, mPhotoSetId);
    }
}
