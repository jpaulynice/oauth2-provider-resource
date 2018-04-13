package com.medviv.rest.config;

import org.glassfish.jersey.server.ResourceConfig;
import com.medviv.rest.v1.BuildResource;

public class JerseyApplication extends ResourceConfig {
    public JerseyApplication() {
        register(BuildResource.class);
    }
}
