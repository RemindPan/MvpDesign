package com.tiny.mvp.module.news.special;

import com.tiny.mvp.api.bean.SpecialInfo;
import com.tiny.mvp.adapter.item.SpecialItem;
import com.tiny.mvp.module.base.IBaseView;

import java.util.List;

/**
 *
 * 专题View接口
 */
public interface ISpecialView extends IBaseView {

    /**
     * 显示数据
     * @param specialItems 新闻
     */
    void loadData(List<SpecialItem> specialItems);

    /**
     * 添加头部
     * @param specialBean
     */
    void loadBanner(SpecialInfo specialBean);
}
