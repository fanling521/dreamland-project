package com.fanling.dreamland.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableEurekaClient
@EnableZuulProxy
public class GetWayApplication {
    public static void main(String[] args) {
        SpringApplication.run(GetWayApplication.class, args);
        System.out.println("zuul gateway start...");
    }
}
