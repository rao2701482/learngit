package org.mike.jerry.exception;

public class UnsupportedHttpMethodException extends Exception {

	private static final long serialVersionUID = -3419237256933447088L;

	public UnsupportedHttpMethodException() {
		super();
	}

	public UnsupportedHttpMethodException(String message) {
		super(message);
	}

	public UnsupportedHttpMethodException(Throwable cause) {
		super(cause);
	}

	public UnsupportedHttpMethodException(String message, Throwable cause) {
		super(message, cause);
	}
}
