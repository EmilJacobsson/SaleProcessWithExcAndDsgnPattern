package se.kth.iv1350.saleprocess.model;

/**
 * Stores information about the item added to the sale and the total price of the sale. 
 * Used to return primitive values to the view.
 */
public class ItemSaleInfoDTO {
	private final int totalPrice;
	private final int price;
	private final String description;

	/**
	 * Creates an instance.
	 * 
	 * @param totalPrice The totalPrice of the sale so far.
	 * @param price The price of the item.
	 * @param description The name of the item.
	 */
	public ItemSaleInfoDTO(int totalPrice, int price, String description) {
		this.totalPrice = totalPrice;
		this.price = price;
		this.description = description;
	}
	

	/**
	 * Retrieve the total price of the sale.
	 * 
	 * @return The total price of the sale.
	 */
	public int getTotalPrice() {
		return totalPrice;
	}
	
	/**
	 * Retrieve the item price without VAT applied.
	 * 
	 * @return The item price.
	 */
	public int getPrice() {
		return price;
	}
	
	/**
	 * Retrieve the item description.
	 * 
	 * @return The item description.
	 */
	public String getDescription() {
		return description;
	}

}
