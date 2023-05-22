package se.kth.iv1350.saleprocess.integration;

/**
 * Thrown when trying to add an item to the sale that does not exist.
 */
@SuppressWarnings("serial")
public class InvalidIdentifierException extends Exception {
	private int invalidIdentifier;

	/**
	 * Creates a new instance with a message stating that the non-existent item 
	 * could not be added to the sale.
	 * 
	 * @param invalidIdentifier The identifier for an item that does not exist.
	 */
	public InvalidIdentifierException(int invalidIdentifier) {
		super("Unable to add an item with the identifier " + invalidIdentifier 
			  + " to the sale, since the item doesn't exist");
		this.invalidIdentifier = invalidIdentifier;
	}
	
	/**
	 * Retrieves the invalid identifier.
	 * 
	 * @return The invalid identifier.
	 */
	public int getInvalidIdentifier() {
		return invalidIdentifier;
	}

}
