package com.tiny.mvp.module.video.list;

import com.tiny.mvp.api.RetrofitService;
import com.tiny.mvp.local.table.VideoInfo;
import com.tiny.mvp.module.base.IBasePresenter;
import com.tiny.mvp.module.base.ILoadDataView;
import com.tiny.mvp.widget.EmptyLayout;
import com.orhanobut.logger.Logger;

import java.util.List;

import rx.Subscriber;
import rx.functions.Action0;

/**
 *
 */

public class VideoListPresenter implements IBasePresenter {

    final private ILoadDataView mView;
    final private String mVideoId;

    private int mPage = 0;


    public VideoListPresenter(ILoadDataView view, String videoId) {
        this.mView = view;
        this.mVideoId = videoId;
    }


    @Override
    public void getData() {
        RetrofitService.getVideoList(mVideoId, mPage)
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        mView.showLoading();
                    }
                })
                .compose(mView.<List<VideoInfo>>bindToLife())
                .subscribe(new Subscriber<List<VideoInfo>>() {
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
                    public void onNext(List<VideoInfo> videoList) {
                        mView.loadData(videoList);
                        mPage++;
                    }
                });

    }

    @Override
    public void getMoreData() {
        RetrofitService.getVideoList(mVideoId, mPage)
                .compose(mView.<List<VideoInfo>>bindToLife())
                .subscribe(new Subscriber<List<VideoInfo>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.e(e.toString());
                        mView.loadNoData();
                    }

                    @Override
                    public void onNext(List<VideoInfo> videoList) {
                        mView.loadMoreData(videoList);
                        mPage++;
                    }
                });
    }

}
