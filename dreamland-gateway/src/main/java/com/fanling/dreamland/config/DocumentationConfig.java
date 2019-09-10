package com.fanling.dreamland.config;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.ArrayList;
import java.util.List;

@Component
@Primary
public class DocumentationConfig implements SwaggerResourcesProvider {
    @Override
    public List<SwaggerResource> get() {
        List<SwaggerResource> resources = new ArrayList<>();
        //  /auth-api/是网关路由，/v2/api-docs是swagger中的
        resources.add(swaggerResource("认证服务", "/auth-api/v2/api-docs"));
        resources.add(swaggerResource("文件服务", "/attachment-api/v2/api-docs"));
        resources.add(swaggerResource("基础服务", "/service-api/v2/api-docs"));
        return resources;
    }

    // 获取对应的路由资源
    private SwaggerResource swaggerResource(String name, String location) {
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setLocation(location);
        swaggerResource.setSwaggerVersion("1.0");
        return swaggerResource;
    }
}
