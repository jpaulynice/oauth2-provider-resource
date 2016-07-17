package com.zikko.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zikko.auth.repository.entity.RoleEntity;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    RoleEntity findByRoleName(String roleName);
}