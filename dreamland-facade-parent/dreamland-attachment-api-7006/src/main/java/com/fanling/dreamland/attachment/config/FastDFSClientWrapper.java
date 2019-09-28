package com.fanling.dreamland.attachment.config;

import com.fanling.dreamland.attachment.service.IFileAttachmentService;
import com.fanling.dreamland.attachment.entity.FileAttachment;
import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Component
public class FastDFSClientWrapper {
    @Autowired
    private FastFileStorageClient storageClient;

    @Autowired
    private IFileAttachmentService fileAttachmentService;

    /**
     * 文件上传
     *
     * @param file
     * @return
     * @throws IOException
     */
    public String uploadFile(MultipartFile file, String uid) {
        String fullPath = null;
        try {
            StorePath storePath = storageClient.uploadFile(file.getInputStream(), file.getSize(), FilenameUtils.getExtension(file.getOriginalFilename()), null);
            fullPath = storePath.getFullPath();
            String originFileName = file.getOriginalFilename();
            //后缀名
            assert originFileName != null;
            FileAttachment fileAttachment = new FileAttachment();
            fileAttachment.setFile_extension(originFileName.substring(originFileName.lastIndexOf(".")));
            fileAttachment.setFile_name(originFileName);
            fileAttachment.setFile_size(Long.toString(file.getSize()));
            fileAttachment.setFile_path(fullPath);
            fileAttachment.setUid(uid);
            fileAttachmentService.insert(fileAttachment);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fullPath;
    }
}
