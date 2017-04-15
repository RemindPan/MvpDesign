package com.tiny.downloaderlib.exception;

/**
 *
 * 下载异常
 */
public class DownloadException extends RuntimeException {

    public DownloadException(String detailMessage) {
        super(detailMessage);
    }
}
