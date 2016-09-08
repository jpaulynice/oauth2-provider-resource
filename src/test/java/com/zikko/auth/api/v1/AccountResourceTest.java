package com.zikko.auth.api.v1;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

import org.testng.annotations.Test;

import com.guerir.model.Account;

public class AccountResourceTest extends BaseJerseyTest {
    @Test
    public void testCreate() {
        final Account account = new Account();
        account.setEmail("testing@gmail.com");
        account.setPassword("testing");

        final Response response = target("accounts").request().post((Entity.json(account)), Response.class);
        final String m = response.readEntity(String.class);
        assertNotNull(m);

        assertNotNull(response);
        assertEquals(response.getStatus(), 200);
    }
}