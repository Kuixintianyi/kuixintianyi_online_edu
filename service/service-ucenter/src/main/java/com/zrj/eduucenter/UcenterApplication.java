package com.zrj.eduucenter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @ClassName: UcenterApplication
 * @Description:
 * @Author Ruijin Zhou
 * @Date 2022/7/31
 * @Version 1.0
 */
@ComponentScan({"com.zrj"})
@SpringBootApplication//取消数据源自动配置
@MapperScan("com.zrj.eduucenter.mapper")
@EnableSwagger2
@EnableDiscoveryClient
public class UcenterApplication {
    public static void main(String[] args) {
        SpringApplication.run(UcenterApplication.class, args);
    }
}
