package se.kth.iv1350.saleprocess.controller;

import se.kth.iv1350.saleprocess.model.Sale;
import se.kth.iv1350.saleprocess.model.SaleObserver;
import se.kth.iv1350.saleprocess.util.LogHandler;
import se.kth.iv1350.saleprocess.util.TotalRevenueFileOutput;
import se.kth.iv1350.saleprocess.integration.InventoryRegistry;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import se.kth.iv1350.saleprocess.integration.AccountingRegistry;
import se.kth.iv1350.saleprocess.integration.RegistryCreator;
import se.kth.iv1350.saleprocess.integration.ItemDTO;
import se.kth.iv1350.saleprocess.model.ItemSaleInfoDTO;
import se.kth.iv1350.saleprocess.integration.SaleInfoDTO;
import se.kth.iv1350.saleprocess.model.CashRegister;
import se.kth.iv1350.saleprocess.integration.Printer;
import se.kth.iv1350.saleprocess.integration.InvalidIdentifierException;
import se.kth.iv1350.saleprocess.integration.InventoryRegistryException;

/**
 * This is the application's only controller. All calls to the model pass through this class.
 */
public class Controller {
	private Sale sale;
	private SaleInfoDTO saleInfo;
	private InventoryRegistry inventoryRegistry;
	private AccountingRegistry accountingRegistry;
	private CashRegister cashRegister;
	private Printer printer;
	private LogHandler log;
	private List<SaleObserver> saleObservers = new ArrayList<SaleObserver>();
	
	/**
	 * Creates an instance.
	 * 
	 * @param creator The <code>RegistryCreator</code> that creates instances of the registries.
	 * @param printer The <code>Printer</code> used to print the receipt.
	 */
	public Controller (RegistryCreator creator, Printer printer) throws IOException{
		inventoryRegistry = creator.getInventoryRegistry();
		accountingRegistry = creator.getAccountingRegistry();
		this.printer = printer;
		cashRegister = new CashRegister();
		this.log = new LogHandler();
		addSaleObserver(new TotalRevenueFileOutput());
	}
	
	/**
	 * Starts a new sale. This method must be called before doing anything else during a sale.
	 */
	public void startSale() {
		sale = new Sale();
		sale.addSaleObservers(saleObservers);
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
	 * Adds the <code>ItemDTO</code> with the same specified identifier to the sale and sets 
	 * the quantity of the <code>ItemDTO</code> to the specified quantity.
	 * 
	 * @param identifier The unique identifier of an <code>ItemDTO</code>.
	 * @param soldQuantity The amount of the <code>ItemDTO</code> to be sold.
	 * 
	 * @throws InvalidIdentifierException if the item with the specified identifier does not exist.
	 * @throws OperationFailedException if the item with the specified identifier could not be
	 *         registered for an unknown reason.
	 * 
	 * @return Information to be printed by the view (to System.out).
	 */
	public ItemSaleInfoDTO registerItem(int identifier, int soldQuantity) throws InvalidIdentifierException, OperationFailedException {
		ItemDTO searchedItem = null;
		
		try {
			searchedItem = inventoryRegistry.getItemInfo(identifier, soldQuantity);
		} catch (InventoryRegistryException exc) {
			log.logExceptionMsg(exc);
			throw new OperationFailedException("Could not register item.", exc);
		}
		ItemSaleInfoDTO itemSaleInfo = sale.addItemToSale(searchedItem);
		return itemSaleInfo;
	}
	
	/**
	 * Signal the end of the sale.
	 * 
	 * @return The total price of the sale including VAT.
	 */
	public int signalEndSale() {
		return sale.getTotalPrice();
	}
	
	/**
	 * Pays for the sale, updates the registries with the sale information, updates the 
	 * balance in the cash register and prints the receipt.
	 * 
	 * @param paidAmount The amount paid by the customer.
	 * 
	 * @return The change to give to the customer.
	 */
	public int pay(int paidAmount) {
		saleInfo = sale.endSale(paidAmount);
		int change = saleInfo.getChange();
		accountingRegistry.storeSaleInfo(saleInfo);
		inventoryRegistry.updateInventory(saleInfo);
		cashRegister.increaseBalance(paidAmount);
		cashRegister.decreaseBalance(change);
		sale.printReceipt(printer, saleInfo);
		
		return change;
	}
}
