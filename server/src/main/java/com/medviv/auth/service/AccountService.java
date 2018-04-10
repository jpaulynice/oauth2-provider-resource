package com.medviv.auth.service;

import com.medviv.auth.api.dto.Account;

public interface AccountService {
    Account createAccount(Account account);

    Account updateAccount(Account account);
}
