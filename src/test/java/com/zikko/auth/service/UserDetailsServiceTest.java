package com.zikko.auth.service;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.testng.annotations.Test;

import com.zikko.auth.model.Account;

public class UserDetailsServiceTest extends BaseSpringTest {
    @Autowired
    private UserDetailsService service;

    @Test
    public void testNotNull() {
        assertNotNull(service);
    }

    @Test
    public void testLoadUser() {
        final Account account = (Account) service.loadUserByUsername("tom@gmail.com");
        assertNotNull(account);
    }

    @Test(expectedExceptions = UsernameNotFoundException.class)
    public void testLoadUserException() {
        final Account account = (Account) service.loadUserByUsername("xywww@gmail.com");
        assertNull(account);
    }
}