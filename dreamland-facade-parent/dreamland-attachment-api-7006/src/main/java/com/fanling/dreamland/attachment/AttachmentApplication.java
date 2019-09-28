package com.fanling.dreamland.attachment;

import com.github.tobato.fastdfs.FdfsClientConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.context.annotation.Import;
import org.springframework.jmx.support.RegistrationPolicy;

@SpringBootApplication
@EnableEurekaClient
@Import(FdfsClientConfig.class)
@MapperScan("com.fanling.dreamland.mapper")
@EnableMBeanExport(registration = RegistrationPolicy.IGNORE_EXISTING)
public class AttachmentApplication {
    public static void main(String[] args) {
        SpringApplication.run(AttachmentApplication.class, args);
    }
}
