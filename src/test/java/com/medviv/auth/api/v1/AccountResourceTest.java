package com.medviv.auth.api.v1;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

import org.testng.annotations.Test;

import com.medviv.model.Account;
import com.medviv.model.Message;

public class AccountResourceTest extends BaseJerseyTest {
    @Test
    public void testCreateNull() {
        // null object
        final Response response = target("accounts").request().post((Entity.json(null)), Response.class);
        assertNotNull(response);
        assertEquals(response.getStatus(), 400);

        final Message error = response.readEntity(Message.class);
        assertEquals(error.getError_description(), "Account object can not be null");
        assertEquals(error.getError(), "Account object can not be null");
    }

    @Test
    public void testCreateNoEmailPw() {
        // no email no password
        final Account account = new Account();
        final Response response = target("accounts").request().post((Entity.json(account)), Response.class);
        assertNotNull(response);
        assertEquals(response.getStatus(), 400);

        final Message error = response.readEntity(Message.class);
        assertEquals(error.getError_description(), "Email is required.");
        assertEquals(error.getError(), "Email is required.");
    }

    @Test
    public void testCreateNoPw() {
        // no password
        final Account account = new Account();
        account.setEmail("testing@gmail.com");
        final Response response = target("accounts").request().post((Entity.json(account)), Response.class);
        assertNotNull(response);
        assertEquals(response.getStatus(), 400);

        final Message error = response.readEntity(Message.class);
        assertEquals(error.getError_description(), "Password is required.");
        assertEquals(error.getError(), "Password is required.");
    }

    @Test
    public void testCreate() {
        // email and pw
        final Account account = new Account();
        account.setEmail("hello@medviv.io");
        account.setPassword("testing");
        final Response response = target("accounts").request().post((Entity.json(account)), Response.class);

        assertNotNull(response);
        assertEquals(response.getStatus(), 200);

        final Account created = response.readEntity(Account.class);
        assertNotNull(created);

        assertEquals(created.getEmail(), account.getEmail());
        assertNull(created.getPassword());
    }

    @Test
    public void testUpdate() {
        // create account
        final Account account = new Account();
        account.setEmail("hey@medviv.io");
        account.setPassword("testing");
        Response response = target("accounts").request().post((Entity.json(account)), Response.class);

        assertNotNull(response);
        assertEquals(response.getStatus(), 200);

        final Account created = response.readEntity(Account.class);
        assertNotNull(created);

        // update account
        account.setEnabled(false);
        response = target("accounts/" + created.getId()).request().put((Entity.json(account)), Response.class);

        assertNotNull(response);
        assertEquals(response.getStatus(), 200);
    }
}