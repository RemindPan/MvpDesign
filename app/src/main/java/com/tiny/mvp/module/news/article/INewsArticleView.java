package com.tiny.mvp.module.news.article;

import com.tiny.mvp.api.bean.NewsDetailInfo;
import com.tiny.mvp.module.base.IBaseView;

/**
 *
 * 新闻详情接口
 */
public interface INewsArticleView extends IBaseView {

    /**
     * 显示数据
     * @param newsDetailBean 新闻详情
     */
    void loadData(NewsDetailInfo newsDetailBean);
}


