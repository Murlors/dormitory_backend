package com.example.springboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springboot.entity.RepairInfo;
import com.example.springboot.mapper.RepairInfoMapper;
import com.example.springboot.service.RepairInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class RepairInfoServiceImpl extends ServiceImpl<RepairInfoMapper, RepairInfo> implements RepairInfoService {

    /**
     * 注入DAO层对象
     */
    @Resource
    private RepairInfoMapper repairInfoMapper;
    /**
     * 首页顶部：报修统计
     */
    @Override
    public int showOrderNum() {
        QueryWrapper<RepairInfo> qw = new QueryWrapper<>();
        int orderCount = Math.toIntExact(repairInfoMapper.selectCount(qw));
        return orderCount;
    }
}
