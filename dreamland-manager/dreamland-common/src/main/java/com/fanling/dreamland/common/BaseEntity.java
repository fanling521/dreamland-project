package com.fanling.dreamland.common;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * Entity基类
 *
 * @author ruoyi
 */
@Getter
@Setter
public class BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 创建者
     */
    private String createBy;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新者
     */
    private String updateBy;

    /**
     * 更新时间
     */
    private Date updateTime;

}
