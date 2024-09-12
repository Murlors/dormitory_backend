package com.example.springboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springboot.entity.Admin;
import com.example.springboot.mapper.AdminMapper;
import com.example.springboot.service.AdminService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {
    /**
     * 注入DAO层对象
     */
    @Resource
    private AdminMapper adminMapper;
    @Resource
    private RedisTemplate<String, Object> redisTemplate; // 注入RedisTemplate

    /**
     * 管理员登录
     */
    @Override
    public Admin adminLogin(String username, String password) {
        // 设置键
        String cacheKey = "admin:login:" + username;
        // 尝试从Redis中获取缓存的管理员登录信息
        Admin cachedAdmin = (Admin) redisTemplate.opsForValue().get(cacheKey);
        if (cachedAdmin != null) {
            return cachedAdmin;
        }
        QueryWrapper<Admin> qw = new QueryWrapper<>();
        qw.eq("username", username);
        qw.eq("password", password);
        Admin admin = adminMapper.selectOne(qw);
        if (admin != null) {
            // 将查询到的管理员信息存入Redis，并设置一个合理的过期时间，比如10分钟
            redisTemplate.opsForValue().set(cacheKey, admin, 10, TimeUnit.MINUTES);
            return admin;
        } else {
            return null;
        }
    }

    /**
     * 管理员信息更新
     */
    @Override
    public int updateAdmin(Admin admin) {
        int i = adminMapper.updateById(admin);
        return i;
    }
}
