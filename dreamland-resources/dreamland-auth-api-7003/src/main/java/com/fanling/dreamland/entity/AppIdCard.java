package com.fanling.dreamland.entity;

import com.fanling.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户身份证表
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class AppIdCard extends BaseEntity {
    private static final long serialVersionUID = 1L;

    //主键
    private String id;
    //用户标识
    private String user_id;
    //真实姓名
    private String real_name;
    //身份证号码
    private String id_card_num;
    //身份证正面图片路径
    private String id_card_photo_path1;
    //身份证反面图片路径
    private String id_card_photo_path2;
    //手持身份证
    private String id_card_photo_path3;
    //验证状态
    private String status;
}
