package com.zrj.eduorder;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @ClassName: OrderApplication
 * @Description:
 * @Author Ruijin Zhou
 * @Date 2022/8/1
 * @Version 1.0
 */
@SpringBootApplication
@ComponentScan("com.zrj")
@EnableSwagger2
@EnableFeignClients
@EnableDiscoveryClient
@MapperScan("com.zrj.eduorder.mapper")
public class OrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }

}
