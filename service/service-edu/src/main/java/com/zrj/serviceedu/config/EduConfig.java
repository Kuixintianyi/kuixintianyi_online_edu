package com.zrj.serviceedu.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @ClassName: MyBatisPlusConfig
 * @Description:
 * @Author Ruijin Zhou
 * @Date 2022/7/19
 * @Version 1.0
 */

@Configuration
@MapperScan("com.zrj.serviceedu.mapper")
public class EduConfig {

    //这个是由MP自带的分页插件实现的，本质是在配置类中配置一个拦截器
    @Bean
    public PaginationInterceptor paginationInterceptor () {
        return new PaginationInterceptor();
    }
}
