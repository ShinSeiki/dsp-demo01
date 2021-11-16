package com.apso.dsp.exception;

public class EntityUsedException extends EntityException {

	private static final long serialVersionUID = 6733149705158497145L;

	public EntityUsedException() {
	}

	public EntityUsedException(String message) {
		super(message);
	}

	public EntityUsedException(Throwable cause) {
		super(cause);
	}

	public EntityUsedException(String message, Throwable cause) {
		super(message, cause);
	}

	public EntityUsedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
