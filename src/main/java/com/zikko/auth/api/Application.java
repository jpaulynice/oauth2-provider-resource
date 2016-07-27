package com.zikko.auth.api;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.web.filter.RequestContextFilter;

public class Application extends ResourceConfig {
    public Application() {
        register(RequestContextFilter.class);
        register(AppExceptionMapper.class);
        packages("com.zikko.auth.api.v1");
    }
}