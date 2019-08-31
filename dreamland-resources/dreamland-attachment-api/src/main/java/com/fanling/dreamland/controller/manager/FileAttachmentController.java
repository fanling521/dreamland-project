package com.fanling.dreamland.controller.manager;

import com.fanling.common.R;
import com.fanling.common.utils.StringUtils;
import com.fanling.common.web.BaseController;
import com.fanling.common.web.TableDataInfo;
import com.fanling.dreamland.entity.search.FileAttachmentSearch;
import com.fanling.dreamland.entity.FileAttachment;
import com.fanling.dreamland.service.IFileAttachmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "图片附件表")
@RestController
@RequestMapping("/manager/file/attachment")
public class FileAttachmentController extends BaseController {

    @Autowired
    private IFileAttachmentService fileAttachmentService;

    @ApiOperation(value = "图片附件表分页查询", notes = "根据条件分页查看图片附件表")
    @ApiImplicitParam(name = "search", required = true, value = "查询条件", dataType = "FileAttachmentSearch", paramType = "body")
    @PostMapping("/list")
    public TableDataInfo list(@RequestBody FileAttachmentSearch search) {
        startPage(search);
        FileAttachment fileAttachment = new FileAttachment();
        List<FileAttachment> list = fileAttachmentService.list(fileAttachment);
        return getDataTable(list);
    }

    @ApiOperation(value = "根据id查询图片附件表", notes = "根据id查询图片附件表")
    @ApiImplicitParam(name = "id", required = true, value = "唯一标识", dataType = "String", paramType = "path")
    @PostMapping("/select/{id}")
    public R selectById(@PathVariable("id") String id) {
        if (StringUtils.isEmpty(id)) {
            return error("id不能为空！");
        }
        FileAttachment fileAttachment = fileAttachmentService.selectById(id);
        if (fileAttachment == null) {
            return error("数据查找失败！");
        }
        return R.success(fileAttachment);
    }

    @ApiOperation(value = "新增图片附件表", notes = "根据必填项目新增图片附件表")
    @ApiImplicitParam(name = "fileAttachment", required = true, value = "要新增的信息", dataType = "FileAttachment", paramType = "body")
    @PostMapping("/insert")
    public R insert(@RequestBody FileAttachment fileAttachment) {
        return toAjax(fileAttachmentService.insert(fileAttachment));
    }

    @ApiOperation(value = "修改图片附件表", notes = "修改图片附件表,id必填")
    @ApiImplicitParam(name = "fileAttachment", required = true, value = "要编辑的信息", dataType = "FileAttachment", paramType = "body")
    @PostMapping("/update")
    public R update(@RequestBody FileAttachment fileAttachment) {
        return toAjax(fileAttachmentService.update(fileAttachment));
    }

    @ApiOperation(value = "根据id删除图片附件表查询", notes = "根据id删除图片附件表查询")
    @ApiImplicitParam(name = "id", required = true, value = "唯一标识", dataType = "String", paramType = "path")
    @DeleteMapping("/remove/{id}")
    public R remove(@PathVariable("id") String id) {
        if (StringUtils.isEmpty(id)) {
            return error("id不能为空！");
        }
        return toAjax(fileAttachmentService.delete(id));
    }

}
