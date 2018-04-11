package com.medviv.rest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@ComponentScan(basePackages = { "com.medviv.*" },
               excludeFilters = { @Filter(type = FilterType.REGEX, 
                                   pattern = { "com.medviv.rest.config.*"}
                                         )})
@Import({ SpringOauth2Config.class })
@PropertySource("classpath:app.properties")
public class SpringConfig {
    @Bean
    static PropertySourcesPlaceholderConfigurer propertyConfig() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
