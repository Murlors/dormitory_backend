package com.example.springboot.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springboot.common.Result;
import com.example.springboot.entity.VisitorInfo;
import com.example.springboot.service.VisitorInfoService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/visitor")
public class VisitorInfoController {

    @Resource
    private VisitorInfoService visitorInfoService;

    @ApiOperation(value = "添加访客")
    @ApiImplicitParam(name = "visitor", value = "访客实体", required = true, dataType = "Visitor")
    @PostMapping("/add")
    public Result<?> add(@RequestBody VisitorInfo visitorInfo) {
        int i = visitorInfoService.addNewVisitor(visitorInfo);
        if (i == 1) {
            return Result.success();
        } else {
            return Result.error("-1", "添加失败");
        }
    }

    @ApiOperation(value = "更新访客信息")
    @ApiImplicitParam(name = "visitor", value = "访客实体", required = true, dataType = "Visitor")
    @PutMapping("/update")
    public Result<?> update(@RequestBody VisitorInfo visitorInfo) {
        int i = visitorInfoService.updateNewVisitor(visitorInfo);
        if (i == 1) {
            return Result.success();
        } else {
            return Result.error("-1", "更新失败");
        }
    }

    @ApiOperation(value = "删除访客")
    @ApiImplicitParam(name = "id", value = "访客id", required = true, dataType = "Integer")
    @DeleteMapping("/delete/{id}")
    public Result<?> delete(@PathVariable Integer id) {
        int i = visitorInfoService.deleteVisitor(id);
        if (i == 1) {
            return Result.success();
        } else {
            return Result.error("-1", "删除失败");
        }
    }

    /**
     * 访客查询
     */
    @ApiOperation(value = "查找访客")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页码", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "pageSize", value = "每页数量", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "search", value = "搜索关键字", required = true, dataType = "String")
    })
    @GetMapping("/find")
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search) {
        Page page = visitorInfoService.find(pageNum, pageSize, search);
        if (page != null) {
            return Result.success(page);
        } else {
            return Result.error("-1", "查询失败");
        }
    }
}
