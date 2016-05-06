package eu.learnpad.exception;

public abstract class LpRestException extends Exception {

	public LpRestException() {
		super();
	}

	public LpRestException(String message) {
		super(message);
	}

	public LpRestException(String message, Throwable cause) {
		super(message, cause);
	}

	public LpRestException(Throwable cause) {
		super(cause);
	}
}