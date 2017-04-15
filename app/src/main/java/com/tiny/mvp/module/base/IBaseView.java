package com.tiny.mvp.module.base;

import com.tiny.mvp.widget.EmptyLayout;
import com.trello.rxlifecycle.LifecycleTransformer;

/**
 *
 * 基础 BaseView 接口
 */
public interface IBaseView {

    /**
     * 显示加载动画
     */
    void showLoading();

    /**
     * 隐藏加载
     */
    void hideLoading();

    /**
     * 显示网络错误
     * @param onRetryListener 点击监听
     */
    void showNetError(EmptyLayout.OnRetryListener onRetryListener);

    /**
     * 绑定生命周期
     * @param <T>
     * @return
     */
    <T> LifecycleTransformer<T> bindToLife();
}
