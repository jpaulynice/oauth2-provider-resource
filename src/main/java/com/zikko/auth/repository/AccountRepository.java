package com.zikko.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zikko.auth.repository.entity.AccountEntity;

public interface AccountRepository extends JpaRepository<AccountEntity, Long> {
    AccountEntity findByEmail(String email);
}