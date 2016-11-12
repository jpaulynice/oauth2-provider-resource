package com.medviv.auth.service.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import com.medviv.auth.service.BaseSpringTest;
import com.medviv.exception.BadRequestException;
import com.medviv.model.Account;

public class AccountValidationTest extends BaseSpringTest {
    @Autowired
    private AccountValidation validator;

    @Test(expectedExceptions = BadRequestException.class)
    public void testNull() {
        validator.checkAccount(null);
        validator.checkAccount(new Account());
    }

    @Test(expectedExceptions = BadRequestException.class)
    public void testEmailNull() {
        validator.checkAccount(new Account());
    }

    @Test(expectedExceptions = BadRequestException.class)
    public void testPasswordNull() {
        final Account a = new Account();
        a.setEmail("test@gmail.com");
        validator.checkAccount(a);
    }
}