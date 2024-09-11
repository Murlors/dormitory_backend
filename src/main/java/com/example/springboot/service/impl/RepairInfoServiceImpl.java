package com.example.springboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springboot.entity.RepairInfo;
import com.example.springboot.mapper.RepairInfoMapper;
import com.example.springboot.service.RepairInfoService;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;


@Service
public class RepairInfoServiceImpl extends ServiceImpl<RepairInfoMapper, RepairInfo> implements RepairInfoService {

    /**
     * 注入DAO层对象
     */
    @Resource
    private RepairInfoMapper repairInfoMapper;

    /**
     * 添加订单
     */
    @Override
    public int addNewOrder(RepairInfo repairInfo) {
        int insert = repairInfoMapper.insert(repairInfo);
        return insert;
    }

    /**
     * 查找订单
     */
    @Override
    public Page find(Integer pageNum, Integer pageSize, String search) {
        Page page = new Page<>(pageNum, pageSize);
        QueryWrapper<RepairInfo> qw = new QueryWrapper<>();
        qw.like("title", search);
        Page orderPage = repairInfoMapper.selectPage(page, qw);
        return orderPage;
    }

    @Override
    public Page individualFind(Integer pageNum, Integer pageSize, String search, String name) {
        Page page = new Page<>(pageNum, pageSize);
        QueryWrapper<RepairInfo> qw = new QueryWrapper<>();
        qw.like("title", search);
        qw.eq("repairer", name);
        Page orderPage = repairInfoMapper.selectPage(page, qw);
        return orderPage;
    }

    /**
     * 更新订单
     */
    @Override
    public int updateNewOrder(RepairInfo repairInfo) {
        int i = repairInfoMapper.updateById(repairInfo);
        Assert.notNull(i, "保修单为空");
        return i;
    }

    /**
     * 删除订单
     */
    @Override
    public int deleteOrder(Integer id) {
        int i = repairInfoMapper.deleteById(id);
        Assert.notNull(i, "保修单为空");
        return i;
    }

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
