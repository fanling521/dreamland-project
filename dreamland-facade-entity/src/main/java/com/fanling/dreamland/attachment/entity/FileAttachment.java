package com.fanling.dreamland.attachment.entity;

import com.fanling.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 图片附件表
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class FileAttachment extends BaseEntity {
    private static final long serialVersionUID = 1L;

    //主键
    private String id;
    //用户标识
    private String uid;
    //文件类型
    private String file_extension;
    //文件大小
    private String file_size;
    //文件名称
    private String file_name;
    //文件路径
    private String file_path;
}
