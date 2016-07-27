/**
 *
 */
package com.zikko.auth;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import com.guerir.model.Message;
import com.zikko.auth.api.AppExceptionMapper;
import com.zikko.auth.service.BaseSpringTest;

/**
 * Test class for application exception mapper
 *
 * @author Jay Paulynice (jay.paulynice@gmail.com)
 */
public class AppExceptionMapperTest extends BaseSpringTest {
    @Autowired
    private AppExceptionMapper mapper;

    /**
     * Test mapper created properly
     */
    @Test
    public void testNotNull() {
        assertNotNull(mapper);
    }

    /**
     * Test map exceptions
     */
    @Test
    public void testExceptions() {
        Response r = mapper.toResponse(new RuntimeException());

        assertNotNull(r);
        assertEquals(r.getStatus(), 500);

        final Message m = (Message) r.getEntity();
        assertNotNull(m);
        assertEquals(m.getError_description(), "Internal server error");

        r = mapper.toResponse(new WebApplicationException());

        assertNotNull(r);
        assertEquals(r.getStatus(), 500);

        final Message m2 = (Message) r.getEntity();
        assertNotNull(m2);
        assertEquals(m2.getError_description(), "HTTP 500 Internal Server Error");
    }
}