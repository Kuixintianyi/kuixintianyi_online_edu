package com.zrj.serviceedu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @ClassName: ServiceEduApplication
 * @Description:
 * @Author Ruijin Zhou
 * @Date 2022/7/19
 * @Version 1.0
 */
@SpringBootApplication
@ComponentScan("com.zrj")
@EnableSwagger2
@EnableDiscoveryClient
@EnableFeignClients //调用feign
public class ServiceEduApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceEduApplication.class, args);
    }
}
