package com.tiny.mvp.module.news.channel;

import com.tiny.mvp.local.table.NewsTypeInfo;

import java.util.List;

/**
 *
 * 栏目管理接口
 */
public interface IChannelView {

    /**
     * 显示数据
     * @param checkList     选中栏目
     * @param uncheckList   未选中栏目
     */
    void loadData(List<NewsTypeInfo> checkList, List<NewsTypeInfo> uncheckList);
}
