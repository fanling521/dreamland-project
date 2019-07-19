package com.fanling.dreamland.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * http://localhost:7003/doc.html
 * http://localhost:7003/swagger-ui.html
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("技术产权开放平台管理中心接口测试平台")
                .description("描述")
                .contact(new Contact("樊领", "https://github.com/fanling521", "862844083@qq.com"))
                //网关的地址，会转发
                .termsOfServiceUrl("http://localhost:7003")
                .version("1.0")
                .build();
    }
}
