package com.tiny.mvp.module.photo.news;

import com.tiny.mvp.api.RetrofitService;
import com.tiny.mvp.api.bean.PhotoInfo;
import com.tiny.mvp.module.base.IBasePresenter;
import com.tiny.mvp.module.base.ILoadDataView;
import com.tiny.mvp.widget.EmptyLayout;
import com.orhanobut.logger.Logger;

import java.util.List;

import rx.Subscriber;
import rx.functions.Action0;

/**
 *
 * 图片新闻列表 Presenter
 */
public class PhotoNewsPresenter implements IBasePresenter {

    private String mNextSetId;
    private ILoadDataView mView;


    public PhotoNewsPresenter(ILoadDataView view) {
        this.mView = view;
    }


    @Override
    public void getData() {
        RetrofitService.getPhotoList()
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        mView.showLoading();
                    }
                })
                .compose(mView.<List<PhotoInfo>>bindToLife())
                .subscribe(new Subscriber<List<PhotoInfo>>() {
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
                    public void onNext(List<PhotoInfo> photoList) {
                        mView.loadData(photoList);
                        mNextSetId = photoList.get(photoList.size() - 1).getSetid();
                    }
                });
    }

    @Override
    public void getMoreData() {
        RetrofitService.getPhotoMoreList(mNextSetId)
                .compose(mView.<List<PhotoInfo>>bindToLife())
                .subscribe(new Subscriber<List<PhotoInfo>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.loadNoData();
                    }

                    @Override
                    public void onNext(List<PhotoInfo> photoList) {
                        mView.loadMoreData(photoList);
                    }
                });
    }
}
