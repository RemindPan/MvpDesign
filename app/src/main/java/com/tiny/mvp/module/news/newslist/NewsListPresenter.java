package com.tiny.mvp.module.news.newslist;

import com.tiny.mvp.adapter.item.NewsMultiItem;
import com.tiny.mvp.api.NewsUtils;
import com.tiny.mvp.api.RetrofitService;
import com.tiny.mvp.api.bean.NewsInfo;
import com.tiny.mvp.module.base.IBasePresenter;
import com.tiny.mvp.widget.EmptyLayout;
import com.orhanobut.logger.Logger;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action0;
import rx.functions.Func1;

/**
 *
 * 新闻列表 Presenter
 */
public class NewsListPresenter implements IBasePresenter {

    private INewsListView mView;
    private String mNewsId;

    private int mPage = 0;

    public NewsListPresenter(INewsListView view, String newsId) {
        this.mView = view;
        this.mNewsId = newsId;
    }

    @Override
    public void getData() {
        RetrofitService.getNewsList(mNewsId, mPage)
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        mView.showLoading();
                    }
                })
                .filter(new Func1<NewsInfo, Boolean>() {
                    @Override
                    public Boolean call(NewsInfo newsBean) {
                        if (NewsUtils.isAbNews(newsBean)) {
                            mView.loadAdData(newsBean);
                        }
                        return !NewsUtils.isAbNews(newsBean);
                    }
                })
                .compose(mTransformer)
                .subscribe(new Subscriber<List<NewsMultiItem>>() {
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
                    public void onNext(List<NewsMultiItem> newsMultiItems) {
                        mView.loadData(newsMultiItems);
                        mPage++;
                    }
                });
    }

    @Override
    public void getMoreData() {
        RetrofitService.getNewsList(mNewsId, mPage)
                .compose(mTransformer)
                .subscribe(new Subscriber<List<NewsMultiItem>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.e(e.toString());
                        mView.loadNoData();
                    }

                    @Override
                    public void onNext(List<NewsMultiItem> newsList) {
                        mView.loadMoreData(newsList);
                        mPage++;
                    }
                });
    }

    /**
     * 统一变换
     */
    private Observable.Transformer<NewsInfo, List<NewsMultiItem>> mTransformer = new Observable.Transformer<NewsInfo, List<NewsMultiItem>>() {
        @Override
        public Observable<List<NewsMultiItem>> call(Observable<NewsInfo> newsInfoObservable) {
            return newsInfoObservable
                    .map(new Func1<NewsInfo, NewsMultiItem>() {
                        @Override
                        public NewsMultiItem call(NewsInfo newsBean) {
                            if (NewsUtils.isNewsPhotoSet(newsBean.getSkipType())) {
                                return new NewsMultiItem(NewsMultiItem.ITEM_TYPE_PHOTO_SET, newsBean);
                            }
                            return new NewsMultiItem(NewsMultiItem.ITEM_TYPE_NORMAL, newsBean);
                        }
                    })
                    .toList()
                    .compose(mView.<List<NewsMultiItem>>bindToLife());
        }
    };
}
