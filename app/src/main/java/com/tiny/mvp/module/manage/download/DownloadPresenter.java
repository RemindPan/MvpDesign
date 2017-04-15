package com.tiny.mvp.module.manage.download;

import com.tiny.mvp.module.base.IRxBusPresenter;
import com.tiny.mvp.rxbus.RxBus;
import com.orhanobut.logger.Logger;

import rx.Subscription;
import rx.functions.Action1;

/**
 *
 * video下载Presenter
 */
public class DownloadPresenter implements IRxBusPresenter {

    private final RxBus mRxBus;

    public DownloadPresenter(RxBus rxBus) {
        mRxBus = rxBus;
    }

    @Override
    public void getData() {
    }

    @Override
    public void getMoreData() {
    }

    @Override
    public <T> void registerRxBus(Class<T> eventType, Action1<T> action) {
        Subscription subscription = mRxBus.doSubscribe(eventType, action, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                Logger.e(throwable.toString());
            }
        });
        mRxBus.addSubscription(this, subscription);
    }

    @Override
    public void unregisterRxBus() {
        mRxBus.unSubscribe(this);
    }
}
