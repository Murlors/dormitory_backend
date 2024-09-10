package com.example.springboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.springboot.entity.RepairInfo;


public interface RepairInfoService extends IService<RepairInfo> {

    //显示订单数量
    int showOrderNum();
}
