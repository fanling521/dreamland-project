package com.fanling.dreamland;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.fanling.dreamland.mapper")
public class ModuleApplication {
    public static void main(String[] args) {
        SpringApplication.run(ModuleApplication.class, args);
    }
}
