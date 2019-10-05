package com.fanling.dreamland.module.service.impl;

import com.fanling.dreamland.common.service.impl.ServiceImpl;
import com.fanling.dreamland.module.entity.StaffComment;
import com.fanling.dreamland.module.mapper.StaffCommentMapper;
import com.fanling.dreamland.module.service.IStaffCommentService;
import org.springframework.stereotype.Service;

@Service
public class StaffCommentServiceImpl extends ServiceImpl<StaffCommentMapper, StaffComment> implements IStaffCommentService {
}
