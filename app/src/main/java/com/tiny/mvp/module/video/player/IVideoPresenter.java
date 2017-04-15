package com.tiny.mvp.module.video.player;

import com.tiny.mvp.local.table.DanmakuInfo;
import com.tiny.mvp.local.table.VideoInfo;
import com.tiny.mvp.module.base.ILocalPresenter;

/**
 *
 * Video Presenter
 */
public interface IVideoPresenter extends ILocalPresenter<VideoInfo> {

    /**
     * 添加一条弹幕到数据库
     * @param danmakuInfo
     */
    void addDanmaku(DanmakuInfo danmakuInfo);

    /**
     * 清空该视频所有缓存弹幕
     */
    void cleanDanmaku();
}
