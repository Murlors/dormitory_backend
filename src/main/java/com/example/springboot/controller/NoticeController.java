package com.example.springboot.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springboot.common.Result;
import com.example.springboot.entity.Notice;
import com.example.springboot.service.NoticeService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/notice")
public class NoticeController {

    @Resource
    NoticeService noticeService;

    @ApiOperation(value = "新增公告")
    @ApiImplicitParam(name = "notice", value = "公告实体", required = true, dataType = "Notice")
    @PostMapping("/add")
    public Result<?> add(@RequestBody Notice notice) {
        int i = noticeService.addNewNotice(notice);
        if (i == 1) {
            return Result.success();
        } else {
            return Result.error("-1", "添加失败");
        }
    }

    @ApiOperation(value = "更新公告")
    @ApiImplicitParam(name = "notice", value = "公告实体", required = true, dataType = "Notice")
    @PutMapping("/update")
    public Result<?> update(@RequestBody Notice notice) {
        int i = noticeService.updateNewNotice(notice);
        if (i == 1) {
            return Result.success();
        } else {
            return Result.error("-1", "更新失败");
        }
    }

    @ApiOperation(value = "删除公告")
    @ApiImplicitParam(name = "id", value = "公告id", required = true, dataType = "Integer")
    @DeleteMapping("/delete/{id}")
    public Result<?> delete(@PathVariable Integer id) {
        int i = noticeService.deleteNotice(id);
        if (i == 1) {
            return Result.success();
        } else {
            return Result.error("-1", "删除失败");
        }
    }

    @ApiOperation(value = "查找公告")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页码", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "pageSize", value = "每页数量", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "search", value = "搜索内容", required = true, dataType = "String")
    })
    @GetMapping("/find")
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search) {
        Page page = noticeService.find(pageNum, pageSize, search);
        if (page != null) {
            return Result.success(page);
        } else {
            return Result.error("-1", "查询失败");
        }
    }
}
