package com.medviv.auth.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.medviv.auth.api.dto.Account;
import com.medviv.auth.api.exception.BadRequestException;
import com.medviv.auth.repository.AccountRepository;
import com.medviv.auth.repository.RoleRepository;
import com.medviv.auth.repository.entity.AccountEntity;
import com.medviv.auth.repository.entity.RoleEntity;
import com.medviv.auth.service.AccountService;
import com.medviv.auth.service.mapper.AccountMapper;
import com.medviv.auth.service.validation.AccountValidation;

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

    /*
     * (non-Javadoc)
     *
     * @see
     * com.medviv.auth.service.AccountService#createAccount(com.medviv.model.
     * Account)
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Account createAccount(final Account account) {
        validator.checkAccount(account);

        log.debug("create account.  email={}", account.getEmail());

        checkAccountExists(account.getEmail());

        final AccountEntity entity = mapper.toEntity(account);
        entity.setPassword(passwordEncoder.encode(account.getPassword()));

        final RoleEntity roleEntity = roleRepo.findByRoleName("ROLE_USER");
        entity.getRoles().add(roleEntity);

        final AccountEntity saved = repo.save(entity);

        return mapper.toDto(saved);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.medviv.auth.service.AccountService#updateAccount(com.medviv.model.
     * Account)
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Account updateAccount(final Account account) {
        validator.checkAccount(account);
        final String email = account.getEmail();

        log.debug("update account. email={}", email);
        final AccountEntity entity = findEntity(email);

        entity.setPassword(passwordEncoder.encode(account.getPassword()));
        entity.setEnabled(account.isEnabled());

        final AccountEntity updated = repo.save(entity);

        return mapper.toDto(updated);
    }

    private AccountEntity findEntity(final String email) {
        final AccountEntity entity = repo.findByEmail(email);

        if (entity == null) {
            throw new BadRequestException("No account found with email: " + email);
        }

        return entity;
    }

    private void checkAccountExists(final String email) {
        final AccountEntity accountEntity = repo.findByEmail(email);
        if (accountEntity != null) {
            throw new BadRequestException("This email address exists already.");
        }
    }
}
