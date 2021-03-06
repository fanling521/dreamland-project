package com.fanling.dreamland.attachment.controller;

import com.fanling.dreamland.attachment.entity.FileAttachment;
import com.fanling.dreamland.attachment.service.IFileAttachmentService;
import com.fanling.dreamland.common.R;
import com.fanling.dreamland.common.entity.BaseSearch;
import com.fanling.dreamland.common.utils.StringUtils;
import com.fanling.dreamland.common.web.BaseController;
import com.fanling.dreamland.common.web.TableDataInfo;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "图片附件表")
@RestController
@RequestMapping("/manage/file/attachment")
public class MaFileAttachmentController extends BaseController {

    @Autowired
    private IFileAttachmentService fileAttachmentService;

    @Autowired
    private FastFileStorageClient storageClient;

    @ApiOperation(value = "图片附件表分页查询", notes = "根据条件分页查看图片附件表")
    @ApiImplicitParam(name = "search", required = true, value = "查询条件", dataType = "FileAttachmentSearch", paramType = "body")
    @PostMapping("/list")
    public TableDataInfo list(@RequestBody BaseSearch<FileAttachment> search) {
        startPage(search);
        FileAttachment fileAttachment = new FileAttachment();
        List<FileAttachment> list = fileAttachmentService.list(fileAttachment);
        return getDataTable(list);
    }

    @ApiOperation(value = "根据id删除图片附件表查询", notes = "根据id删除图片附件表查询")
    @ApiImplicitParam(name = "id", required = true, value = "唯一标识", dataType = "String", paramType = "path")
    @DeleteMapping("/remove/{id}")
    public R remove(@PathVariable("id") String id) {
        if (StringUtils.isEmpty(id)) {
            return error("id不能为空！");
        }
        FileAttachment fileAttachment = fileAttachmentService.selectById(id);
        storageClient.deleteFile(fileAttachment.getFile_path());
        return toAjax(fileAttachmentService.delete(id));
    }

}
