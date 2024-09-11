package com.example.springboot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * 用户实体，简化登录问题
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String username;

    private String password;
    private String identity;
    private String avatar;
}
