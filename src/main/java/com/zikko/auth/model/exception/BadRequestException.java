package com.zikko.auth.model.exception;

public class BadRequestException extends AbstractBaseException {
    private static final long serialVersionUID = -2083433108611394754L;

    public BadRequestException(final String message) {
        super(message);
    }

    @Override
    public int getStatus() {
        return 400;
    }
}