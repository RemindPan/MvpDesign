package com.tiny.mvp.injector.modules;

import com.tiny.mvp.adapter.NewsMultiListAdapter;
import com.tiny.mvp.injector.PerFragment;
import com.tiny.mvp.module.base.IBasePresenter;
import com.tiny.mvp.module.news.newslist.NewsListFragment;
import com.tiny.mvp.module.news.newslist.NewsListPresenter;
import com.tiny.recycler.adapter.BaseQuickAdapter;

import dagger.Module;
import dagger.Provides;

/**
 *
 * 新闻列表 Module
 */
@Module
public class NewsListModule {

    private final NewsListFragment mNewsListView;
    private final String mNewsId;

    public NewsListModule(NewsListFragment view, String newsId) {
        this.mNewsListView = view;
        this.mNewsId = newsId;
    }

    @PerFragment
    @Provides
    public IBasePresenter providePresenter() {
        return new NewsListPresenter(mNewsListView, mNewsId);
    }

    @PerFragment
    @Provides
    public BaseQuickAdapter provideAdapter() {
        return new NewsMultiListAdapter(mNewsListView.getContext());
    }
}
