package com.example.springboot.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springboot.entity.DormManager;
import com.example.springboot.mapper.DormManagerMapper;
import com.example.springboot.service.DormManagerService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;


@Service
public class DormManagerServiceImpl extends ServiceImpl<DormManagerMapper, DormManager> implements DormManagerService {
    /**
     * 注入DAO层对象
     */
    @Resource
    private DormManagerMapper dormManagerMapper;
    @Resource
    private RedisTemplate<String, Object> redisTemplate; // 注入RedisTemplate

    /**
     * 宿管登录
     */
    @Override
    public DormManager dormManagerLogin(String username, String password) {
        String cacheKey = "dormManager:login:" + username;

        // 尝试从Redis中获取缓存的宿舍管理员登录信息
        DormManager cachedDormManager = (DormManager) redisTemplate.opsForValue().get(cacheKey);
        if (cachedDormManager != null) {
            return cachedDormManager;
        }
        QueryWrapper<DormManager> qw = new QueryWrapper<>();
        qw.eq("username", username);
        qw.eq("password", password);
        DormManager dormManager = dormManagerMapper.selectOne(qw);
        if (dormManager != null) {
            // 将查询到的宿舍管理员信息存入Redis，并设置一个合理的过期时间，例如10分钟
            redisTemplate.opsForValue().set(cacheKey, dormManager, 10, TimeUnit.MINUTES);
            return dormManager;
        } else {
            return null;
        }
    }

    /**
     * 宿管新增
     */
    @Override
    public int addNewDormManager(DormManager dormManager) {
        int insert = dormManagerMapper.insert(dormManager);
        return insert;
    }

    /**
     * 宿管查找
     */
    @Override
    public Page find(Integer pageNum, Integer pageSize, String search) {
        Page page = new Page<>(pageNum, pageSize);
        QueryWrapper<DormManager> qw = new QueryWrapper<>();
        qw.like("name", search);
        Page dormManagerPage = dormManagerMapper.selectPage(page, qw);
        return dormManagerPage;
    }

    /**
     * 宿管信息更新
     */
    @Override
    public int updateNewDormManager(DormManager dormManager) {
        int i = dormManagerMapper.updateById(dormManager);
        return i;
    }

    /**
     * 宿管删除
     */
    @Override
    public int deleteDormManager(String username) {
        int i = dormManagerMapper.deleteById(username);
        return i;
    }
}
