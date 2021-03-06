package com.tiny.mvp.module.photo.main;

import com.tiny.mvp.local.table.BeautyPhotoInfoDao;
import com.tiny.mvp.module.base.IRxBusPresenter;
import com.tiny.mvp.rxbus.RxBus;
import com.orhanobut.logger.Logger;

import rx.Subscription;
import rx.functions.Action1;

/**
 *
 * 图片专栏 Presenter
 */
public class PhotoMainPresenter implements IRxBusPresenter {

    private final IPhotoMainView mView;
    private final BeautyPhotoInfoDao mDbDao;
    private final RxBus mRxBus;

    public PhotoMainPresenter(IPhotoMainView view, BeautyPhotoInfoDao dbDao, RxBus rxBus) {
        mView = view;
        mDbDao = dbDao;
        mRxBus = rxBus;
    }

    @Override
    public void getData() {
        mView.updateCount((int) mDbDao.queryBuilder().where(BeautyPhotoInfoDao.Properties.IsLove.eq(true)).count());
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
