package com.medviv.auth.repository.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Type;

@DynamicInsert
@DynamicUpdate
@Entity(name = "ACCOUNTS")
public class AccountEntity extends AuditEntity {
    private static final long serialVersionUID = 1588639101961883557L;

    @Id
    @GeneratedValue(generator = "ACCOUNTS_KEY_SEQ", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "ACCOUNTS_KEY_SEQ", sequenceName = "ACCOUNTS_ID_KEY_SEQ", allocationSize = 1)
    @Column(name = "ACCOUNT_ID")
    private Long id;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "ENABLED")
    @Type(type = "yes_no")
    private boolean enabled = true;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "ACCOUNTS_ROLES", joinColumns = { @JoinColumn(name = "ACCOUNT_ID") },
            inverseJoinColumns = { @JoinColumn(name = "ROLE_ID") })
    private final List<RoleEntity> roles = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public List<RoleEntity> getRoles() {
        return roles;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(final boolean enabled) {
        this.enabled = enabled;
    }
}