package com.medviv.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import com.medviv.auth.config.SpringOauth2Config;
import com.medviv.auth.config.SpringSecurityConfig;

@Configuration
@ComponentScan(basePackages = { "com.medviv.auth.*" },
        excludeFilters = @Filter(type = FilterType.REGEX, pattern = "com.medviv.auth.config.*"))
@Import({ SpringDataTestConfig.class, SpringSecurityConfig.class, SpringOauth2Config.class })
@PropertySource("classpath:props/dev.properties")
public class SpringTestConfig {
    @Bean
    static PropertySourcesPlaceholderConfigurer propertyConfig() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}