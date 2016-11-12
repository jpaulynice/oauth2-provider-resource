package com.medviv.auth.service.mapper;

import org.springframework.stereotype.Component;

import com.medviv.auth.repository.entity.AccountEntity;
import com.medviv.model.Account;

/**
 * @author Jay Paulynice (jay.paulynice@gmail.com)
 */
@Component
public class AccountMapper {

    /**
     * Map from user dto object to user entity object
     *
     * @param dto the dto object to map
     * @return the entity object
     */
    public AccountEntity toEntity(final Account dto) {
        if (dto == null) {
            return null;
        }

        final AccountEntity entity = new AccountEntity();
        entity.setEmail(dto.getEmail());

        return entity;
    }

    /**
     * Map from user entity object to user dto object
     *
     * @param entity the entity to map
     * @return the dto object
     */
    public Account toDto(final AccountEntity entity) {
        if (entity == null) {
            return null;
        }

        final Account dto = new Account();
        dto.setEmail(entity.getEmail());
        dto.setCreatedBy(entity.getCreatedBy());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setUpdatedBy(entity.getUpdatedBy());
        dto.setUpdatedDate(entity.getUpdatedDate());

        return dto;
    }
}