package se.kth.iv1350.saleprocess.model;

import se.kth.iv1350.saleprocess.integration.SaleInfoDTO;

/**
 * Observer interface for receiving notifications about completed sales. 
 */
public interface SaleObserver {
	
	/**
	 * Invoked when a sale has been paid.
	 * 
	 * @param saleInfo Contains all relevant information about the sale.
	 */
	void newSale (SaleInfoDTO saleInfo);
}
