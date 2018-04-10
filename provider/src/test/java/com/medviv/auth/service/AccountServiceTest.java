package com.medviv.auth.service;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertTrue;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import com.medviv.auth.api.dto.Account;
import com.medviv.auth.api.exception.BadRequestException;


public class AccountServiceTest extends BaseSpringTest {
    @Autowired
    private AccountService accountService;

    @Test
    public void test() {
        final Account a = new Account();
        a.setEmail("test@gmail.com");
        a.setPassword("abc123");

        final Account saved = accountService.createAccount(a);
        assertNotNull(saved);
        assertEquals(a.getEmail(), saved.getEmail());
        assertTrue(a.isEnabled());
        assertNull(saved.getPassword());
    }

    @Test(expectedExceptions = BadRequestException.class)
    public void testBadRequest() {
        final Account a = new Account();
        a.setEmail("admin@gmail.com");
        a.setPassword("abc123");

        accountService.createAccount(a);
    }

    @Test(expectedExceptions = BadRequestException.class)
    public void testUpdateBad() {
        final Account a = new Account();
        a.setEmail("hiyya@medviv.io");
        a.setPassword("password");

        accountService.updateAccount(a);
    }

    @Test
    public void testUpdate() {
        // save account
        final Account a = new Account();
        a.setEmail("test@medviv.io");
        a.setPassword("password");

        final Account saved = accountService.createAccount(a);
        assertNotNull(saved);
        assertEquals(a.getEmail(), saved.getEmail());

        // update
        saved.setEnabled(false);
        saved.setPassword("pw123456");

        final Account updated = accountService.updateAccount(saved);
        assertNotNull(updated);
        assertFalse(updated.isEnabled());
    }
}