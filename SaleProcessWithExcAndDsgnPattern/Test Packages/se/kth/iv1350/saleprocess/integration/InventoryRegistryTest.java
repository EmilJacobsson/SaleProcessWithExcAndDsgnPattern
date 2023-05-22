package se.kth.iv1350.saleprocess.integration;

import static org.junit.jupiter.api.Assertions.*;
import se.kth.iv1350.saleprocess.model.Sale;
import org.junit.jupiter.api.Test;

class InventoryRegistryTest {

	@Test
	public void testGetItemInfoQuantityEqual() {
		
		int identifier = 4;
		int soldQuantity = 3;
		int price = 40;
		double vat = 1.06;
		String description = "Canned Tomatoes";
		ItemDTO searchedItem = new ItemDTO (identifier, soldQuantity, price, vat, description);
		
		InventoryRegistry instance = new InventoryRegistry();
		ItemDTO expResult = searchedItem;
	    ItemDTO result = null;
	    
		try {
	    	result = instance.getItemInfo(identifier, soldQuantity);
		} catch (InvalidIdentifierException esc) {
			fail("Existing item does not exist");
	    	esc.printStackTrace();
	    } catch (InventoryRegistryException exc) {
	    	fail("Item registered with database error");
	    	exc.printStackTrace();
	    }
	    assertEquals(expResult, result, "Equal items not equal");
	}
	
	@Test
	public void testGetItemInfoQuantityNotEqual() {
		
		int identifier = 4;
		int soldQuantity = 3;
		int price = 40;
		double vat = 1.06;
		String description = "Canned Tomatoes";
		ItemDTO searchedItem = new ItemDTO (identifier, soldQuantity, price, vat, description);
		
		int otherSoldQuantity = 5;
		InventoryRegistry instance = new InventoryRegistry();
		ItemDTO expResult = searchedItem;
		ItemDTO result = null;
		
		try {
			result = instance.getItemInfo(identifier, otherSoldQuantity);
		} catch (InvalidIdentifierException esc) {
			fail("Existing item does not exist");
	    	esc.printStackTrace();
	    } catch (InventoryRegistryException exc) {
	    	fail("Item registered with database error");
	    	exc.printStackTrace();
	    }
		assertEquals(expResult, result, "Equal items not equal");
	}
	
	@Test
	public void testUpdateInventoryCorrectItemQuantity() {
		InventoryRegistry instance = new InventoryRegistry();
		int identifier = 4;
		int soldQuantity = 3;
		int price = 40;
		double vat = 1.06;
		String description = "Canned Tomatoes";
		ItemDTO searchedItem = new ItemDTO (identifier, soldQuantity, price, vat, description);
		
		Sale sale = new Sale();
		sale.addItemToSale(searchedItem);
		int paidAmount = 150;
		SaleInfoDTO saleInfo = sale.endSale(paidAmount);
		instance.updateInventory(saleInfo);
		int inventoryQuantity = 20;
		int expResult = inventoryQuantity - soldQuantity;
		int result = instance.getQuantity(searchedItem);
		assertEquals(expResult, result, "Wrong item quantity in inventory");
	}
	
	@Test
	public void testGetItemInfoDatabaseError() {
		InventoryRegistry instance = new InventoryRegistry();
		int identifierForDatabaseError = 5;
		int soldQuantity = 2;
		
		try {
			instance.getItemInfo(identifierForDatabaseError, soldQuantity);
			fail("Item registered with a database error");
		} catch (InvalidIdentifierException exc) {
			fail("Got exception: ");
			exc.printStackTrace();
		} catch (InventoryRegistryException exc) {
			String identifierForDatabaseErrorString = Integer.toString(identifierForDatabaseError);
			assertTrue(exc.getMessage().contains(identifierForDatabaseErrorString),
					   "The exception message is wrong, it does not contain the specified identifier: " 
			           + exc.getMessage());
		}
	}
}
