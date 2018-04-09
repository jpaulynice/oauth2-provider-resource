package com.medviv.auth.service.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import com.medviv.auth.api.dto.Account;
import com.medviv.auth.api.exception.BadRequestException;
import com.medviv.auth.service.BaseSpringTest;

public class AccountValidationTest extends BaseSpringTest {
    @Autowired
    private AccountValidation validator;

    @Test(expectedExceptions = BadRequestException.class)
    public void testNewObject() {
        validator.checkAccount(new Account());
    }

    @Test(expectedExceptions = BadRequestException.class)
    public void testNull() {
        validator.checkAccount(null);
    }

    @Test(expectedExceptions = BadRequestException.class)
    public void testPasswordNull() {
        final Account a = new Account();
        a.setEmail("test@gmail.com");
        validator.checkAccount(a);
    }

    @Test(expectedExceptions = BadRequestException.class)
    public void testEmailEmpty() {
        final Account a = new Account();
        a.setEmail("");
        validator.checkAccount(a);
    }

    @Test(expectedExceptions = BadRequestException.class)
    public void testPwEmpty() {
        final Account a = new Account();
        a.setEmail("test@gmail.com");
        a.setPassword("");
        validator.checkAccount(a);
    }

    @Test
    public void testPwNull() {
        final Account a = new Account();
        a.setEmail("test@gmail.com");
        a.setPassword("password");

        validator.checkAccount(a);
    }
}
