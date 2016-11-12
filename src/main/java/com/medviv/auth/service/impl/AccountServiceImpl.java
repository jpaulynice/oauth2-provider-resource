package com.medviv.auth.service.impl;

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
        validator.checkAccount(account);

        final AccountEntity e = repo.findByEmail(account.getEmail());
        if (e != null) {
            throw new BadRequestException("This email address already exists.");
        }

        final AccountEntity entity = mapper.toEntity(account);
        entity.setPassword(passwordEncoder.encode(account.getPassword()));

        final RoleEntity roleEntity = roleRepo.findByRoleName("ROLE_USER");
        entity.getRoles().add(roleEntity);

        final AccountEntity saved = repo.save(entity);

        return mapper.toDto(saved);
    }
}