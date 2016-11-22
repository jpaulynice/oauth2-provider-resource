package com.medviv.auth.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.medviv.auth.repository.entity.AccountEntity;

public interface AccountRepository extends JpaRepository<AccountEntity, Serializable> {
    AccountEntity findByEmail(String email);
}