package com.sap.webi.sample;

public class BI4Exception extends RuntimeException {

	/**
	 * serial
	 */
	private static final long serialVersionUID = 1L;

	public BI4Exception() {
	}

	public BI4Exception(String message, Throwable cause) {
		super(message, cause);
	}

	public BI4Exception(String message) {
		super(message);
	}

	public BI4Exception(Throwable cause) {
		super(cause);
	}
}
