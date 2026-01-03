package org.example.farmingsellbackend.utils;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.example.farmingsellbackend.entity.User;
import org.example.farmingsellbackend.mapper.UserMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Date;

/**
 * 功能：这个文件的主要作用是用于生成token
 * 作者：大神
 * 日期： 2025/11/19 23:41
 */
@Component
//通过上面这个注解，将这个代码注入到springboot容器中去
public class TokenUtils {
    private static final String AES_KEY = "12345678901234561234567890123456"; // 32位密钥
    private static final String AES_IV  = "1234567890123456";                 // 16位IV
    private static UserMapper staticUserMapper;

    @Resource
    UserMapper userMapper;

    @PostConstruct
    public void setUserService() {
        staticUserMapper = userMapper;
    }

//    public static String createToken(String userId, String role) {
//        // 构造载荷内容：userId + 过期时间戳
//        String content = userId + ":" + (System.currentTimeMillis() + 2 * 3600 * 1000);
//
//        // 使用你定义的密钥和 IV 进行 AES-256-CBC 加密
//        cn.hutool.crypto.symmetric.AES aes = cn.hutool.crypto.SecureUtil.aes(AES_KEY.getBytes());
//        aes.setIv(AES_IV.getBytes());
//
//        // 返回加密后的十六进制字符串作为 Token
//        return aes.encryptHex(content);
//    }
    public static String createToken(String userId, String sign) {
        return JWT.create().withAudience(userId) // 将 user id 保存到 token 里面，作为载荷
                .withExpiresAt(DateUtil.offsetHour(new Date(), 2)) // 2小时后token过期，这个写法表示当前时间往后推移俩小时的过期时间
                .sign(Algorithm.HMAC256(sign)); // 以 password 作为 token 的密钥
    }

    /**
     * 获取当前登录的用户信息
     *
     * @return user对象
     */
    public static User getCurrentUser() {
        //只要这次请求的接口里面有token，，就可以通过token拿到当前请求用户的所有信息
        try {
            HttpServletRequest request = (HttpServletRequest) ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            String token = request.getHeader("token");
            if (StrUtil.isNotBlank(token)) {
                String userId = JWT.decode(token).getAudience().get(0);
                return staticUserMapper.selectById(Integer.valueOf(userId));
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }
}
