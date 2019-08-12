package com.fanling.dreamland.common.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class BaseEntity implements Serializable {
    private static final long serialVersionUID = 2159746447551604368L;

    private String create_by;
    private Date create_time;
    private String update_by;
    private Date update_time;
}
