package com.zikko.auth;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.web.filter.RequestContextFilter;

public class Application extends ResourceConfig {
    public Application() {
        register(RequestContextFilter.class);
        packages("com.zikko.auth.api.v1");
    }
}