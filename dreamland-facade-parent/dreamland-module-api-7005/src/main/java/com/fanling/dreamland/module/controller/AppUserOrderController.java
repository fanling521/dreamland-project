package com.fanling.dreamland.module.controller;

import com.fanling.dreamland.common.R;
import com.fanling.dreamland.common.web.BaseController;
import com.fanling.dreamland.module.response.UserSale;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "用户与服务数据")
@RestController
@RequestMapping("/app/user/sale")
public class AppUserOrderController extends BaseController {

    @ApiOperation(value = "查询用户服务订购信息", notes = "根据id查询用户服务订购信息")
    @ApiImplicitParam(name = "id", required = true, value = "唯一标识", dataType = "String", paramType = "path")
    @PostMapping("/select/{id}")
    R getUserSale(@PathVariable("id") String id) {
        return R.success(new UserSale());
    }

}
