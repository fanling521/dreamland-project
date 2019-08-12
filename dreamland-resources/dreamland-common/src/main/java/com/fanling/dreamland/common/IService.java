package com.fanling.dreamland.common;

import java.util.List;

public interface IService<T> {
    /**
     * 根据条件查询t表的信息
     *
     * @param t
     * @return
     */
    List<T> list(T t);

    /**
     * 根据id查询t表的信息
     *
     * @param id
     * @return
     */
    T selectById(String id);

    /**
     * 新增t表的信息
     *
     * @param t
     * @return
     */
    int insert(T t);

    /**
     * 修改t表的信息
     *
     * @param t
     * @return
     */
    int update(T t);

    /**
     * 根据id删除t表的信息
     *
     * @param id
     * @return
     */
    int delete(String id);
}
