package com.medviv.rest.config;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.web.filter.RequestContextFilter;

import com.medviv.rest.provider.AppExceptionMapper;

public class JerseyApplication extends ResourceConfig {
    public JerseyApplication() {
        register(RequestContextFilter.class);
        register(AppExceptionMapper.class);
        register(JacksonFeature.class);

        packages("com.medviv.rest.v1");
    }
}
