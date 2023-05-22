package se.kth.iv1350.saleprocess.integration;

import java.util.ArrayList;
import java.util.List;

/**
 * Stores and updates information about the items in the inventory of the retail store. 
 * (Would normally be used to call the external inventory system database and fetch item information from there.) 
 */
public class InventoryRegistry {
	private List<ItemData> itemInventory = new ArrayList<ItemData>();
    
	/**
	 * Creates a package private instance with a {@link itemInventory} that contain some 
	 * <code>ItemData</code> instances.
	 */
	InventoryRegistry()	{
		addItems();
	}
	
	/**
	 * Retrieves an item with the specified identifier and sets the quantity of the item to the specified quantity.
	 * 
	 * @param identifier The identifier of the searched <code>item</code>
	 * @param soldQuantity The quantity of the <code>item</code> to be sold.
	 * @return <code>ItemDTO</code> if there is a <code>item</code> with the specified <code>identifier</code>,
	 *         <code>null</code> if there is not.
	 * @throws InvalidIdentifierException if the item with the specified identifier does not exist.
	 * @throws InventoryRegistryException when the identifier is 5 to simulate an unchecked database exception.
	 */
	public ItemDTO getItemInfo(int identifier, int soldQuantity) throws InvalidIdentifierException {
		int identifierToSimulateDatabaseError = 5;
		if (identifier == identifierToSimulateDatabaseError) {
			throw new InventoryRegistryException("Could not establish connection to inventory database when registering "
					                             + "item with identifier: " + identifierToSimulateDatabaseError);
		}
		
		for (ItemData item : itemInventory) {
			if (identifierMatches(identifier, item)) {
				return new ItemDTO (item.identifier, soldQuantity, item.price, item.vat, item.description);
			}
		}
	    throw new InvalidIdentifierException(identifier);
	}
	
	/**
	 * Updates the quantity of items in the inventory.
	 * 
	 * @param saleInfo The sale information.
	 */
	public void updateInventory(SaleInfoDTO saleInfo) {
		ItemDTO[] itemList = saleInfo.getItemList();
		for (ItemDTO itemInSale : itemList) {
			for (ItemData itemInInventory : itemInventory) {
				if (match(itemInSale, itemInInventory)) {
					 itemInInventory.quantity -= itemInSale.getQuantity();
					 break;
				}
			}
		}
	}
	
	private boolean match(ItemDTO itemInSale, ItemData itemInInventory) {
		if (itemInSale.getIdentifier() == itemInInventory.identifier) {
			return true;
		}
		return false;
	}
	
	/**
	 * Checks if the specified identifier and the identifier of the specified item are equal. 
	 * 
	 * @param searchedIdentifier The identifier used to compare with the identifier of <code>foundItem</code>. 
	 * @param foundItem The item with identifier used to compare to <code>searchedIdentifier</code>.
	 * 
	 * @return <code>true</code> if <code>searchedIdentifier</code> and identifier of <code>foundItem</code> are equal,
	 *         <code>false</code> if they are not equal.
	 */
	private boolean identifierMatches(int searchedIdentifier, ItemData foundItem) {
		if (searchedIdentifier == foundItem.identifier) {
			return true;
		}
		return false;
	}
	
	/**
	 * Adds some instances of <code>ItemData</code> to the {@link itemInventory}. 
	 */
	private void addItems() {
		itemInventory.add (new ItemData (1, 30, 14, 1.06, "Milk"));
		itemInventory.add (new ItemData (2, 6, 50, 1.12, "Frozen Pizza"));
		itemInventory.add (new ItemData (3, 10, 80, 1.25, "Ground beef"));
		itemInventory.add (new ItemData (4, 20, 40, 1.06, "Canned Tomatoes"));
		itemInventory.add (new ItemData (5, 8, 130, 1.12, "T-shirt"));
		itemInventory.add (new ItemData (6, 10, 35, 1.25, "Tooth Paste"));
	}
	
	/**
	 * Retrieves the quantity of an item from the inventory.
	 * 
	 * @param searchedItem The item.
	 * 
	 * @return The quantity of the item in the inventory.
	 */
	public int getQuantity(ItemDTO searchedItem) {
		for (ItemData item : itemInventory) {
			if (identifierMatches(searchedItem.getIdentifier(), item)) {
				return item.quantity;
			}
		}
		return 0;
	}
	
	/**
	 * Represents mutable item information stored in {@link itemInventory}.
	 * (Would normally represent the mutable data stored in the external inventory system database.)
	 */
	private static class ItemData {
		private int identifier;
		private int quantity;
		private int price;
		private double vat;
		private String description;
		
		/**
		 * Creates an instance of a mutable item.
		 * 
		 * @param identifier A mutable value unique to each item. Identifies an item. 
	     * @param quantity The mutable amount of the item in the inventory.
	     * @param price The mutable price of the item.
	     * @param vat The mutable VAT rate of the item as a multiplicative modifier.
	     * @param description The mutable name of the item.
		 */
		public ItemData (int identifier, int quantity, int price, double vat, String description) {
			this.identifier = identifier;
			this.quantity = quantity;
			this.price = price;
			this.vat = vat;
			this.description = description;
		}
	}
}
