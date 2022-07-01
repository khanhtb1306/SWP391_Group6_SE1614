package com.SE1614.Group6.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        Path avatarUpLoadDir = Paths.get("./user_avatar/");
        String avatarUpLoadPath = avatarUpLoadDir.toFile().getAbsolutePath();

        registry.addResourceHandler("/user_avatar/**").addResourceLocations("file:/"+avatarUpLoadPath+"/");
    }
}
