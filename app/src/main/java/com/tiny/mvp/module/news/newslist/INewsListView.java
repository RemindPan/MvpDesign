package com.tiny.mvp.module.news.newslist;

import com.tiny.mvp.api.bean.NewsInfo;
import com.tiny.mvp.adapter.item.NewsMultiItem;
import com.tiny.mvp.module.base.ILoadDataView;

import java.util.List;

/**
 *
 * 新闻列表视图接口
 */
public interface INewsListView extends ILoadDataView<List<NewsMultiItem>> {

    /**
     * 加载广告数据
     * @param newsBean 新闻
     */
    void loadAdData(NewsInfo newsBean);
}
