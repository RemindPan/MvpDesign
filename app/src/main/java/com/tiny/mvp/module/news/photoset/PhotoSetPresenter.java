package com.tiny.mvp.module.news.photoset;

import com.tiny.mvp.api.RetrofitService;
import com.tiny.mvp.api.bean.PhotoSetInfo;
import com.tiny.mvp.module.base.IBasePresenter;
import com.tiny.mvp.widget.EmptyLayout;
import com.orhanobut.logger.Logger;

import rx.Subscriber;
import rx.functions.Action0;

/**
 *
 * 图集 Presenter
 */
public class PhotoSetPresenter implements IBasePresenter {

    private final IPhotoSetView mView;
    private final String mPhotoSetId;

    public PhotoSetPresenter(IPhotoSetView view, String photoSetId) {
        mView = view;
        mPhotoSetId = photoSetId;
    }

    @Override
    public void getData() {
        RetrofitService.getPhotoSet(mPhotoSetId)
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        mView.showLoading();
                    }
                })
                .compose(mView.<PhotoSetInfo>bindToLife())
                .subscribe(new Subscriber<PhotoSetInfo>() {
                    @Override
                    public void onCompleted() {
                        mView.hideLoading();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.e(e.toString());
                        mView.showNetError(new EmptyLayout.OnRetryListener() {
                            @Override
                            public void onRetry() {
                                getData();
                            }
                        });
                    }

                    @Override
                    public void onNext(PhotoSetInfo photoSetBean) {
                        mView.loadData(photoSetBean);
                    }
                });
    }

    @Override
    public void getMoreData() {
    }
}
