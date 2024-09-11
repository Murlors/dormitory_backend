package com.example.springboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springboot.entity.DormBuild;
import com.example.springboot.mapper.DormBuildMapper;
import com.example.springboot.service.DormBuildService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;



@Service
public class DormBuildImpl extends ServiceImpl<DormBuildMapper, DormBuild> implements DormBuildService {


    /**
     * 注入DAO层对象
     */
    @Resource
    private DormBuildMapper dormBuildMapper;

    /**
     * 楼宇添加
     */
    @Override
    public int addNewBuilding(DormBuild dormBuild) {
        int insert = dormBuildMapper.insert(dormBuild);
        return insert;
    }

    /**
     * 楼宇查找
     */
    @Override
    public Page find(Integer pageNum, Integer pageSize, String search) {
        Page page = new Page<>(pageNum, pageSize);
        QueryWrapper<DormBuild> qw = new QueryWrapper<>();
        qw.like("DormBuild_id", search);
        // 如果缓存中没有总条数，则查询数据库并缓存结果的总条数
        Page buildingPage = dormBuildMapper.selectPage(page, qw);
        return buildingPage;
    }

    /**
     * 楼宇信息更新
     */
    @Override
    public int updateNewBuilding(DormBuild dormBuild) {
        int i = dormBuildMapper.updateById(dormBuild);
        return i;
    }

    /**
     * 楼宇删除
     */
    @Override
    public int deleteBuilding(Integer id) {
        int i = dormBuildMapper.deleteById(id);
        return i;
    }

}
