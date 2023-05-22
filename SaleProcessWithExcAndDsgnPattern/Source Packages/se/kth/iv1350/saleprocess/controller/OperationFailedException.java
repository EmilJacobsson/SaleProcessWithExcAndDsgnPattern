package se.kth.iv1350.saleprocess.controller;

/**
 * Thrown when a method fails but the cause is unknown.
 */
@SuppressWarnings("serial")
public class OperationFailedException extends Exception {

	/**
	 * Creates an instance with the specified exception message and root cause.
	 * 
	 * @param msg The exception message.
	 * @param rootCause The exception that created this instance.
	 */
	public OperationFailedException(String msg, Exception rootCause) {
		super(msg, rootCause);
	}

}
