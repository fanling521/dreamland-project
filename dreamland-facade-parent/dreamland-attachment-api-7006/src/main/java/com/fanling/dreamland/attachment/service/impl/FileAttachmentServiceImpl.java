package com.fanling.dreamland.attachment.service.impl;

import com.fanling.dreamland.attachment.entity.FileAttachment;
import com.fanling.dreamland.attachment.mapper.FileAttachmentMapper;
import com.fanling.dreamland.attachment.service.IFileAttachmentService;
import com.fanling.dreamland.common.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 图片附件表的业务实现层
 */
@Service
public class FileAttachmentServiceImpl extends ServiceImpl<FileAttachmentMapper,FileAttachment> implements IFileAttachmentService {

}
