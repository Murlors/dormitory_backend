package com.example.springboot.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springboot.common.Result;
import com.example.springboot.entity.Student;
import com.example.springboot.entity.User;
import com.example.springboot.service.StudentService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/stu")
public class StudentController {
    @Resource
    private StudentService studentService;

    @ApiOperation(value = "学生登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user", value = "学生实体", required = true, dataType = "User"),
            @ApiImplicitParam(name = "session", value = "session", required = true, dataType = "HttpSession")
    })
    @PostMapping("/login")
    public Result<?> login(@RequestBody User user, HttpSession session) {
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
        Object o = studentService.stuLogin(user.getUsername(), user.getPassword());
        if (o != null) {
            System.out.println(o);
            //存入session
            session.setAttribute("Identity", "stu");
            session.setAttribute("User", o);
            return Result.success(o);
        } else {
            return Result.error("-1", "用户名或密码错误");
        }
    }

    @ApiOperation(value = "新增学生信息")
    @ApiImplicitParam(name = "student", value = "学生实体", required = true, dataType = "Student")
    @PostMapping("/add")
    public Result<?> add(@RequestBody Student student) {
        int i = studentService.addNewStudent(student);
        if (i == 1) {
            return Result.success();
        } else {
            return Result.error("-1", "添加失败");
        }
    }

    @ApiOperation(value = "删除学生信息")
    @ApiImplicitParam(name = "username", value = "学生用户名", required = true, dataType = "String")
    @DeleteMapping("/delete/{username}")
    public Result<?> delete(@PathVariable String username) {
        int i = studentService.deleteStudent(username);
        if (i == 1) {
            return Result.success();
        } else {
            return Result.error("-1", "删除失败");
        }
    }

    @ApiOperation(value = "更新学生信息")
    @ApiImplicitParam(name = "student", value = "学生实体", required = true, dataType = "Student")
    @PutMapping("/update")
    public Result<?> update(@RequestBody Student student) {
        int i = studentService.updateNewStudent(student);
        if (i == 1) {
            return Result.success();
        } else {
            return Result.error("-1", "更新失败");
        }
    }

    @ApiOperation(value = "查询学生信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页码", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "pageSize", value = "每页数量", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "search", value = "搜索关键字", required = true, dataType = "String")
    })
    @GetMapping("/find")
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search) {
        Page page = studentService.find(pageNum, pageSize, search);
        if (page != null) {
            return Result.success(page);
        } else {
            return Result.error("-1", "查询失败");
        }
    }

    @ApiOperation(value = "学生统计，用于首页顶部")
    @GetMapping("/stuNum")
    public Result<?> stuNum() {
        int num = studentService.stuNum();
        if (num > 0) {
            return Result.success(num);
        } else {
            return Result.error("-1", "查询失败");
        }
    }

}
