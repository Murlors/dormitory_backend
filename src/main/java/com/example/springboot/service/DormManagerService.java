package com.example.springboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.springboot.entity.DormManager;


public interface DormManagerService extends IService<DormManager> {

    DormManager dormManagerLogin(String username, String password);

}
