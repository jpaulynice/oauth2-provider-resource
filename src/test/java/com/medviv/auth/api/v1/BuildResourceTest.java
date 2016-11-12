package com.medviv.auth.api.v1;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import javax.ws.rs.core.Response;

import org.testng.annotations.Test;

public class BuildResourceTest extends BaseJerseyTest {
    @Test
    public void testGetVersion() {
        final Response response = target("build").request().get();
        assertNotNull(response);
        assertEquals(response.getStatus(), 200);

        final String version = response.readEntity(String.class);
        assertEquals(version, "1.0.0-SNAPSHOT");
    }
}