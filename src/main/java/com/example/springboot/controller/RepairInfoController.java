package com.example.springboot.controller;

import com.example.springboot.common.Result;
import com.example.springboot.service.RepairInfoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/repair")
public class RepairInfoController {

    @Resource
    private RepairInfoService repairInfoService;

    @ApiOperation(value = "报修统计 用于首页顶部")
    @GetMapping("/orderNum")
    public Result<?> orderNum() {
        int num = repairInfoService.showOrderNum();
        if (num >= 0) {
            return Result.success(num);
        } else {
            return Result.error("-1", "报修统计查询失败");
        }
    }
}
