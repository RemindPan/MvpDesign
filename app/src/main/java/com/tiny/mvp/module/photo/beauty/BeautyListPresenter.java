package com.tiny.mvp.module.photo.beauty;

import com.tiny.mvp.api.RetrofitService;
import com.tiny.mvp.local.table.BeautyPhotoInfo;
import com.tiny.mvp.module.base.IBasePresenter;
import com.tiny.mvp.module.base.ILoadDataView;
import com.tiny.mvp.widget.EmptyLayout;
import com.orhanobut.logger.Logger;

import java.util.List;

import rx.Subscriber;
import rx.functions.Action0;

/**
 *
 * 美图 Presenter
 */
public class BeautyListPresenter implements IBasePresenter {

    private final ILoadDataView mView;

    private int mPage = 0;

    public BeautyListPresenter(ILoadDataView view) {
        this.mView = view;
    }

    @Override
    public void getData() {
        // 因为网易这个原接口参数一大堆，我只传了部分参数，返回的数据会出现图片重复的情况，请不要在意这个问题- -
        RetrofitService.getBeautyPhoto(mPage)
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        mView.showLoading();
                    }
                })
                .compose(mView.<List<BeautyPhotoInfo>>bindToLife())
                .subscribe(new Subscriber<List<BeautyPhotoInfo>>() {
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
                    public void onNext(List<BeautyPhotoInfo> photoList) {
                        mView.loadData(photoList);
                        mPage++;
                    }
                });
    }

    @Override
    public void getMoreData() {
        RetrofitService.getBeautyPhoto(mPage)
                .compose(mView.<List<BeautyPhotoInfo>>bindToLife())
                .subscribe(new Subscriber<List<BeautyPhotoInfo>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.e(e.toString());
                        mView.loadNoData();
                    }

                    @Override
                    public void onNext(List<BeautyPhotoInfo> photoList) {
                        mView.loadMoreData(photoList);
                        mPage++;
                    }
                });
    }
}
