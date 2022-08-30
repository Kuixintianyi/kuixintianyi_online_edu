package com.zrj.edustatistics;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @ClassName: StaApplication
 * @Description:
 * @Author Ruijin Zhou
 * @Date 2022/8/2
 * @Version 1.0
 */
@SpringBootApplication
@MapperScan("com.zrj.edustatistics.mapper")
@ComponentScan("com.zrj")
@EnableDiscoveryClient
@EnableFeignClients
@EnableSwagger2
@EnableScheduling
public class StaApplication {
    public static void main(String[] args) {
        SpringApplication.run(StaApplication.class, args);
    }
}
