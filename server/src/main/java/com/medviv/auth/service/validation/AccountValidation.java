package com.medviv.auth.service.validation;

import org.springframework.stereotype.Component;

import com.medviv.auth.api.dto.Account;
import com.medviv.auth.api.exception.BadRequestException;

@Component
public class AccountValidation {
    public void checkAccount(final Account account) {
        String message = null;

        if (account == null) {
            message = "Account object can not be null";
        } else if ((account.getEmail() == null) || account.getEmail().isEmpty()) {
            message = "Email is required.";
        } else if ((account.getPassword() == null) || account.getPassword().isEmpty()) {
            message = "Password is required.";
        }

        if (message != null) {
            throw new BadRequestException(message);
        }
    }
}
