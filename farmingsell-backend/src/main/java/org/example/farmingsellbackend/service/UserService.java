package org.example.farmingsellbackend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.farmingsellbackend.entity.User;

public interface UserService extends IService<User> {
    User login(User user);
    User register(User user);
    // 根据用户名查询用户
    User selectByUsername(String username);

}
