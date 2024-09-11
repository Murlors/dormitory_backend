package com.example.springboot.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springboot.common.Result;
import com.example.springboot.entity.AdjustRoomInfo;
import com.example.springboot.service.AdjustRoomInfoService;
import com.example.springboot.service.DormRoomService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/adjustRoom")
public class AdjustRoomInfoController {

    @Resource
    private AdjustRoomInfoService adjustRoomInfoService;

    @Resource
    private DormRoomService dormRoomService;

    @ApiOperation(value = "添加调宿申请")
    @ApiImplicitParam(name = "adjustRoom", value = "调宿申请实体", required = true, dataType = "AdjustRoom")
    @PostMapping("/add")
    public Result<?> add(@RequestBody AdjustRoomInfo adjustRoomInfo) {
        int result = adjustRoomInfoService.addApply(adjustRoomInfo);
        if (result == 1) {
            return Result.success();
        } else {
            return Result.error("-1", "查询失败");
        }
    }


    @ApiOperation(value = "更新调宿申请")
    @ApiImplicitParam(name = "adjustRoom", value = "调宿申请实体", required = true, dataType = "AdjustRoom")
    @PutMapping("/update/{state}")
    public Result<?> update(@RequestBody AdjustRoomInfo adjustRoomInfo, @PathVariable Boolean state) {
        if (state) {
            int i = dormRoomService.adjustRoomUpdate(adjustRoomInfo);
            if (i == -1) {
                return Result.error("-1", "重复操作");
            }
        }
        int i = adjustRoomInfoService.updateApply(adjustRoomInfo);
        if (i == 1) {
            return Result.success();
        } else {
            return Result.error("-1", "更新失败");
        }
    }

    @ApiOperation(value = "删除调宿申请")
    @ApiImplicitParam(name = "id", value = "调宿申请id", required = true, dataType = "Integer")
    @DeleteMapping("/delete/{id}")
    public Result<?> delete(@PathVariable Integer id) {
        int i = adjustRoomInfoService.deleteAdjustment(id);
        if (i == 1) {
            return Result.success();
        } else {
            return Result.error("-1", "删除失败");
        }
    }

    @ApiOperation(value = "查询调宿申请")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页码", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "pageSize", value = "每页显示数量", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "search", value = "搜索关键字", required = true, dataType = "String")
    })
    @GetMapping("/find")
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search) {
        Page page = adjustRoomInfoService.find(pageNum, pageSize, search);
        if (page != null) {
            return Result.success(page);
        } else {
            return Result.error("-1", "查询失败");
        }
    }
}
