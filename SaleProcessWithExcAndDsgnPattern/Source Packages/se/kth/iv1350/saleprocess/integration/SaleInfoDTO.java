package se.kth.iv1350.saleprocess.integration;

import java.time.LocalDateTime;

/**
 * Stores relevant sale information of a sale.
 */
public class SaleInfoDTO {
	private final ItemDTO[] itemList;
	private final int totalPrice;
	private final int totalVAT;
	private final int paidAmount;
	private final int change;
	private final LocalDateTime saleTime;
	
	/**
	 * Creates an instance that is used to pass sale information to the accounting registry
	 * and the amount of change to the view.
	 * 
	 * @param itemList The list of items in the sale.
	 * @param totalPrice The total price of the sale including VAT.
	 * @param totalVAT The total VAT of the sale.
	 * @param paidAmount The amount of cash the customer paid.
	 * @param change The amount of change to give to the customer.
	 * @param saleTime The date and time of the sale.
	 */
	public SaleInfoDTO(ItemDTO[] itemList, int totalPrice, int totalVAT, int paidAmount, int change, LocalDateTime saleTime) {
		this.itemList = itemList;
		this.totalPrice = totalPrice;
		this.totalVAT = totalVAT;
		this.paidAmount = paidAmount;
		this.change = change;
		this.saleTime = saleTime;
	}
	
	/**
	 * Retrieves the final item list of the sale.
	 * 
	 * @return The item list.
	 */
	public ItemDTO[] getItemList() {
		return itemList;
	}
	
	/**
	 * Retrieves the final total price of the sale.
	 * 
	 * @return The total price;
	 */
	public int getTotalPrice() {
		return totalPrice;
	}
	
	/**
	 * Retrieves the total VAT of the sale.
	 * 
	 * @return The total VAT.
	 */
	public int getTotalVAT() {
		return totalVAT;
	}

	/**
	 * Retrieve the amount paid by the customer.
	 *
	 * @return The amount paid.
	 */
	public int getPaidAmount() {
		return paidAmount;
	}
	
	/**
	 * Retrieve the change to give to the customer.
	 * 
	 * @return The change. 
	 */
	public int getChange() {
		return change;
	}
	
	/**
	 * Retrieve the date and time of the sale.
	 * 
	 * @return The date and time of the sale.
	 */
	public LocalDateTime getDateAndTime() {
		return saleTime;
	}
}
