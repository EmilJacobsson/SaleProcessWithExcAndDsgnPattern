package se.kth.iv1350.saleprocess.integration;

/**
 * An item in the sale. Stores relevant item information. 
 */
public class ItemDTO {
	private final int identifier;
	private final int quantity;
	private final int price;
	private final double vat;
	private final String description;

	/**
	 * Creates an instance of an item.
	 * 
	 * @param identifier A value unique to each item. Identifies an item. 
	 * @param quantity The amount of the item in the sale.
	 * @param price The price of the item.
	 * @param vat The VAT rate of the item as a multiplicative modifier.
	 * @param description The name of the item.
	 */
	public ItemDTO(int identifier, int quantity, int price, double vat, String description) {
		this.identifier = identifier;
		this.quantity = quantity;
		this.price = price;
		this.vat = vat;
		this.description = description;
	}
	
	
	/**
	 * Checks if two <code>ItemDTO</code> are equal by comparing their identifiers.
	 * 
	 * @param otherObject The <code>ItemDTO</code> to compare with this <code>ItemDTO</code>.
	 * 
	 * @return <code>true</code> if the identifier of this <code>ItemDTO</code> is equal to the 
	 *         identifier of the specified <code>ItemDTO</code>, <code>false</code> if they are not.
	 */
	@Override
	public boolean equals(Object otherObject) {
		if (otherObject == null) {
			return false;
		}
		
		if (getClass() != otherObject.getClass()) {
			return false;
		}
		
		final ItemDTO other = (ItemDTO) otherObject;
		if (identifier != other.identifier) {
			return false;
		}
		return true;
	}
	
	/**
	 * Retrieves the item price without VAT applied.
	 * 
	 * @return The item price.
	 */
	public int getPrice() {
		return price;
	}
	
	/**
	 * Retrieves the item description.
	 * 
	 * @return The item description.
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * Retrieves the item VAT rate.
	 * 
	 * @return The item VAT rate as a multiplicative modifier.
	 */
	public double getVAT() {
		return vat;
	}
	
	/**
	 * Retrieve the item amount.
	 * 
	 * @return The item quantity.
	 */
	public int getQuantity() {
		return quantity;
	}
	
	/**
	 * Retrieves the item identifier.
	 * 
	 * @return The item identifier.
	 */
	public int getIdentifier() {
		return identifier;
	}
}
