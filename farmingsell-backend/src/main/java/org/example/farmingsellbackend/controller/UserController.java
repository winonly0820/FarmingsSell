package org.example.farmingsellbackend.controller;

import org.example.farmingsellbackend.common.AuthAccess;
import org.example.farmingsellbackend.common.Result;
import org.example.farmingsellbackend.entity.User;
import org.example.farmingsellbackend.service.UserService;
import org.example.farmingsellbackend.utils.RSAUtils;
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
//    @AuthAccess
//    @PostMapping("/login")
//public Result login(@RequestBody User user) {
//        User loginUser = userService.login(user);
//        if (loginUser != null) {
//            // 登录成功：用 Result.success 包裹数据
//            // 这就是为什么前端能收到 code: '200' 的原因
//            return Result.success(loginUser);
//        } else {
//            // 登录失败：用 Result.error 返回错误信息
//            return Result.error("账号或密码错误");
//        }
//    }
@AuthAccess
@PostMapping("/login")
public Result login(@RequestBody User user) {
    // 1. 基础校验
    if (user.getUsername() == null || user.getPassword() == null) {
        return Result.error("账号或密码不能为空");
    }

    try {
        // 2. RSA 解密：将前端传来的密文还原为原始明文
        // 只有解密后，才能交给 Service 层进行 BCrypt 匹配
        String rawPassword = RSAUtils.decrypt(user.getPassword());
        user.setPassword(rawPassword);

        // 3. 调用 Service 层逻辑 (现在 Service 内部应该使用 BCrypt.checkpw)
        User loginUser = userService.login(user);

        if (loginUser != null) {
            // 登录成功：用 Result.success 包裹数据
            return Result.success(loginUser);
        } else {
            return Result.error("账号或密码错误");
        }
    } catch (Exception e) {
        // 如果解密失败（可能是请求被非法篡改或密钥不匹配）
        return Result.error("登录解析异常");
    }
}
    @AuthAccess
    @PostMapping("/register")
    public Result register(@RequestBody User user) {
        // 1. 校验基础信息（安全性增强）
        if (user.getUsername() == null || user.getPassword() == null) {
            return Result.error("账号或密码不能为空");
        }

        try {
            // 2. RSA 解密：将前端传来的密文还原为原始明文
            // 注意：这里调用你之前的 RSAUtils
            String rawPassword = RSAUtils.decrypt(user.getPassword());

            // 3. BCrypt 哈希加固：满足“数据库不存储明文密码”的要求
            // 使用 Hutool 的 BCrypt，生成的哈希值包含盐值，极难破解
            String hashedPassword = cn.hutool.crypto.digest.BCrypt.hashpw(rawPassword);

            // 4. 将哈希后的密码设置回对象
            user.setPassword(hashedPassword);

            // 5. RBAC 初始化：注册用户默认为普通用户
            // 对应你实体类中的 role 字段
            if (user.getRole() == null) {
                user.setRole("USER");
            }

            // 6. 调用 Service 层存入数据库 (farmings_sell_users表)
            userService.register(user);

            return Result.success("注册成功");

        } catch (Exception e) {
            // 如果解密失败（比如有人恶意篡改请求包），会抛出异常
            return Result.error("注册请求非法或解析错误");
        }
    }
    }
