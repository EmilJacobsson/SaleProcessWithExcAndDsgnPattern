package se.kth.iv1350.saleprocess.integration;

/**
 * Thrown when something in the inventory registry goes wrong except
 * when an identifier is invalid. This exception simulates the scenario
 * when the database can not be called for whatever reason.
 * 
 * (Would normally have a different name to match the database 
 * like "ExternalInventorySystemException" to indicate that 
 * the database can not be called.)
 */
@SuppressWarnings("serial")
public class InventoryRegistryException extends RuntimeException{

	/**
	 * Creates an instance with the specified exception message.
	 * 
	 * @param msg The message detailing the cause of the exception.
	 */
	public InventoryRegistryException(String msg) {
		super(msg);
	}
}
