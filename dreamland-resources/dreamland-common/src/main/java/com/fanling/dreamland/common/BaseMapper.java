package com.fanling.dreamland.common;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BaseMapper<T> {
    /**
     * 根据条件查询表的信息
     *
     * @param t
     * @return
     */
    List<T> list(T t);

    /**
     * 根据id查询表的信息
     *
     * @param id
     * @return
     */
    T selectById(@Param("id") String id);

    /**
     * 新增表的信息
     *
     * @param t
     * @return
     */
    int insert(T t);

    /**
     * 修改表的信息
     *
     * @param t
     * @return
     */
    int update(T t);

    /**
     * 根据id删除表的信息
     *
     * @param id
     * @return
     */
    int delete(@Param("id") String id);
}
