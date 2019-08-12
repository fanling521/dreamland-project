package com.fanling.dreamland.common.entity;

import com.fanling.dreamland.utils.StringUtils;
import lombok.Data;

@Data
public class BaseSearch {
    //当前记录起始索引
    private Integer page_num;
    //每页显示记录数
    private Integer page_size;
    //排序列
    private String order_by;
    //排序的方向 "desc" 或者 "asc".
    private String asc;

    public String getOrder_by() {
        if (StringUtils.isEmpty(order_by)) {
            return "";
        }
        return StringUtils.toUnderScoreCase(order_by) + " " + asc;
    }
}
