package com.example.springboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springboot.entity.AdjustRoomInfo;
import com.example.springboot.mapper.AdjustRoomInfoMapper;
import com.example.springboot.service.AdjustRoomInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AdjustRoomInfoServiceImpl extends ServiceImpl<AdjustRoomInfoMapper, AdjustRoomInfo> implements AdjustRoomInfoService {


    @Resource
    private AdjustRoomInfoMapper adjustRoomInfoMapper;

    /**
     * 添加调宿申请
     */
    @Override
    public int addApply(AdjustRoomInfo adjustRoomInfo) {
        int insert = adjustRoomInfoMapper.insert(adjustRoomInfo);
        return insert;
    }

    /**
     * 查找调宿申请
     */
    @Override
    public Page find(Integer pageNum, Integer pageSize, String search) {
        Page page = new Page<>(pageNum, pageSize);
        QueryWrapper<AdjustRoomInfo> qw = new QueryWrapper<>();
        qw.like("username", search);
        Page orderPage = adjustRoomInfoMapper.selectPage(page, qw);
        return orderPage;
    }

    /**
     * 删除调宿申请
     */
    @Override
    public int deleteAdjustment(Integer id) {
        int i = adjustRoomInfoMapper.deleteById(id);
        return i;
    }

    /**
     * 更新调宿申请
     */
    @Override
    public int updateApply(AdjustRoomInfo adjustRoomInfo) {
        int i = adjustRoomInfoMapper.updateById(adjustRoomInfo);
        return i;
    }
}
