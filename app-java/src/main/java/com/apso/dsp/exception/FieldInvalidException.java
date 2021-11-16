package com.apso.dsp.exception;

public class FieldInvalidException extends FieldException {

	private static final long serialVersionUID = 3966285764887319087L;

	public FieldInvalidException() {
	}

	public FieldInvalidException(String message) {
		super(message);
	}

	public FieldInvalidException(Throwable cause) {
		super(cause);
	}

	public FieldInvalidException(String message, Throwable cause) {
		super(message, cause);
	}

	public FieldInvalidException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
