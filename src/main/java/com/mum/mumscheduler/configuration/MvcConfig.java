package com.mum.mumscheduler.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry registry)
    {
        registry.addViewController("/").setViewName("admin-dashboard");
        registry.addViewController("/index").setViewName("admin-dashboard");
        registry.addViewController("/login").setViewName("login");

    }
}
