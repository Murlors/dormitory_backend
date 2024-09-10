package com.example.springboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.springboot.entity.DormRoom;


public interface DormRoomService extends IService<DormRoom> {
    //统计没有住满的宿舍数量
    int notFullRoom();
    //主页 住宿人数
    Long selectHaveRoomStuNum();
}
