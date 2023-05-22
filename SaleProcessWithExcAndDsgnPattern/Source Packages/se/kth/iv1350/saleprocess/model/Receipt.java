package se.kth.iv1350.saleprocess.model;

import se.kth.iv1350.saleprocess.integration.SaleInfoDTO;
import se.kth.iv1350.saleprocess.integration.ItemDTO;

/**
 * The receipt of a sale.
 */
public class Receipt {
	private SaleInfoDTO saleInfo;
	
	/**
	 * Creates a new instance.
	 * 
	 * @param saleInfo The sale information used to create the receipt.
	 */
	public Receipt(SaleInfoDTO saleInfo) {
		this.saleInfo = saleInfo;
	}
	
	/**
	 * Creates a string representation of the receipt for the sale.
	 * 
	 * @return The receipt as a string.
	 */
	public String createReceipt() {
		ItemDTO[] itemList = saleInfo.getItemList();
		StringBuilder receipt = new StringBuilder();
		receipt.append("RECEIPT");
		insertNewLine(receipt, "Date and time: ");
		receipt.append(saleInfo.getDateAndTime());
		insertNewLine(receipt, "Purchased items:");
		startNewLine (receipt);
		
		for (ItemDTO item : itemList) {
			receipt.append(item.getDescription());
			receipt.append(", Amount: ");
			receipt.append(item.getQuantity());
			receipt.append(", Cost: ");
			receipt.append(item.getPrice());
			startNewLine(receipt);
		}
		insertNewLine(receipt, "Total price: ");
		receipt.append(saleInfo.getTotalPrice());
		insertNewLine(receipt, "Total VAT: ");
		receipt.append(saleInfo.getTotalVAT());
		insertNewLine(receipt, "Amount paid: ");
		receipt.append(saleInfo.getPaidAmount());
		insertNewLine(receipt, "Change: ");
		receipt.append(saleInfo.getChange());
		insertNewLine (receipt, "END OF RECEIPT");
		
		return receipt.toString();
	}
	
	private void startNewLine(StringBuilder receipt) {
		receipt.append("\n");
	}
	
	private void insertNewLine(StringBuilder receipt, String content) {
		receipt.append("\n" + content);
	}
}
