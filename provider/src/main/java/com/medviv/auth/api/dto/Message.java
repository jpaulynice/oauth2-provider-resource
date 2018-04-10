package com.medviv.auth.api.dto;

public class Message {
    public int status;
    private String error;
    private String description;

    public Message() {}

    public Message(final int status, final String error, final String description) {
        this.status = status;
        this.error = error;
        this.description = description;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(final int status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(final String error) {
        this.error = error;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String error_description) {
        this.description = error_description;
    }

    @Override
    public String toString() {
        return "Message [status=" + status + ", error=" + error + ", description=" + description + "]";
    }
}
