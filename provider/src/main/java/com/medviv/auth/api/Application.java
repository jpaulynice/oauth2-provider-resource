package com.medviv.auth.api;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.web.filter.RequestContextFilter;

import com.medviv.auth.api.v1.AccountResource;
import com.medviv.auth.api.v1.BuildResource;

public class Application extends ResourceConfig {
    public Application() {
        register(RequestContextFilter.class);
        register(AppExceptionMapper.class);
        register(AccountResource.class);
        register(BuildResource.class);
    }
}
