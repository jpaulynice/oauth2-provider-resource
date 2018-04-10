package com.medviv.auth.api.dto;

import java.util.Date;

public abstract class Audit {
    private Date createdDate;
    private String createdBy;
    private Date updatedDate;
    private String updatedBy;

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(final Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(final String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(final Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(final String updatedBy) {
        this.updatedBy = updatedBy;
    }

    @Override
    public String toString() {
        return "AuditDto [createdDate=" + createdDate + ", createdBy=" + createdBy + ", updatedDate=" + updatedDate
                + ", updatedBy=" + updatedBy + "]";
    }
}
