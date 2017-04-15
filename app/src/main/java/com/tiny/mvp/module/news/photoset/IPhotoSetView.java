package com.tiny.mvp.module.news.photoset;

import com.tiny.mvp.api.bean.PhotoSetInfo;
import com.tiny.mvp.module.base.IBaseView;

/**
 *
 * 图集界面接口
 */
public interface IPhotoSetView extends IBaseView {

    /**
     * 显示数据
     * @param photoSetBean 图集
     */
    void loadData(PhotoSetInfo photoSetBean);
}
