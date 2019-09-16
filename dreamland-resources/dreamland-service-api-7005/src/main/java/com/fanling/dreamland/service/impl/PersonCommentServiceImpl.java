package com.fanling.dreamland.service.impl;

import com.fanling.dreamland.entity.PersonComment;
import com.fanling.dreamland.mapper.PersonCommentMapper;
import com.fanling.dreamland.service.IPersonCommentService;
import com.fanling.common.service.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 维修人员评价表的业务实现层
 */
@Service
public class PersonCommentServiceImpl extends ServiceImpl<PersonCommentMapper,PersonComment> implements IPersonCommentService {

}
