package com.tiny.mvp.module.news.channel;

import com.tiny.mvp.module.base.ILocalPresenter;

/**
 *
 * 频道 Presenter 接口
 */
public interface IChannelPresenter<T> extends ILocalPresenter<T> {

    /**
     * 交换
     * @param fromPos
     * @param toPos
     */
    void swap(int fromPos, int toPos);
}
