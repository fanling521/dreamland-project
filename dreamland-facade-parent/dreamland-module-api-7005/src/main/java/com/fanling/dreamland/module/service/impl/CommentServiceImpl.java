package com.fanling.dreamland.module.service.impl;

import com.fanling.common.service.ServiceImpl;
import com.fanling.dreamland.module.entity.Comment;
import com.fanling.dreamland.module.mapper.CommentMapper;
import com.fanling.dreamland.module.service.ICommentService;
import org.springframework.stereotype.Service;

/**
 * 商品评论表的业务实现层
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements ICommentService {

}
