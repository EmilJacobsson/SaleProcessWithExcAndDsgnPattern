package se.kth.iv1350.saleprocess.view;

import java.time.LocalDateTime;

/**
 * Handles all exception messages to show to the user.
 */
public class ExceptionMessageHandler {
	
	/**
	 * Creates an error message for the user to see.
	 * 
	 * @param msg The error message.
	 */
	public void showExceptionMsg(String msg) {
		LocalDateTime dateAndTime = LocalDateTime.now();
		StringBuilder exceptionMsg = new StringBuilder();
		exceptionMsg.append("\nTime of error: ");
		exceptionMsg.append(dateAndTime);
		exceptionMsg.append("\nERROR: " + msg);
		System.out.println(exceptionMsg);
	}
}
