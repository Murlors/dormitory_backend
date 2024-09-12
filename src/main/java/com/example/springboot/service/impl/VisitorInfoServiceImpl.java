package com.example.springboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springboot.entity.VisitorInfo;
import com.example.springboot.mapper.VisitorInfoMapper;
import com.example.springboot.service.VisitorInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class VisitorInfoServiceImpl extends ServiceImpl<VisitorInfoMapper, VisitorInfo> implements VisitorInfoService {

    @Resource
    private VisitorInfoMapper visitorInfoMapper;

    /**
     * 访客添加
     */
    @Override
    public int addNewVisitor(VisitorInfo visitorInfo) {
        int insert = visitorInfoMapper.insert(visitorInfo);
        return insert;
    }

    /**
     * 访客查询
     */
    @Override
    public Page find(Integer pageNum, Integer pageSize, String search) {
//        return visitorMapper.selectPage(new Page<>(pageNum, pageSize), new QueryWrapper<Visitor>().like("name", search));
        Page newPage = new Page<>(pageNum, pageSize);
        QueryWrapper<VisitorInfo> qw = new QueryWrapper<>();
        qw.like("name", search);
        Page visitorPage = visitorInfoMapper.selectPage(newPage, qw);
        return visitorPage;
    }

    /**
     * 访客信息更新
     */
    @Override
    public int updateNewVisitor(VisitorInfo visitorInfo) {
        int i = visitorInfoMapper.updateById(visitorInfo);
        return i;
    }

    /**
     * 访客删除
     */
    @Override
    public int deleteVisitor(Integer id) {
        int i = visitorInfoMapper.deleteById(id);
        return i;
    }
}
