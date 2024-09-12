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
import java.util.List;
import java.util.concurrent.TimeUnit;


@Service
public class DormBuildImpl extends ServiceImpl<DormBuildMapper, DormBuild> implements DormBuildService {


    /**
     * 注入DAO层对象
     */
    @Resource
    private DormBuildMapper dormBuildMapper;
    @Resource
    private RedisTemplate<String, Object> redisTemplate; // 注入RedisTemplate

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
        String cacheKey = "dormBuild:cnt" + pageNum + ":" + pageSize + ":" + search;
        // 更正：从缓存中获取的是查询结果的总条数，不是Page对象
        Integer total = (Integer) redisTemplate.opsForValue().get(cacheKey);
        Page page = new Page<>(pageNum, pageSize);
        QueryWrapper<DormBuild> qw = new QueryWrapper<>();
        qw.like("DormBuild_id", search);
        // 如果缓存中没有总条数，则查询数据库并缓存结果的总条数
        if (total == null) {
            Page buildingPage = dormBuildMapper.selectPage(page, qw);
            total = (int) buildingPage.getTotal();
            redisTemplate.opsForValue().set(cacheKey, total, 10, TimeUnit.MINUTES); // 缓存总条数，过期时间10分钟
            return buildingPage;
        } else {
            // 如果缓存中有总条数，直接设置到Page对象中，然后执行分页查询获取数据
            page.setTotal(total);
            return dormBuildMapper.selectPage(page, qw);
        }
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

    /**
     * 首页 获取建筑名称
     */
    @Override
    public List<DormBuild> getBuildingId() {
        String cacheKey = "dormBuild:getBuildingId";
        List<DormBuild> cachedDormBuilds = (List<DormBuild>) redisTemplate.opsForValue().get(cacheKey);
        if (cachedDormBuilds != null) {
            return cachedDormBuilds;
        }
        QueryWrapper<DormBuild> qw = new QueryWrapper<>();
        qw.select("dormbuild_id");
        List<DormBuild> dormBuilds = dormBuildMapper.selectList(qw);
        redisTemplate.opsForValue().set(cacheKey, dormBuilds, 10, TimeUnit.MINUTES); // 过期时间1小时
        return dormBuilds;
    }
}
