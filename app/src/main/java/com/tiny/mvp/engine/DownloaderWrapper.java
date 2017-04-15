package com.tiny.mvp.engine;

import android.text.TextUtils;

import com.tiny.downloaderlib.DownloadListener;
import com.tiny.downloaderlib.FileDownloader;
import com.tiny.downloaderlib.entity.FileInfo;
import com.tiny.downloaderlib.model.DownloadStatus;
import com.tiny.mvp.local.table.VideoInfo;
import com.tiny.mvp.local.table.VideoInfoDao;
import com.tiny.mvp.rxbus.RxBus;
import com.tiny.mvp.rxbus.event.VideoEvent;
import com.tiny.mvp.utils.StringUtils;
import com.orhanobut.logger.Logger;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * 下载器封装
 */
public final class DownloaderWrapper {

    private static List<VideoInfo> sDLVideoList = new ArrayList<>();
    private static RxBus sRxBus;
    private static VideoInfoDao sDbDao;

    private DownloaderWrapper() {
        throw new AssertionError();
    }

    public static void init(RxBus rxBus, VideoInfoDao videoInfoDao) {
        sRxBus = rxBus;
        sDbDao = videoInfoDao;
    }

    /**
     * 开始下载
     * @param info
     */
    public static void start(VideoInfo info) {
        // 在真正处理前状态设为等待
        info.setDownloadStatus(DownloadStatus.WAIT);
        // 有高清就用高清的，没有用另一个
        if (TextUtils.isEmpty(info.getMp4Hd_url())) {
            info.setVideoUrl(info.getMp4_url());
        } else {
            info.setVideoUrl(info.getMp4Hd_url());
        }
        // 插入或更新
        sDbDao.insertOrReplace(info);
        sDLVideoList.add(info);
        // 通知 Video 主界面刷新下载数
        sRxBus.post(new VideoEvent());
        // 启动下载
        FileDownloader.start(info.getVideoUrl(), StringUtils.clipFileName(info.getVideoUrl()), new ListenerWrapper());
    }

    /**
     * 暂停下载
     * @param info
     */
    public static void stop(VideoInfo info) {
        FileDownloader.stop(info.getVideoUrl());
    }

    /**
     * 取消下载，不会删除已下载完的文件
     *
     * @param info
     */
    public static void cancel(VideoInfo info) {
        FileDownloader.cancel(info.getVideoUrl());
        // 删除
        sDbDao.delete(info);
        // 通知 Video 主界面刷新下载数
        sRxBus.post(new VideoEvent());
        // 通知缓存列表
        sRxBus.post(new FileInfo(DownloadStatus.CANCEL, info.getVideoUrl(),
                StringUtils.clipFileName(info.getVideoUrl()), (int) info.getTotalSize()));
        sDLVideoList.remove(_findApp(info.getVideoUrl()));
    }

    /**
     * 删除会把下载完成的文件清除
     * @param info
     */
    public static void delete(VideoInfo info) {
        // 路径要在 FileDownloader.cancel 前获取
        String path = FileDownloader.getFilePathByUrl(info.getVideoUrl());
        FileDownloader.cancel(info.getVideoUrl());
        // 删除
        sDbDao.delete(info);
        // 通知 Video 主界面刷新下载数
        sRxBus.post(new VideoEvent());
        sDLVideoList.remove(_findApp(info.getVideoUrl()));
        File file = new File(path);
        if (file.exists()) {
            file.delete();
        }
    }

    /**
     * 监听器封装类
     */
    static class ListenerWrapper implements DownloadListener {

        @Override
        public void onStart(FileInfo fileInfo) {
            _updateVideoInfo(fileInfo);
            sRxBus.post(fileInfo);
        }

        @Override
        public void onUpdate(FileInfo fileInfo) {
            _updateVideoInfo(fileInfo);
            sRxBus.post(fileInfo);
        }

        @Override
        public void onStop(FileInfo fileInfo) {
            _updateVideoInfo(fileInfo);
            sRxBus.post(fileInfo);
            sDLVideoList.remove(_findApp(fileInfo.getUrl()));
        }

        @Override
        public void onComplete(FileInfo fileInfo) {
            _updateVideoInfo(fileInfo);
            sRxBus.post(fileInfo);
            // 通知 Video 主界面刷新下载数
            sRxBus.post(new VideoEvent());
            Logger.e("onComplete " + fileInfo.toString());
        }

        @Override
        public void onCancel(FileInfo fileInfo) {
            _updateVideoInfo(fileInfo);
            sRxBus.post(fileInfo);
            sDLVideoList.remove(_findApp(fileInfo.getUrl()));
        }

        @Override
        public void onError(FileInfo fileInfo, String s) {
            _updateVideoInfo(fileInfo);
            Logger.e(s);
            sRxBus.post(fileInfo);
        }
    }

    /**
     * 查找APP
     *
     * @param url url
     * @return
     */
    private static VideoInfo _findApp(String url) {
        for (VideoInfo appInfo : sDLVideoList) {
            if (appInfo.getVideoUrl().equals(url)) {
                return appInfo;
            }
        }
        return null;
    }

    /**
     * 更新数据
     *
     * @param fileInfo 文件信息
     */
    private static void _updateVideoInfo(FileInfo fileInfo) {
        VideoInfo info = _findApp(fileInfo.getUrl());
        if (info != null) {
            if (fileInfo.getTotalBytes() != 0) {
                info.setTotalSize(fileInfo.getTotalBytes());
                info.setLoadedSize(fileInfo.getLoadBytes());
                info.setDownloadSpeed(fileInfo.getSpeed());
            }
            info.setDownloadStatus(fileInfo.getStatus());
            sDbDao.update(info);
        }
    }
}
