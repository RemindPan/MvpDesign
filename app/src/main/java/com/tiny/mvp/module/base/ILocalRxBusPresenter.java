package com.tiny.mvp.module.base;

import java.util.List;

/**
 *
 */

public interface ILocalRxBusPresenter<E> extends IRxBusPresenter {

    /**
     * 插入数据
     * @param data  数据
     */
    void insert(E data);

    /**
     * 删除数据
     * @param data  数据
     */
    void delete(E data);

    /**
     * 更新数据
     * @param list   所有数据
     */
    void update(List<E> list);
}
