package com.fanling.dreamland.common.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class BaseEntity implements Serializable {
    private static final long serialVersionUID = -1840343569789811501L;
    //主键
    private String id;
    //创建人
    private String create_by;
    //创建时间
    private Date create_time;
    //更新时间
    private Date update_time;
}
