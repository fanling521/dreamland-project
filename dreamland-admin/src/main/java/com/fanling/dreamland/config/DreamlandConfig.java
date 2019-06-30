package com.fanling.dreamland.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
@Setter
public class DreamlandConfig {
    private String name;
    private String version;
}
