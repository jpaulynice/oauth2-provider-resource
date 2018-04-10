package com.medviv.auth.api.exception;

public abstract class AbstractBaseException extends RuntimeException {
	private static final long serialVersionUID = 7934168968831314440L;
	private final String message;

	public abstract int getStatus();

	public AbstractBaseException(final String message) {
		super(message);
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}
}
