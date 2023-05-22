package se.kth.iv1350.saleprocess.model;

/**
 * Represents a cash register. Stores the amount paid by the customer.
 */
public class CashRegister {
	private static final int NO_BALANCE = 0;
	private int balance = NO_BALANCE;

	/**
	 * Increase the balance by the specified amount.
	 * 
	 * @param paidAmount The amount to increase balance with.
	 */
	public void increaseBalance(int paidAmount) {
		balance += paidAmount;
	}
	
	/**
	 * Decrease the balance by the specified amount.
	 * 
	 * @param change The amount to decrease balance with.
	 */
	public void decreaseBalance(int change) {
		balance -= change;
	}
	
	/**
	 * Retrieve the balance of the cash register.
	 * 
	 * @return The amount of money in the cash register.
	 */
	public int getBalance() {
		return balance;
	}
}
