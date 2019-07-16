package com.fanling.dreamland.page;

import com.fanling.dreamland.utils.StringUtils;
import lombok.Getter;
import lombok.Setter;

/**
 * 分页数据
 */
@Getter
@Setter
public class PageDomain {
    //当前记录起始索引
    private Integer pageNum;
    //每页显示记录数
    private Integer pageSize;
    //排序列
    private String orderByColumn;
    //排序的方向 "desc" 或者 "asc".
    private String isAsc;

    public String getOrderBy() {
        if (StringUtils.isEmpty(orderByColumn)) {
            return "";
        }
        return StringUtils.toUnderScoreCase(orderByColumn) + " " + isAsc;
    }
}
