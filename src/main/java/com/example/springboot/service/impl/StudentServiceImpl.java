package com.example.springboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springboot.entity.Student;
import com.example.springboot.mapper.StudentMapper;
import com.example.springboot.service.StudentService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;


@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {
    /**
     * 注入DAO层对象
     */
    @Resource
    private StudentMapper studentMapper;
    @Resource
    private RedisTemplate<String, Object> redisTemplate; // 注入RedisTemplate

    /**
     * 学生登陆
     */
    @Override
    public Student stuLogin(String username, String password) {

        // 尝试从Redis中获取缓存的学生信息
        Student cachedStudent = (Student) redisTemplate.opsForValue().get(cacheKey);
        if (cachedStudent != null) {
            return cachedStudent;
        }
        QueryWrapper<Student> qw = new QueryWrapper<>();
        qw.eq("username", username);
        qw.eq("password", password);
        Student student = studentMapper.selectOne(qw);
        if (student != null) {
            // 将查询到的学生信息存入Redis，并设置过期时间，比如5分钟
            redisTemplate.opsForValue().set(cacheKey, student, 10, TimeUnit.MINUTES);
            return student;
        } else {
            return null;
        }
    }

    /**
     * 新增学生信息
     */
    @Override
    public int addNewStudent(Student student) {
        int insert = studentMapper.insert(student);
        return insert;
    }

    /**
     * 删除学生信息
     */
    @Override
    public int deleteStudent(String username) {
        int i = studentMapper.deleteById(username);
        return i;
    }

    /**
     * 更新学生信息
     */
    @Override
    public int updateNewStudent(Student student) {
        int i = studentMapper.updateById(student);
        return i;
    }

    /**
     * 分页查询学生信息
     */
    @Override
    public Page find(Integer pageNum, Integer pageSize, String search) {
        Page page = new Page<>(pageNum, pageSize);
        QueryWrapper<Student> qw = new QueryWrapper<>();
        qw.like("name", search);
        Page studentPage = studentMapper.selectPage(page, qw);
        return studentPage;
    }

    /**
     * 主页顶部：学生统计
     */
    @Override
    public int stuNum() {
        QueryWrapper<Student> qw = new QueryWrapper<>();
        qw.isNotNull("username");
        int stuNum = Math.toIntExact(studentMapper.selectCount(qw));
        return stuNum;
    }

    /**
     * 床位信息，查询该学生信息
     */
    @Override
    public Student stuInfo(String username) {
        QueryWrapper<Student> qw = new QueryWrapper<>();
        qw.eq("username", username);
        Student student = studentMapper.selectOne(qw);
        return student;
    }
}
