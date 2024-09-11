package com.example.springboot.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.springboot.entity.AdjustRoomInfo;

public interface AdjustRoomInfoService extends IService<AdjustRoomInfo> {
    //查询调宿申请
    Page find(Integer pageNum, Integer pageSize, String search);

    //删除调宿申请
    int deleteAdjustment(Integer id);

    //更新
    int updateApply(AdjustRoomInfo adjustRoomInfo);

    // 添加
    int addApply(AdjustRoomInfo adjustRoomInfo);
}
