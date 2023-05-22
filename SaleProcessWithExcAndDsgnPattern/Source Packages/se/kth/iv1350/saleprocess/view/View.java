package se.kth.iv1350.saleprocess.view;

import java.io.IOException;

import se.kth.iv1350.saleprocess.controller.Controller;
import se.kth.iv1350.saleprocess.model.ItemSaleInfoDTO;
import se.kth.iv1350.saleprocess.integration.InvalidIdentifierException;

/**
 * This is a placeholder for the real view. It contains a hard-coded execution with calls to all
 * system operations in the controller.
 */
public class View {
	private Controller controller;
	private ExceptionMessageHandler excMsgHandler = new ExceptionMessageHandler();
	
	/**
	 * Creates a new instance that uses the specified controller for all calls to other layers. 
	 * 
	 * @param controller The controller to use for all calls to other layers. 
	 */
	public View(Controller controller) throws IOException{
		this.controller = controller;
		controller.addSaleObserver(new TotalRevenueView());
	}
	
	/**
	 * Performs a fake sale by calling all system operations in the controller. 
	 */
	public void runFakeExecution() {
		controller.startSale();
		System.out.println("A new sale has been started.");
		
		registerItem(4,3);
		registerItem(1,5);
		registerItem(7,2);
		registerItem(6,2);
		registerItem(3,1);
		registerItem(2,1);
		registerItem(5,3);
		registerItem(1,1);
		registerItem(2,5);
		
		int paidAmount = 1000;
		endSale(paidAmount);
		
		controller.startSale();
		System.out.println("A new sale has been started.");
		
		registerItem(1,10);
		registerItem(3,4);
		registerItem(2,6);
		
		paidAmount = 900;
		endSale(paidAmount);
	}
	
	private void registerItem(int identifier, int quantity) {
		try {
			printSaleInfo(controller.registerItem(identifier, quantity));
		} catch (InvalidIdentifierException exc) {
			excMsgHandler.showExceptionMsg("Invalid identifier. Please try again.");
		} catch (Exception exc) {
			excMsgHandler.showExceptionMsg("Failed to register item(s).");
		}
	}
	
	private void printSaleInfo(ItemSaleInfoDTO itemSaleInfo) {
		System.out.println("\nNew item(s) added to the sale.\nTotal price: " 
                           + itemSaleInfo.getTotalPrice() + "\nDescription: " 
		                   + itemSaleInfo.getDescription() + "\nPrice: " 
                           + itemSaleInfo.getPrice());
	}
	
	private void endSale(int paidAmount) {
		int totalPrice = controller.signalEndSale();
		System.out.println("\nTo pay: " + totalPrice + "\n");
		int change = controller.pay(paidAmount);
		System.out.println("\nChange: " + change + "\n");
	}
}
