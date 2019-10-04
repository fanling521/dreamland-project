package com.fanling.dreamland.common.entity;

import com.fanling.dreamland.common.utils.StringUtils;
import lombok.Data;

/**
 * 查询DTO
 * @param <T>
 */
@Data
public class BaseSearch<T> {
    private int page_size;
    private int page_num;
    private String order_by;
    private String asc;
    private T obj;

    public String getOrder_by() {
        if (StringUtils.isEmpty(order_by)) {
            return "";
        }
        return order_by + " " + asc;
    }
}
