package com.fanling.dreamland.common.web;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * 表格分页数据对象
 */
@Getter
@Setter
public class TableDataInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 总记录数
     */
    private long count;
    /**
     * 列表数据
     */
    private List<?> data;
    /**
     * 消息状态码
     */
    private int code;

    /**
     * 表格数据对象
     */
    public TableDataInfo() {
    }

    /**
     * 分页
     *
     * @param list  列表数据
     * @param count 总记录数
     */
    public TableDataInfo(List<?> list, int count) {
        this.data = list;
        this.count = count;
    }
}
