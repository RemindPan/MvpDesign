package com.tiny.mvp.module.video.player;

import com.tiny.mvp.local.table.VideoInfo;
import com.tiny.mvp.module.base.IBaseView;

import java.io.InputStream;

/**
 *
 * Video接口
 */
public interface IVideoView extends IBaseView {

    /**
     * 获取Video数据
     * @param data 数据
     */
    void loadData(VideoInfo data);

    /**
     * 获取弹幕数据
     * @param inputStream 数据
     */
    void loadDanmakuData(InputStream inputStream);

}
