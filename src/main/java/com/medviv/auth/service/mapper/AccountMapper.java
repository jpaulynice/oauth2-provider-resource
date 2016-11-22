package com.medviv.auth.service.mapper;

import org.springframework.stereotype.Component;

import com.medviv.auth.repository.entity.AccountEntity;
import com.medviv.model.Account;

@Component
public class AccountMapper {
    public AccountEntity toEntity(final Account dto) {
        if (dto == null) {
            return null;
        }

        final AccountEntity entity = new AccountEntity();
        entity.setEmail(dto.getEmail());
        entity.setEnabled(dto.isEnabled());

        return entity;
    }

    public Account toDto(final AccountEntity entity) {
        if (entity == null) {
            return null;
        }

        final Account dto = new Account();
        dto.setEmail(entity.getEmail());
        dto.setEnabled(entity.isEnabled());

        return dto;
    }
}