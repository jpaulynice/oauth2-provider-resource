package com.medviv.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.medviv.auth.repository.entity.AccountEntity;

public interface AccountRepository extends JpaRepository<AccountEntity, Long> {
    AccountEntity findByEmail(String email);
}