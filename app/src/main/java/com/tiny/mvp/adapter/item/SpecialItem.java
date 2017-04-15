package com.tiny.mvp.adapter.item;

import com.tiny.mvp.api.bean.NewsItemInfo;
import com.tiny.recycler.entity.SectionEntity;


public class SpecialItem extends SectionEntity<NewsItemInfo> {

    public SpecialItem(boolean isHeader, String header) {
        super(isHeader, header);
    }

    public SpecialItem(NewsItemInfo newsItemBean) {
        super(newsItemBean);
    }
}
