package com.example.springboot.controller;

import com.example.springboot.common.Result;
import com.example.springboot.common.UID;
import com.example.springboot.entity.Admin;
import com.example.springboot.entity.User;
import com.example.springboot.service.AdminService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@RestController //表示这是一个控制器类，它处理 HTTP 请求并返回 JSON 格式的响应。
@RequestMapping("/admin")//设置此控制器的基础 URL 为 /admin，也就是说，所有请求 URL 都会以 /admin 开头。
public class AdminController {

    String uid = new UID().produceUID();//生成一个唯一的标识符，用于标识当前操作的管理员，

    @Resource
    private AdminService adminService;

    @ApiOperation(value = "管理员登录")//用于生成 Swagger 文档，描述接口的用途。
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user", value = "用户实体", required = true, dataType = "User"),
            @ApiImplicitParam(name = "session", value = "session", required = true, dataType = "HttpSession")
    })
    @PostMapping("/login")//该方法处理 /admin/login 路径的 POST 请求。
    public Result<?> login(@RequestBody User user, HttpSession session) {
        Object o = adminService.adminLogin(user.getUsername(), user.getPassword());
        if (o != null) {
            System.out.println(o);
            //存入session
            session.setAttribute("Identity", "admin");
            session.setAttribute("User", o);
            return Result.success(o);
        } else {
            return Result.error("-1", "用户名或密码错误");
        }
    }

    @ApiOperation(value = "更新管理员信息")
    @ApiImplicitParam(name = "admin", value = "管理员实体", required = true, dataType = "Admin")
    @PutMapping("/update")
    public Result<?> update(@RequestBody Admin admin) {
        int i = adminService.updateAdmin(admin);
        if (i == 1) {
            return Result.success();
        } else {
            return Result.error("-1", "更新失败");
        }
    }
}
