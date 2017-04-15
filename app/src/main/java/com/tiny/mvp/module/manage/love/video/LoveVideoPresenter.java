package com.tiny.mvp.module.manage.love.video;

import com.tiny.downloaderlib.model.DownloadStatus;
import com.tiny.mvp.local.table.VideoInfo;
import com.tiny.mvp.local.table.VideoInfoDao;
import com.tiny.mvp.module.base.ILocalPresenter;
import com.tiny.mvp.module.base.ILocalView;
import com.tiny.mvp.rxbus.RxBus;
import com.tiny.mvp.rxbus.event.VideoEvent;
import com.tiny.mvp.utils.ListUtils;

import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 *
 * Video收藏 Presenter
 */
public class LoveVideoPresenter implements ILocalPresenter<VideoInfo> {

    private final ILocalView mView;
    private final VideoInfoDao mDbDao;
    private final RxBus mRxBus;

    public LoveVideoPresenter(ILocalView view, VideoInfoDao dbDao, RxBus rxBus) {
        mView = view;
        mDbDao = dbDao;
        mRxBus = rxBus;
    }

    @Override
    public void getData() {
        mDbDao.queryBuilder().where(VideoInfoDao.Properties.IsCollect.eq(true))
                .rx()
                .list()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<VideoInfo>>() {
                    @Override
                    public void call(List<VideoInfo> videoList) {
                        if (ListUtils.isEmpty(videoList)) {
                            mView.noData();
                        } else {
                            mView.loadData(videoList);
                        }
                    }
                });
    }

    @Override
    public void getMoreData() {
    }

    @Override
    public void insert(VideoInfo data) {
    }

    @Override
    public void delete(VideoInfo data) {
        data.setCollect(false);
        if (!data.isCollect() && data.getDownloadStatus() == DownloadStatus.NORMAL) {
            mDbDao.delete(data);
        } else {
            mDbDao.update(data);
        }
        if (mDbDao.queryBuilder().where(VideoInfoDao.Properties.IsCollect.eq(true)).count() == 0) {
            // 如果收藏为0则显示无收藏
            mView.noData();
        }
        mRxBus.post(new VideoEvent());
    }

    @Override
    public void update(List<VideoInfo> list) {
    }
}
