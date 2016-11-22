package com.medviv.auth.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.medviv.auth.repository.AccountRepository;
import com.medviv.auth.repository.RoleRepository;
import com.medviv.auth.repository.entity.AccountEntity;
import com.medviv.auth.repository.entity.RoleEntity;
import com.medviv.auth.service.AccountService;
import com.medviv.auth.service.mapper.AccountMapper;
import com.medviv.auth.service.validation.AccountValidation;
import com.medviv.exception.BadRequestException;
import com.medviv.model.Account;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {
    private static final Logger log = LoggerFactory.getLogger(AccountServiceImpl.class);

    private final AccountRepository repo;
    private final RoleRepository roleRepo;
    private final AccountMapper mapper;
    private final AccountValidation validator;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AccountServiceImpl(final AccountRepository repo, final RoleRepository roleRepo, final AccountMapper mapper,
            final AccountValidation validator, final PasswordEncoder passwordEncoder) {
        this.repo = repo;
        this.roleRepo = roleRepo;
        this.mapper = mapper;
        this.validator = validator;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Account createAccount(final Account account) {
        log.debug("create account");

        validator.checkAccount(account);
        checkAccountExists(account.getEmail());

        final AccountEntity entity = mapper.toEntity(account);
        entity.setPassword(passwordEncoder.encode(account.getPassword()));

        final RoleEntity roleEntity = roleRepo.findByRoleName("ROLE_USER");
        entity.getRoles().add(roleEntity);

        final AccountEntity saved = repo.save(entity);

        return mapper.toDto(saved);
    }

    private void checkAccountExists(final String email) {
        final AccountEntity accountEntity = repo.findByEmail(email);
        if (accountEntity != null) {
            throw new BadRequestException("This email address exists already.");
        }
    }
}