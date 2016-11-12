package com.medviv.auth.service;

import static org.testng.Assert.assertNotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import com.medviv.model.Account;

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
    }
}