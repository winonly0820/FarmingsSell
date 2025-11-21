package org.example.farmingsellbackend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.farmingsellbackend.entity.User;

public interface UserService extends IService<User> {
    User login(User user);}
