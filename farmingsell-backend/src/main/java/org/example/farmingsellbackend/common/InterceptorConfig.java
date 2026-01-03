package org.example.farmingsellbackend.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * 功能：
 * 作者：大神
 * 日期： 2025/11/23 23:15
 */
@Configuration   //这个注解表示这个文件是一个配置类
public class InterceptorConfig extends WebMvcConfigurationSupport {
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterceptor())
//                下面这行代码的意思是，拦截所有路由的接口
                .addPathPatterns("/**");
//                下面这行代码的意思是放行所有带着个路由的接口，如果要添加其他新的路由，直接,"/路由"即可，不需要另起一行
//                .excludePathPatterns("/user");

        super.addInterceptors(registry);
    }

    @Bean
    public JwtInterceptor jwtInterceptor() {
        return new JwtInterceptor();
    }
}
