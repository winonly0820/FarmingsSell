package org.example.farmingsellbackend.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.example.farmingsellbackend.entity.User;
import org.example.farmingsellbackend.exception.ServiceException;
import org.example.farmingsellbackend.mapper.UserMapper;
import org.example.farmingsellbackend.service.UserService;
import org.example.farmingsellbackend.utils.TokenUtils;
import org.springframework.stereotype.Service;

/**
 * 功能：
 * 作者：大神
 * 日期： 2025/11/20 0:01
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
//这里我是想加上一个override注解的，但是我不会这个是什么意思，所以就没加，应该是加上的
    public User selectByUsername(String Username) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", Username);
//        从父类中继承了getone的方法，所以这里就不要写basemapper了，直接使用this.即可
        return this.getOne(queryWrapper);
    }
    @Override
    public User login(User user) {
        User dbuser = selectByUsername(user.getUsername());
        if (dbuser == null) {
            throw new ServiceException("用户名或者密码错误");
        }
        if (!user.getPassword().equals(dbuser.getPassword())) {
            throw new ServiceException("密码错误");
        }
        String token = TokenUtils.createToken(dbuser.getId().toString(), dbuser.getPassword());
        dbuser.setToken(token);
        return dbuser;


    }

}