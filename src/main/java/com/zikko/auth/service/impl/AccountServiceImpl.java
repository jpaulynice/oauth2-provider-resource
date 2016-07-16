package com.zikko.auth.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.guerir.model.Account;
import com.guerir.repository.AccountRepository;
import com.guerir.repository.entity.AccountEntity;
import com.zikko.auth.service.AccountService;

@Service
@Transactional
public class AccountServiceImpl implements UserDetailsService, AccountService {
    private final AccountRepository repo;

    @Autowired
    public AccountServiceImpl(final AccountRepository repo) {
        this.repo = repo;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public UserDetails loadUserByUsername(final String email) throws UsernameNotFoundException {
        final AccountEntity account = repo.findByEmail(email);
        if (account == null) {
            throw new UsernameNotFoundException("Invalid email and/or password.");
        }

        return new Account(account.getEmail(), account.getPassword(), account.isEnabled());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public Account createAccount(final Account account) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Account updateAccount(final Account account) {
        // TODO Auto-generated method stub
        return null;
    }
}