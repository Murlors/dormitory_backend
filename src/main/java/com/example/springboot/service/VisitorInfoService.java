package com.example.springboot.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.springboot.entity.VisitorInfo;


public interface VisitorInfoService extends IService<VisitorInfo> {

    //新增订单
    int addNewVisitor(VisitorInfo visitorInfo);

    //查询
    Page find(Integer pageNum, Integer pageSize, String search);

    //更新订单信息
    int updateNewVisitor(VisitorInfo visitorInfo);

    //删除订单
    int deleteVisitor(Integer id);
}
