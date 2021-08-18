package com.dkthanh.demo.config;

import com.dkthanh.demo.domain.ResourcePath;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ResourcePathConfig {
    @Bean
    public ResourcePath resourcePathConfiguration(){
        String path = System.getProperty("user.dir");
        String uploadRootPath = path + "/src/main/resources/static/";
        return new ResourcePath(uploadRootPath);
    }
}
