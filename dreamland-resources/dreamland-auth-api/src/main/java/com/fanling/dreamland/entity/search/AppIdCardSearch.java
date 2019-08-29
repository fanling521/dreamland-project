package com.fanling.dreamland.entity.search;

import com.fanling.common.entity.BaseSearch;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 用户身份证表
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class AppIdCardSearch extends BaseSearch {
    private static final long serialVersionUID = 1L;
    // 真实姓名
    private String real_name;
    // 身份证号码
    private String id_card_num;
}