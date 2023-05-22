package se.kth.iv1350.saleprocess.integration;

/**
 * Creates instances of all registries.
 */
public class RegistryCreator {
	private InventoryRegistry inventoryRegistry;
	private AccountingRegistry accountingRegistry;
	
	/**
	 * Creates a new instance that creates instances of all registries.
	 */
    public RegistryCreator() {
    	inventoryRegistry = new InventoryRegistry();
    	accountingRegistry = new AccountingRegistry();
    }
    
    /**
     * Retrieves the inventory registry.
     * 
     * @return The inventory registry.
     */
    public InventoryRegistry getInventoryRegistry() {
    	return inventoryRegistry;
    }
    
    /**
     * Retrieves the accounting registry.
     * 
     * @return The accounting registry.
     */
    public AccountingRegistry getAccountingRegistry() {
    	return accountingRegistry;
    }
}
