package com.zikko.auth.model.exception;

public class InvalidCredentialsException extends AbstractBaseException {
    private static final long serialVersionUID = 7418750907096451796L;

    public InvalidCredentialsException(final String message) {
        super(message);
    }

    @Override
    public int getStatus() {
        return 401;
    }
}