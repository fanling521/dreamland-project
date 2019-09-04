package com.fanling.dreamland.controller.api;

import com.fanling.common.web.BaseController;
import com.fanling.dreamland.config.FastDFSClientWrapper;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Api(tags = "附件对外API")
@RestController
@RequestMapping("/file/attachment")
public class AttachmentApiController extends BaseController {

    @Autowired
    private FastDFSClientWrapper dfsClient;

    // 上传文件
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String upload(MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return dfsClient.uploadFile(file);
    }
}
