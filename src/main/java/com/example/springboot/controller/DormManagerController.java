package com.example.springboot.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springboot.common.Result;
import com.example.springboot.entity.DormManager;
import com.example.springboot.entity.User;
import com.example.springboot.service.DormManagerService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/dormManager")
public class DormManagerController {

    @Resource
    private DormManagerService dormManagerService;

    @ApiOperation(value = "添加宿管")
    @ApiImplicitParam(name = "dormManager", value = "宿管实体", required = true, dataType = "DormManager")
    @PostMapping("/add")
    public Result<?> add(@RequestBody DormManager dormManager) {
        int i = dormManagerService.addNewDormManager(dormManager);
        if (i == 1) {
            return Result.success();
        } else {
            return Result.error("-1", "添加失败");
        }
    }

    @ApiOperation(value = "更新宿管信息")
    @ApiImplicitParam(name = "dormManager", value = "宿管实体", required = true, dataType = "DormManager")
    @PutMapping("/update")
    public Result<?> update(@RequestBody DormManager dormManager) {
        int i = dormManagerService.updateNewDormManager(dormManager);
        if (i == 1) {
            return Result.success();
        } else {
            return Result.error("-1", "更新失败");
        }
    }

    @ApiOperation(value = "删除宿管")
    @ApiImplicitParam(name = "username", value = "宿管用户名", required = true, dataType = "String")
    @DeleteMapping("/delete/{username}")
    public Result<?> delete(@PathVariable String username) {
        int i = dormManagerService.deleteDormManager(username);
        if (i == 1) {
            return Result.success();
        } else {
            return Result.error("-1", "删除失败");
        }
    }

    @ApiOperation(value = "查找宿管")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页码", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "pageSize", value = "每页数量", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "search", value = "搜索关键字", required = true, dataType = "String")
    })
    @GetMapping("/find")
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search) {
        Page page = dormManagerService.find(pageNum, pageSize, search);
        if (page != null) {
            return Result.success(page);
        } else {
            return Result.error("-1", "查询失败");
        }
    }

    @ApiOperation(value = "宿管登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user", value = "宿管实体", required = true, dataType = "User"),
            @ApiImplicitParam(name = "session", value = "session", required = true, dataType = "HttpSession")
    })
    @PostMapping("/login")
    public Result<?> login(@RequestBody User user, HttpSession session) {
        Object o = dormManagerService.dormManagerLogin(user.getUsername(), user.getPassword());
        if (o != null) {
            System.out.println(o);
            //存入session
            session.setAttribute("Identity", "dormManager");
            session.setAttribute("User", o);
            return Result.success(o);
        } else {
            return Result.error("-1", "用户名或密码错误");
        }
    }
}
