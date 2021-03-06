package org.kryptose.exceptions;

/**
 * Any error sent to the client that's resolvable by an error message and a "please try again".
 * 
 * @author jshi
 */
public class RecoverableException extends Exception {
	private static final long serialVersionUID = 4384928253759960805L;

	public RecoverableException() {
		super("An error occurred. Please try again.");
		this.setStackTrace(new StackTraceElement[0]);
	}

	public RecoverableException(String message) {
		super(message);
		this.setStackTrace(new StackTraceElement[0]);
	}

}
