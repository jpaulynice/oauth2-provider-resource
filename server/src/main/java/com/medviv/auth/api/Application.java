package com.medviv.auth.api;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.web.filter.RequestContextFilter;

public class Application extends ResourceConfig {
    public Application() {
        register(RequestContextFilter.class);
        register(AppExceptionMapper.class);
        packages("com.medviv.auth.api.v1");
    }
}
