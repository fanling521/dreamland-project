package com.fanling.dreamland.module.service.impl;

import com.fanling.dreamland.common.service.impl.ServiceImpl;
import com.fanling.dreamland.module.entity.ItemComment;
import com.fanling.dreamland.module.mapper.ItemCommentMapper;
import com.fanling.dreamland.module.service.IItemCommentService;
import org.springframework.stereotype.Service;

/**
 * 商品评论表的业务实现层
 */
@Service
public class ItemCommentServiceImpl extends ServiceImpl<ItemCommentMapper, ItemComment> implements IItemCommentService {

}
