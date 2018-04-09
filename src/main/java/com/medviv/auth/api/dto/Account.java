package com.medviv.auth.api.dto;

import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Objects;

public class Account extends Audit {
    private Long id;
    private String email;
    private String password;
    private boolean enabled;
    private final List<Role> roles = new ArrayList<>();

    public Account() {
        this.enabled = true;
    }

    public Account(final String email, final String password, final boolean enabled) {
        this.email = email;
        this.password = password;
        this.enabled = enabled;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEnabled(final boolean enabled) {
        this.enabled = enabled;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(final String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Account)) {
            return false;
        }
        final Account other = (Account) obj;
        return Objects.equal(email, other.email);
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return Objects.hashCode(email);
    }

    @Override
    public String toString() {
        return "Account [email=" + email + ", enabled=" + enabled + "]";
    }

    public List<Role> getRoles() {
        return roles;
    }
}
