package com.course.sysuser.controller;

import com.course.common.vo.R;
import com.course.sysuser.entity.Sysuser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

/**
 * @auther shanhen
 * @create 2020-09-11 14:28
 */
@Api(description = "用户管理")
@CrossOrigin
@RestController
@RequestMapping(value = "/admin/sysuser")
public class SysuserController {

    @ApiOperation(value = "用户登录")
    @PostMapping("login")
    public R login(
            @ApiParam(name = "sysuser", value = "用户对象", required = true)
            @RequestBody Sysuser sysuser
            ){
        return R.ok().data("token","admin");
    }

    @ApiOperation(value = "用户信息")
    @GetMapping("info")
    public R info(
            @ApiParam(name = "token", value = "令牌", required = true)
            @RequestParam String token
    ){
        return R.ok().data("name","admin").data("roles","admin");
    }

    @ApiOperation(value = "用户退出")
    @PostMapping("logout")
    public R logout(){
        return R.ok();
    }

}
