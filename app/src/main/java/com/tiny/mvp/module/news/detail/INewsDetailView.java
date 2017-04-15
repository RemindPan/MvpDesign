package com.tiny.mvp.module.news.detail;

import com.tiny.mvp.api.bean.NewsDetailInfo;
import com.tiny.mvp.module.base.IBaseView;

/**
 *
 * 新闻详情接口
 */
@Deprecated
public interface INewsDetailView extends IBaseView {

    /**
     * 显示数据
     * @param newsDetailBean 新闻详情
     */
    void loadData(NewsDetailInfo newsDetailBean);
}
