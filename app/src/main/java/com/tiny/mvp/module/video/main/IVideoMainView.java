package com.tiny.mvp.module.video.main;

/**
 *
 * video 主界面接口
 */
public interface IVideoMainView {

    /**
     * 更新数据
     * @param lovedCount 收藏数
     */
    void updateLovedCount(int lovedCount);

    /**
     * 更新数据
     * @param downloadCount 下载中个数
     */
    void updateDownloadCount(int downloadCount);
}
