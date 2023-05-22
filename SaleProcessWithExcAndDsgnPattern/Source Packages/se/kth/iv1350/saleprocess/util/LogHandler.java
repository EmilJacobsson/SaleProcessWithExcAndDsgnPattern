package se.kth.iv1350.saleprocess.util;

import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Handles all unchecked exception messages to print in the specified log file.
 */
public class LogHandler {
	private static final String LOG_FILE = "saleprocess-log.txt";
	private PrintWriter printToLog;

	/**
	 * Creates a new instance that creates an input-output stream to the log file.
	 * 
	 * @throws IOException if the named file exists but is a directory rather than a regular file, 
	 * does not exist but cannot be created, or cannot be opened for any other reason. 
	 * Source: https://docs.oracle.com/javase/8/docs/api/java/io/FileWriter.html#FileWriter-java.lang.String-boolean-
	 */
	public LogHandler() throws IOException {
		printToLog = new PrintWriter(new FileWriter(LOG_FILE, true), true);
	}
	
	/**
	 * Creates an exception message and prints it and the stack trace in the log file.
	 * 
	 * @param exc The exception to log.
	 */
	public void logExceptionMsg(Exception exc) {
		LocalDateTime dateAndTime = LocalDateTime.now();
		StringBuilder exceptionMsg = new StringBuilder();
		exceptionMsg.append("\n\nTime of error: ");
		exceptionMsg.append(dateAndTime);
		exceptionMsg.append("\nERROR: " + exc.getMessage());
		printToLog.println(exceptionMsg);
		exc.printStackTrace(printToLog);
	}

}
