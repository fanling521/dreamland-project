package com.fanling.dreamland.service.impl;

import com.fanling.dreamland.entity.Comment;
import com.fanling.dreamland.mapper.CommentMapper;
import com.fanling.dreamland.service.ICommentService;
import com.fanling.dreamland.common.service.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 商品评论表的业务实现层
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper,Comment> implements ICommentService {

}
