package se.kth.iv1350.saleprocess.view;

import se.kth.iv1350.saleprocess.integration.SaleInfoDTO;
import se.kth.iv1350.saleprocess.model.SaleObserver;

/**
 * Shows the total revenue for all sales on the interface.
 */
public class TotalRevenueView implements SaleObserver {
	private int totalRevenue = 0;

	/**
	 * Prints the total cost of all sales (to System.out.).
	 */
	@Override
	public void newSale(SaleInfoDTO saleInfo) {
		totalRevenue += saleInfo.getTotalPrice();
		printCurrentTotalRevenue();
	}

	private void printCurrentTotalRevenue() {
		System.out.println("Total amount of cash money earned from all sales: " + totalRevenue + "\n");
	}
}
