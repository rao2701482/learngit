package org.mike.jerry.exception;

public class ServerException extends RuntimeException {
	private static final long serialVersionUID = -8403743736818054912L;

	public ServerException() {
		super();
	}

	public ServerException(String message) {
		super(message);
	}

	public ServerException(Throwable cause) {
		super(cause);
	}

	public ServerException(String message, Throwable cause) {
		super(message, cause);
	}
}
