package com.zikko.auth.service;

import com.zikko.auth.model.Account;

public interface AccountService {
    Account createAccount(Account account);

    Account updateAccount(Account account);
}