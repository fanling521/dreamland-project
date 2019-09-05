package com.fanling.dreamland.controller;

import com.fanling.common.R;
import com.fanling.common.web.BaseController;
import com.fanling.dreamland.config.FastDFSClientWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Api(tags = "附件上传")
@RestController
@RequestMapping("/file/attachment")
public class UploadController extends BaseController {

    @Autowired
    protected FastDFSClientWrapper fastDFSClientWrapper;

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

}
