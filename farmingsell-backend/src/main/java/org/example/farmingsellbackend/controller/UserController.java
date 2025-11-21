package org.example.farmingsellbackend.controller;

import org.example.farmingsellbackend.common.Result;
import org.example.farmingsellbackend.entity.User;
import org.example.farmingsellbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 功能：
 * 作者：大神
 * 日期： 2025/11/20 0:51
 */
@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    @PostMapping("/login")
public Result login(@RequestBody User user) {
        User loginUser = userService.login(user);
        if (loginUser != null) {
            // 登录成功：用 Result.success 包裹数据
            // 这就是为什么前端能收到 code: '200' 的原因
            return Result.success(loginUser);
        } else {
            // 登录失败：用 Result.error 返回错误信息
            return Result.error("账号或密码错误");
        }
    }
    }
