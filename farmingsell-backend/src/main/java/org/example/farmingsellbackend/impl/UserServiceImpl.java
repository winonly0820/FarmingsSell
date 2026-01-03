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
    @Override
    public User register(User user) {
        // 1. 检查用户名是否已存在
        User dbUser = selectByUsername(user.getUsername());
        if (dbUser != null) {
            throw new ServiceException("该用户名已被注册");
        }

        // 2. 这里的密码是从 Controller 传来的，由于 Controller 已经做过解密和哈希处理，
        // 我们只需要执行保存即可。如果 Controller 没处理，这里可以补充逻辑。
        // 按照你现在的架构，保存操作如下：
        if (!this.save(user)) {
            throw new ServiceException("注册失败");
        }
        return user;
    }


//这里我是想加上一个override注解的，但是我不会这个是什么意思，所以就没加，应该是加上的
    public User selectByUsername(String Username) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", Username);
//        从父类中继承了getone的方法，所以这里就不要写basemapper了，直接使用this.即可
        return this.getOne(queryWrapper);
    }
//    @Override
//    public User login(User user) {
//        User dbuser = selectByUsername(user.getUsername());
//        if (dbuser == null) {
//            throw new ServiceException("用户名或者密码错误");
//        }
//        if (!user.getPassword().equals(dbuser.getPassword())) {
//            throw new ServiceException("密码错误");
//        }
//        String token = TokenUtils.createToken(dbuser.getId().toString(), dbuser.getPassword());
//        dbuser.setToken(token);
//        return dbuser;
//
//
//    }
@Override
public User login(User user) {
    User dbuser = selectByUsername(user.getUsername());
    if (dbuser == null) {
        throw new ServiceException("用户名或者密码错误");
    }

    // --- 修改开始：使用 BCrypt 校验哈希密码 ---
    // user.getPassword() 是 Controller 传来的解密后的明文
    // dbuser.getPassword() 是数据库中存储的 $2a$ 开头的哈希值
    if (!cn.hutool.crypto.digest.BCrypt.checkpw(user.getPassword(), dbuser.getPassword())) {
        throw new ServiceException("用户名或者密码错误");
    }
    // --- 修改结束 ---

    // 注意：Token 的签名建议使用角色(Role)而不是密码，因为哈希密码太长且可能包含非法字符
    String token = TokenUtils.createToken(dbuser.getId().toString(), dbuser.getRole());
    dbuser.setToken(token);

    // 返回前擦除密码，增加安全性
    dbuser.setPassword(null);
    return dbuser;
}
}

//}
//@Service
//public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
//
//    @Resource
//    private UserMapper userMapper;
//
//    /**
//     * 根据用户名查询用户
//     */
//    public User selectByUsername(String username) {
//        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("username", username);
//        return this.getOne(queryWrapper);
//    }
//
//    @Override
//    public User login(User user) {
//        // 1. 根据用户名查找数据库用户
//        User dbUser = selectByUsername(user.getUsername());
//
//        if (dbUser == null) {
//            throw new ServiceException("用户名或密码错误");
//        }
//
//        // 2. 验证密码
//        // 注意：未来这里应该接收前端 RSA 加密后的密文并解密后再对比
//        if (!user.getPassword().equals(dbUser.getPassword())) {
//            throw new ServiceException("用户名或密码错误");
//        }
//
//        // 3. 生成加密 Token
//        // 关键点：调用你修改后的方法，传入 ID 和 角色(role)
//        // 满足：使用 AES256-CBC 算法替换标准 JWT
//        String token = TokenUtils.createToken(dbUser.getId().toString(), dbUser.getRole());
//
//        // 4. 将生成的加密 Token 返回给前端
//        dbUser.setToken(token);
//
//        // 为了安全，建议返回前将对象中的密码设为 null，防止密码通过接口返回到前端控制台
//        dbUser.setPassword(null);
//
//        return dbUser;
//    }
//}