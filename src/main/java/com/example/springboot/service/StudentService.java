package com.example.springboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.springboot.entity.Student;


public interface StudentService extends IService<Student> {

    //学生登陆
    Student stuLogin(String username, String password);

}
