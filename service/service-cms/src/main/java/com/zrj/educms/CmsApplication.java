package com.zrj.educms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @ClassName: CmsApplication
 * @Description:
 * @Author Ruijin Zhou
 * @Date 2022/7/30
 * @Version 1.0
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.zrj"})
@MapperScan("com.zrj.educms.mapper")
@EnableSwagger2
public class CmsApplication {
    public static void main(String[] args) {
        SpringApplication.run(CmsApplication.class, args);
    }
}
