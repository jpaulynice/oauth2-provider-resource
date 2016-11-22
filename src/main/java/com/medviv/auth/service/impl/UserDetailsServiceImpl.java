package com.medviv.auth.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.medviv.auth.model.Account;
import com.medviv.auth.repository.AccountRepository;
import com.medviv.auth.repository.entity.AccountEntity;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {
    private static final Logger log = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    private final AccountRepository repo;

    @Autowired
    public UserDetailsServiceImpl(final AccountRepository repo) {
        this.repo = repo;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public UserDetails loadUserByUsername(final String email) {
        log.debug("loadUserByUsername...email={}", email);

        final AccountEntity account = repo.findByEmail(email);
        if (account == null) {
            throw new UsernameNotFoundException("Invalid email and/or password.");
        }

        return new Account(account.getEmail(), account.getPassword(), account.isEnabled());
    }
}