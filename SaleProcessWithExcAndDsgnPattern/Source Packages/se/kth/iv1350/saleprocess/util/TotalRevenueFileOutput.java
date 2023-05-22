package se.kth.iv1350.saleprocess.util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import se.kth.iv1350.saleprocess.integration.SaleInfoDTO;
import se.kth.iv1350.saleprocess.model.SaleObserver;

/**
 * Shows the total revenue of all sales in a log file.
 */
public class TotalRevenueFileOutput implements SaleObserver {
	private static final String LOG_FILE = "totalRevenue-log.txt";
	private PrintWriter printToLog;
	private int totalRevenue = 0;
	
	/**
	 * Creates a new instance that creates an input-output stream to the log file.
	 * 
	 * @throws IOException if the named file exists but is a directory rather than a regular file, 
	 * does not exist but cannot be created, or cannot be opened for any other reason. 
	 * Source: https://docs.oracle.com/javase/8/docs/api/java/io/FileWriter.html#FileWriter-java.lang.String-boolean-
	 */
	public TotalRevenueFileOutput() throws IOException{
		printToLog = new PrintWriter(new FileWriter(LOG_FILE, true), true);
	}

	/**
	 * Prints the total cost of all sales to the specified log file.
	 */
	@Override
	public void newSale(SaleInfoDTO saleInfo) {
		totalRevenue += saleInfo.getTotalPrice();
		printCurrentTotalRevenue();
	}

	private void printCurrentTotalRevenue() {
		printToLog.println("Total amount of cash money earned from all sales: " + totalRevenue + "\n");
	}

}
