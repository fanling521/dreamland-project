package com.fanling.dreamland.controller.manager;

import com.fanling.common.R;
import com.fanling.common.utils.StringUtils;
import com.fanling.common.web.BaseController;
import com.fanling.common.web.TableDataInfo;
import com.fanling.dreamland.entity.PersonComment;
import com.fanling.dreamland.entity.search.PersonCommentSearch;
import com.fanling.dreamland.service.IPersonCommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "维修人员评价表")
@RestController
@RequestMapping("/manager/person/comment")
public class PersonCommentController extends BaseController {

    @Autowired
    private IPersonCommentService personCommentService;

    @ApiOperation(value = "维修人员评价表分页查询", notes = "根据条件分页查看维修人员评价表")
    @ApiImplicitParam(name = "search", required = true, value = "查询条件", dataType = "PersonCommentSearch", paramType = "body")
    @PostMapping("/list")
    public TableDataInfo list(@RequestBody PersonCommentSearch search) {
        startPage(search);
        PersonComment personComment = new PersonComment();
        personComment.setStar_lv(search.getStar_lv());
        List<PersonComment> list = personCommentService.list(personComment);
        return getDataTable(list);
    }

    @ApiOperation(value = "根据id查询维修人员评价表", notes = "根据id查询维修人员评价表")
    @ApiImplicitParam(name = "id", required = true, value = "唯一标识", dataType = "String", paramType = "path")
    @PostMapping("/select/{id}")
    public R selectById(@PathVariable("id") String id) {
        if (StringUtils.isEmpty(id)) {
            return error("id不能为空！");
        }
        PersonComment personComment = personCommentService.selectById(id);
        if (personComment == null) {
            return error("数据查找失败！");
        }
        return R.success(personComment);
    }

    @ApiOperation(value = "新增维修人员评价表", notes = "根据必填项目新增维修人员评价表")
    @ApiImplicitParam(name = "personComment", required = true, value = "要新增的信息", dataType = "PersonComment", paramType = "body")
    @PostMapping("/insert")
    public R insert(@RequestBody PersonComment personComment) {
        return toAjax(personCommentService.insert(personComment));
    }

    @ApiOperation(value = "修改维修人员评价表", notes = "修改维修人员评价表,id必填")
    @ApiImplicitParam(name = "personComment", required = true, value = "要编辑的信息", dataType = "PersonComment", paramType = "body")
    @PostMapping("/update")
    public R update(@RequestBody PersonComment personComment) {
        return toAjax(personCommentService.update(personComment));
    }

    @ApiOperation(value = "根据id删除维修人员评价表查询", notes = "根据id删除维修人员评价表查询")
    @ApiImplicitParam(name = "id", required = true, value = "唯一标识", dataType = "String", paramType = "path")
    @DeleteMapping("/remove/{id}")
    public R remove(@PathVariable("id") String id) {
        if (StringUtils.isEmpty(id)) {
            return error("id不能为空！");
        }
        return toAjax(personCommentService.delete(id));
    }

}
