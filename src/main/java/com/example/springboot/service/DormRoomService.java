package com.example.springboot.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.springboot.entity.DormRoom;


public interface DormRoomService extends IService<DormRoom> {
    //统计没有住满的宿舍数量
    int notFullRoom();

    //新增房间
    int addNewRoom(DormRoom dormRoom);

    //删除房间信息
    int deleteRoom(Integer dormRoomId);

    //更新房间信息
    int updateNewRoom(DormRoom dormRoom);

    //查询房间
    Page find(Integer pageNum, Integer pageSize, String search);

    //删除床位上的学生信息
    int deleteBedInfo(String bedName, Integer dormRoomId, int calCurrentNum);

    //床位信息，查询该学生是否已有宿舍
    DormRoom judgeHadBed(String username);
    //主页 住宿人数
    Long selectHaveRoomStuNum();
}
