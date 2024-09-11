package com.example.springboot.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.springboot.entity.RepairInfo;


public interface RepairInfoService extends IService<RepairInfo> {

    //显示订单数量
    int showOrderNum();

    //新增订单
    int addNewOrder(RepairInfo repairInfo);

    //查询
    Page find(Integer pageNum, Integer pageSize, String search);

    //查询
    Page individualFind(Integer pageNum, Integer pageSize, String search, String name);

    //更新订单信息
    int updateNewOrder(RepairInfo repairInfo);

    //删除订单
    int deleteOrder(Integer id);
}
