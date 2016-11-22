package com.medviv.auth.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.medviv.auth.repository.entity.RoleEntity;

public interface RoleRepository extends JpaRepository<RoleEntity, Serializable> {
    RoleEntity findByRoleName(String roleName);
}