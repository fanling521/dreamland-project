package com.fanling.dreamland.entitys;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class BaseEntity implements Serializable {
    private static final long serialVersionUID = 2159746447551604368L;

    private String create_by;
    private Date create_time;
    private String update_by;
    private Date update_time;
}
