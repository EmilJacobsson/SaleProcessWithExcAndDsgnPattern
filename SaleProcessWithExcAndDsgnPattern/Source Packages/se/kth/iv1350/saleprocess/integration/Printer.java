package se.kth.iv1350.saleprocess.integration;

import se.kth.iv1350.saleprocess.model.Receipt;

/**
 * Represents a printer. Used to print the receipt (to System.out).
 */
public class Printer {

	/**
	 * Prints the specified <code>Receipt</code> (to System.out). 
	 * 
	 * @param receipt The receipt to be printed.
	 */
	public void Print(Receipt receipt) {
		System.out.println(receipt.createReceipt());
	}
}
