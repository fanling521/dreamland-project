package com.fanling.dreamland.module.controller;

import com.fanling.dreamland.common.R;
import com.fanling.dreamland.common.entity.BaseSearch;
import com.fanling.dreamland.common.utils.StringUtils;
import com.fanling.dreamland.common.web.BaseController;
import com.fanling.dreamland.common.web.TableDataInfo;
import com.fanling.dreamland.module.entity.StaffComment;
import com.fanling.dreamland.module.service.IStaffCommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "维修人员的评论表")
@RestController
@RequestMapping("/manage/staff/comment")
public class MaStaffCommentController extends BaseController {

    @Autowired
    private IStaffCommentService staffCommentService;

    @ApiOperation(value = "商品评论表分页查询", notes = "根据条件分页查看商品评论表")
    @ApiImplicitParam(name = "search", required = true, value = "查询条件", dataType = "BaseSearch", paramType = "body")
    @PostMapping("/list")
    public TableDataInfo list(@RequestBody BaseSearch<StaffComment> search) {
        startPage(search);
        StaffComment staffComment = new StaffComment();
        List<StaffComment> list = staffCommentService.list(staffComment);
        return getDataTable(list);
    }

    @ApiOperation(value = "根据id查询商品评论表", notes = "根据id查询商品评论表")
    @ApiImplicitParam(name = "id", required = true, value = "唯一标识", dataType = "String", paramType = "path")
    @PostMapping("/select/{id}")
    public R selectById(@PathVariable("id") String id) {
        if (StringUtils.isEmpty(id)) {
            return error("id不能为空！");
        }
        StaffComment staffComment = staffCommentService.selectById(id);
        if (staffComment == null) {
            return error("数据查找失败！");
        }
        return R.success(staffComment);
    }

    @ApiOperation(value = "新增商品评论表", notes = "根据必填项目新增商品评论表")
    @ApiImplicitParam(name = "comment", required = true, value = "要新增的信息", dataType = "StaffComment", paramType = "body")
    @PostMapping("/insert")
    public R insert(@RequestBody StaffComment staffComment) {
        return toAjax(staffCommentService.insert(staffComment));
    }

    @ApiOperation(value = "修改商品评论表", notes = "修改商品评论表,id必填")
    @ApiImplicitParam(name = "comment", required = true, value = "要编辑的信息", dataType = "StaffComment", paramType = "body")
    @PostMapping("/update")
    public R update(@RequestBody StaffComment staffComment) {
        return toAjax(staffCommentService.update(staffComment));
    }

    @ApiOperation(value = "根据id删除商品评论表查询", notes = "根据id删除商品评论表查询")
    @ApiImplicitParam(name = "id", required = true, value = "唯一标识", dataType = "String", paramType = "path")
    @DeleteMapping("/remove/{id}")
    public R remove(@PathVariable("id") String id) {
        if (StringUtils.isEmpty(id)) {
            return error("id不能为空！");
        }
        return toAjax(staffCommentService.delete(id));
    }

}
