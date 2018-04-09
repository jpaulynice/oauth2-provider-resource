package com.medviv.auth.service.mapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Component;

import com.medviv.auth.api.dto.Account;
import com.medviv.auth.api.dto.Role;
import com.medviv.auth.repository.entity.AccountEntity;
import com.medviv.auth.repository.entity.RoleEntity;

@Component
public class AccountMapper {
    public AccountEntity toEntity(final Account dto) {
        if (dto == null) {
            return null;
        }

        final AccountEntity entity = new AccountEntity();

        entity.setId(dto.getId());
        entity.setEmail(dto.getEmail());
        entity.setEnabled(dto.isEnabled());
        entity.setCreatedBy(dto.getCreatedBy());
        entity.setCreatedDate(dto.getCreatedDate());
        entity.setUpdatedBy(dto.getUpdatedBy());
        entity.setUpdatedDate(dto.getUpdatedDate());

        return entity;
    }

    public Account toDto(final AccountEntity entity) {
        if (entity == null) {
            return null;
        }

        final Account dto = new Account();

        dto.setId(entity.getId());
        dto.setEmail(entity.getEmail());
        dto.setEnabled(entity.isEnabled());
        dto.getRoles().addAll(toRoleEntities(entity.getRoles()));

        dto.setCreatedBy(entity.getCreatedBy());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setUpdatedBy(entity.getUpdatedBy());
        dto.setUpdatedDate(entity.getUpdatedDate());

        return dto;
    }

    private List<Role> toRoleEntities(final List<RoleEntity> entities) {
        if ((entities == null) || entities.isEmpty()) {
            return Collections.emptyList();
        }

        final List<Role> roles = new ArrayList<>();
        for (final RoleEntity r : entities) {
            final Role d = new Role();
            d.setId(r.getId());
            d.setName(r.getRoleName());

            roles.add(d);
        }

        return roles;
    }
}