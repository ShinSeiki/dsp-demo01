package com.apso.dsp.exception;

public class FieldException extends EntityException {

	private static final long serialVersionUID = 5421984626042149255L;

	public FieldException() {
	}

	public FieldException(String message) {
		super(message);
	}

	public FieldException(Throwable cause) {
		super(cause);
	}

	public FieldException(String message, Throwable cause) {
		super(message, cause);
	}

	public FieldException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
