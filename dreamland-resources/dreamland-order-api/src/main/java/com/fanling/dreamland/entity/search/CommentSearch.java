package com.fanling.dreamland.entity.search;

import com.fanling.dreamland.common.entity.BaseSearch;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 商品评论表
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CommentSearch extends BaseSearch {
    private static final long serialVersionUID = 1L;
}