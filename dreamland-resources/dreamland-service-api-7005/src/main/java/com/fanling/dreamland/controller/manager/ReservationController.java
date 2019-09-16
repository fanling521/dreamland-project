package com.fanling.dreamland.controller.manager;

import com.fanling.common.R;
import com.fanling.common.utils.StringUtils;
import com.fanling.common.web.BaseController;
import com.fanling.common.web.TableDataInfo;
import com.fanling.dreamland.entity.Reservation;
import com.fanling.dreamland.entity.search.ReservationSearch;
import com.fanling.dreamland.service.IReservationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "服务订购/预约表")
@RestController
@RequestMapping("/manager/reservation")
public class ReservationController extends BaseController {

    @Autowired
    private IReservationService reservationService;

    @ApiOperation(value = "服务订购/预约表分页查询", notes = "根据条件分页查看服务订购/预约表")
    @ApiImplicitParam(name = "search", required = true, value = "查询条件", dataType = "ReservationSearch", paramType = "body")
    @PostMapping("/list")
    public TableDataInfo list(@RequestBody ReservationSearch search) {
        startPage(search);
        Reservation reservation = new Reservation();
        List<Reservation> list = reservationService.list(reservation);
        return getDataTable(list);
    }

    @ApiOperation(value = "根据id查询服务订购/预约表", notes = "根据id查询服务订购/预约表")
    @ApiImplicitParam(name = "id", required = true, value = "唯一标识", dataType = "String", paramType = "path")
    @PostMapping("/select/{id}")
    public R selectById(@PathVariable("id") String id) {
        if (StringUtils.isEmpty(id)) {
            return error("id不能为空！");
        }
        Reservation reservation = reservationService.selectById(id);
        if (reservation == null) {
            return error("数据查找失败！");
        }
        return R.success(reservation);
    }

    @ApiOperation(value = "新增服务订购/预约表", notes = "根据必填项目新增服务订购/预约表")
    @ApiImplicitParam(name = "reservation", required = true, value = "要新增的信息", dataType = "Reservation", paramType = "body")
    @PostMapping("/insert")
    public R insert(@RequestBody Reservation reservation) {
        return toAjax(reservationService.insert(reservation));
    }

    @ApiOperation(value = "修改服务订购/预约表", notes = "修改服务订购/预约表,id必填")
    @ApiImplicitParam(name = "reservation", required = true, value = "要编辑的信息", dataType = "Reservation", paramType = "body")
    @PostMapping("/update")
    public R update(@RequestBody Reservation reservation) {
        return toAjax(reservationService.update(reservation));
    }

    @ApiOperation(value = "根据id删除服务订购/预约表查询", notes = "根据id删除服务订购/预约表查询")
    @ApiImplicitParam(name = "id", required = true, value = "唯一标识", dataType = "String", paramType = "path")
    @DeleteMapping("/remove/{id}")
    public R remove(@PathVariable("id") String id) {
        if (StringUtils.isEmpty(id)) {
            return error("id不能为空！");
        }
        return toAjax(reservationService.delete(id));
    }

}
