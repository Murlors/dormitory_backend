package com.example.springboot.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.springboot.entity.DormManager;


public interface DormManagerService extends IService<DormManager> {

    //宿管登陆
    DormManager dormManagerLogin(String username, String password);

    //新增宿管
    int addNewDormManager(DormManager dormManager);

    //查询宿管
    Page find(Integer pageNum, Integer pageSize, String search);

    //更新宿管信息
    int updateNewDormManager(DormManager dormManager);

    //删除宿管信息
    int deleteDormManager(String username);
}
