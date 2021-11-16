package com.apso.dsp.exception;

public class Demo01Exception extends RuntimeException {

	private static final long serialVersionUID = -3857946260198473292L;

	public Demo01Exception() {
	}

	public Demo01Exception(String message) {
		super(message);
	}

	public Demo01Exception(Throwable cause) {
		super(cause);
	}

	public Demo01Exception(String message, Throwable cause) {
		super(message, cause);
	}

	public Demo01Exception(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
