package com.example.springboot.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springboot.common.Result;
import com.example.springboot.entity.DormBuild;
import com.example.springboot.service.DormBuildService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/building")
public class DormBuildController {

    @Resource
    private DormBuildService dormBuildService;

    @ApiOperation(value = "添加楼宇")
    @ApiImplicitParam(name = "dormBuild", value = "楼宇实体", required = true, dataType = "DormBuild")
    @PostMapping("/add")
    public Result<?> add(@RequestBody DormBuild dormBuild) {
        int i = dormBuildService.addNewBuilding(dormBuild);
        if (i == 1) {
            return Result.success();
        } else {
            return Result.error("-1", "添加失败");
        }
    }

    @ApiOperation(value = "更新楼宇信息")
    @ApiImplicitParam(name = "dormBuild", value = "楼宇实体", required = true, dataType = "DormBuild")
    @PutMapping("/update")
    public Result<?> update(@RequestBody DormBuild dormBuild) {
        int i = dormBuildService.updateNewBuilding(dormBuild);
        if (i == 1) {
            return Result.success();
        } else {
            return Result.error("-1", "更新失败");
        }
    }

    @ApiOperation(value = "删除楼宇")
    @ApiImplicitParam(name = "dormBuildId", value = "楼宇id", required = true, dataType = "Integer")
    @DeleteMapping("/delete/{dormBuildId}")
    public Result<?> delete(@PathVariable Integer dormBuildId) {
        int i = dormBuildService.deleteBuilding(dormBuildId);
        if (i == 1) {
            return Result.success();
        } else {
            return Result.error("-1", "删除失败");
        }
    }

    @ApiOperation(value = "查找楼宇")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页码", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "pageSize", value = "每页数量", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "search", value = "搜索关键字", required = true, dataType = "String")
    })
    @GetMapping("/find")
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search) {
        Page page = dormBuildService.find(pageNum, pageSize, search);
        if (page != null) {
            return Result.success(page);
        } else {
            return Result.error("-1", "查询失败");
        }
    }

}
