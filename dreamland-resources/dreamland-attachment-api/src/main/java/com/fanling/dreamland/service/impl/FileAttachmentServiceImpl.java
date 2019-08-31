package com.fanling.dreamland.service.impl;

import com.fanling.dreamland.entity.FileAttachment;
import com.fanling.dreamland.mapper.FileAttachmentMapper;
import com.fanling.dreamland.service.IFileAttachmentService;
import com.fanling.common.service.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 图片附件表的业务实现层
 */
@Service
public class FileAttachmentServiceImpl extends ServiceImpl<FileAttachmentMapper,FileAttachment> implements IFileAttachmentService {

}
