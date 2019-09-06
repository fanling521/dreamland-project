package com.fanling.dreamland.controller;

import com.fanling.common.R;
import com.fanling.common.utils.MyAssert;
import com.fanling.common.web.BaseController;
import com.fanling.dreamland.config.FastDFSClientWrapper;
import com.fanling.dreamland.entity.FileAttachment;
import com.fanling.dreamland.entity.FileDelRequest;
import com.fanling.dreamland.service.IFileAttachmentService;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Api(tags = "附件上传")
@RestController
@RequestMapping("/file/attachment")
public class UploadController extends BaseController {

    @Autowired
    private IFileAttachmentService fileAttachmentService;

    @Autowired
    protected FastDFSClientWrapper fastDFSClientWrapper;

    @Autowired
    private FastFileStorageClient storageClient;

    @ApiOperation(value = "新增图片附件表", notes = "根据必填项目新增图片附件表")
    @ApiImplicitParam(name = "uid", required = true, value = "用户标识", dataType = "String", paramType = "query")
    @PostMapping("/upload")
    public R upload(MultipartFile file, @RequestParam("uid") String uid) {
        //文件大小限制
        long fileSize = file.getSize();
        if (fileSize == 0) {
            return error("文件大小为空，请重新选择！");
        }
        String path = fastDFSClientWrapper.uploadFile(file, uid);
        return success(path);
    }

    @ApiOperation(value = "根据路径删除文件", notes = "根据路径删除文件")
    @ApiImplicitParam(name = "uid", required = true, value = "用户标识", dataType = "String", paramType = "query")
    @PostMapping("/remove")
    public R remove(@RequestBody FileDelRequest fileDelRequest) {
        MyAssert.notNull(fileDelRequest.getUid(), "用户标识不能为空！");
        MyAssert.notNull(fileDelRequest.getPath(), "文件路径不能为空！");
        //根据路径找文件信息
        FileAttachment fileAttachment = new FileAttachment();
        fileAttachment.setFile_path(fileDelRequest.getPath());
        List<FileAttachment> list = fileAttachmentService.list(fileAttachment);
        if (list.size() == 0) {
            return error("删除失败，没有找到相关文件的信息");
        } else {
            FileAttachment bean = list.get(0);
            storageClient.deleteFile(bean.getFile_path());
            return toAjax(fileAttachmentService.delete(bean.getId()));
        }
    }

}
