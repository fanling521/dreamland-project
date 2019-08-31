package com.fanling.dreamland;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@MapperScan("com.fanling.dreamland.mapper")
@EnableEurekaClient
public class AttachmentApplication {
    public static void main(String[] args) {
        SpringApplication.run(AttachmentApplication.class, args);
    }
}
