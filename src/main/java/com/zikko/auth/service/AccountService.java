package com.zikko.auth.service;

import com.guerir.model.Account;

public interface AccountService {
    Account createAccount(Account account);

    Account updateAccount(Account account);
}