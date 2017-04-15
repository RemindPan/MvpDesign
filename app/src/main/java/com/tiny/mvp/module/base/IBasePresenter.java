package com.tiny.mvp.module.base;

/**
 *
 * 基础 Presenter
 */
public interface IBasePresenter {

    /**
     * 获取网络数据，更新界面
     */
    void getData();

    /**
     * 加载更多数据
     */
    void getMoreData();
}
