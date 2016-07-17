package com.zikko.auth.repository.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.google.common.base.Objects;

@DynamicInsert
@DynamicUpdate
@Entity(name = "ROLES")
public class RoleEntity extends AuditEntity {
    private static final long serialVersionUID = 7275421276424881891L;

    @Id
    @GeneratedValue(generator = "ROLES_KEY_SEQ", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "ROLES_KEY_SEQ", sequenceName = "ROLES_ID_KEY_SEQ", allocationSize = 1)
    @Column(name = "ROLE_ID")
    private Long id;

    @Column(name = "ROLE_NAME")
    private String roleName;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(final String roleName) {
        this.roleName = roleName;
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
        if (!(obj instanceof RoleEntity)) {
            return false;
        }

        final RoleEntity other = (RoleEntity) obj;
        return Objects.equal(roleName, other.roleName);
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return Objects.hashCode(roleName);
    }
}
