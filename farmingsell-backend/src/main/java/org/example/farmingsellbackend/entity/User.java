package org.example.farmingsellbackend.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 功能：user用户表中登录的实体类
 * 作者：大神
 * 日期： 2025/10/30 14:23
 */
@Data
@TableName("farmings_sell_users")
public class User {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String username;
    private String password;
    private String name;
    private String phone;
    private String email;
    private String avatar;
    private String role;
    @TableField(exist = false)
    private String token;

}