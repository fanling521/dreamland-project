package com.fanling.dreamland.common;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ServiceImpl<M extends BaseMapper<T>, T> implements IService<T> {
    @Autowired
    private M mapper;

    /**
     * 根据条件查询t表的信息
     *
     * @param t
     * @return
     */
    @Override
    public List<T> list(T t) {
        return mapper.list(t);
    }

    /**
     * 根据id查询t表的信息
     *
     * @param id
     * @return
     */
    @Override
    public T selectById(String id) {
        return mapper.selectById(id);
    }

    /**
     * 新增t表的信息
     *
     * @param t
     * @return
     */
    @Override
    public int insert(T t) {
        return mapper.insert(t);
    }

    /**
     * 修改t表的信息
     *
     * @param t
     * @return
     */
    @Override
    public int update(T t) {
        return mapper.update(t);
    }

    /**
     * 根据id删除t表的信息
     *
     * @param id
     * @return
     */
    @Override
    public int delete(String id) {
        return mapper.delete(id);
    }
}
