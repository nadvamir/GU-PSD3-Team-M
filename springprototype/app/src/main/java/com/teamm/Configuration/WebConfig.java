package com.teamm.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@EnableWebMvc
@Configuration
@ComponentScan(basePackages = "com.teamm")
public class WebConfig extends WebMvcConfigurerAdapter {
    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
            
            registry
                    .addResourceHandler("/static/**")
                    .addResourceLocations("/static/");                
            
    }        
}
