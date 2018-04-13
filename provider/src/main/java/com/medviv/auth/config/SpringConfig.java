package com.medviv.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@ComponentScan(
		basePackages = { "com.medviv.auth.*" },
        excludeFilters = { @Filter(type = FilterType.REGEX, pattern = "com.medviv.auth.config.*") }
		)

@Import({ 
	SpringDataConfig.class, 
	SpringOauth2Config.class, 
	SpringSecurityConfig.class, 
	SpringWebConfig.class 
	})

@PropertySource("classpath:props/dev.properties")
public class SpringConfig {
    @Bean
    static PropertySourcesPlaceholderConfigurer propertyConfig() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
