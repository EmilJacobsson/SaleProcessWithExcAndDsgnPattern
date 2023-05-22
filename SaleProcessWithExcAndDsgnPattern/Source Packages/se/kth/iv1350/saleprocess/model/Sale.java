package se.kth.iv1350.saleprocess.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import se.kth.iv1350.saleprocess.integration.ItemDTO;
import se.kth.iv1350.saleprocess.integration.SaleInfoDTO;
import se.kth.iv1350.saleprocess.integration.Printer;
import java.lang.Math;

/**
 * One single sale made by one single customer.
 */
public class Sale {
	private List<ItemDTO> itemList = new ArrayList<ItemDTO>();
	private double totalPrice;
	private LocalDateTime saleTime;
	private List<SaleObserver> saleObservers = new ArrayList<SaleObserver>();
	
	/**
	 * Creates a new instance and saves the date and time of the sale.
	 */
	public Sale() {
		saleTime = LocalDateTime.now();
	}
	
	/**
	 * Adds the specified <code>ItemDTO</code> to the sale or if the specified <code>ItemDTO</code> 
	 * is already in the sale, updates the quantity of the specified <code>ItemDTO</code> in the sale.
	 * Also calculates the total price of the sale.
	 * 
	 * @param item The <code>ItemDTO</code> to be added to the sale.
	 * 
	 * @return The <code>ItemSaleInfoDTO</code> which contain information to be printed by view 
	 *         (to System.out).
	 */
	public ItemSaleInfoDTO addItemToSale(ItemDTO item) {
		boolean itemAlreadyInSale = updateQuantityAndTotalPrice(item);
		
		if (!itemAlreadyInSale) {
			itemList.add(item);
			calculateTotalPrice(item);
		}
		ItemSaleInfoDTO itemSaleInfo = new ItemSaleInfoDTO(getTotalPrice(), item.getPrice(), 
				                                           item.getDescription());
		return itemSaleInfo;
	}
	
	/**
	 * Ends the sale by creating a <code>SaleInfoDTO</code> that contain all relevant sale information.
	 * Also calculates change and total VAT for the sale.
	 * 
	 * @param paidAmount The amount paid by the customer.
	 * @return The sale information.
	 */
	public SaleInfoDTO endSale(int paidAmount) {
		int change = calculateChange(paidAmount);
		int totalVAT = calculateTotalVAT();
		SaleInfoDTO saleInfo = new SaleInfoDTO(getItemList(), getTotalPrice(), totalVAT, paidAmount,
				                               change, saleTime);
		notifyObservers(saleInfo);
		return saleInfo;
	}
	
	private void notifyObservers (SaleInfoDTO saleInfo) {
		for (SaleObserver saleObserver : saleObservers) {
			saleObserver.newSale(saleInfo);
		}
	}
	
	/**
	 * Adds the specified sale observer that is notified when the sale has been paid. 
	 * 
	 * @param saleObserver The observer to be notified.
	 */
	public void addSaleObserver(SaleObserver saleObserver) {
		saleObservers.add(saleObserver);
	}
	
	/**
	 * Adds all specified sale observers that are notified when the sale has been paid. 
	 * 
	 * @param saleObservers The observers to be notified.
	 */
	public void addSaleObservers(List<SaleObserver> saleObservers) {
		this.saleObservers.addAll(saleObservers);
	}
	
	/**
	 * Prints the receipt (to System.out) with the specified sale information.
	 * 
	 * @param printer The printer that prints the receipt.
	 * @param saleInfo The sale information.
	 */
	public void printReceipt(Printer printer, SaleInfoDTO saleInfo) {
		Receipt receipt = new Receipt(saleInfo);
		receipt.createReceipt();
		printer.Print(receipt);
	}
	
	/**
	 * Checks if the specified <code>ItemDTO</code> is already in the {@link itemList} and if so,
	 * updates the quantity of the item in the {@link itemList} and also updates the {@link totalPrice}.
	 * 
	 * @param searchedItem The <code>ItemDTO</code> to check if it's already in the {@link itemList}.
	 * 
	 * @return <code>true</code> if the specified <code>ItemDTO</code> is already in the {@link itemList},
	 *         <code>false</code> if it's not.
	 */
	private boolean updateQuantityAndTotalPrice(ItemDTO searchedItem) {
		boolean itemAlreadyInSale = false;
		
		for (ItemDTO foundItem : itemList) {
			if (foundItem.equals(searchedItem)) {
				calculateTotalPrice(searchedItem);
				int newSoldQuantity = foundItem.getQuantity() + searchedItem.getQuantity();
				ItemDTO itemWithUpdatedQuantity = new ItemDTO (foundItem.getIdentifier(), newSoldQuantity, 
						                                       foundItem.getPrice(), foundItem.getVAT(), 
						                                       foundItem.getDescription());
				replaceItemInItemList(foundItem, itemWithUpdatedQuantity);
				itemAlreadyInSale = true;
				return itemAlreadyInSale;
			}
		}
		return itemAlreadyInSale;
	}
	
	/**
	 * Replaces the specified <code>ItemDTO</code> with the other specified <code>ItemDTO</code>
	 * in the {@link itemList}.
	 * 
	 * @param itemToReplace The item to replace in the {@link itemList}.
	 * @param itemToAdd The item to add to the {@link itemList}.
	 */
	private void replaceItemInItemList(ItemDTO itemToReplace, ItemDTO itemToAdd) {
		int listPosition = itemList.indexOf(itemToReplace);
		itemList.set(listPosition, itemToAdd);
	}
	
	/**
	 * Calculates the current {@link totalPrice} of the sale.
	 *
	 * @param item The <code>ItemDTO</code> that contain the price information.
	 */
	private void calculateTotalPrice(ItemDTO item) {
		totalPrice += item.getPrice() * item.getVAT() * item.getQuantity();
	}
	
	private int calculateChange(int paidAmount) {
		int change = paidAmount - getTotalPrice();
		return change;
	}
	
	private int calculateTotalVAT() {
		int totalPriceWhithoutVAT = 0;
		
		for (ItemDTO item : itemList) {
			totalPriceWhithoutVAT += item.getPrice() * item.getQuantity();
		}
		int totalVAT = getTotalPrice() - totalPriceWhithoutVAT;
		return totalVAT;
	}
	
	/**
	 * Retrieves the current total price of the sale.
	 * 
	 * @return The current total price of the sale.
	 */
	public int getTotalPrice() {
		return (int)Math.round(totalPrice);
	}
	
	/**
	 * Retrieves a copy of the current item list.
	 * 
	 * @return A copy of the item list.
	 */
	public ItemDTO[] getItemList() {
		ItemDTO[] itemArray = {};
		return itemList.toArray(itemArray);
	}
}
