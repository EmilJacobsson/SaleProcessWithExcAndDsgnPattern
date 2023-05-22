package se.kth.iv1350.saleprocess.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.lang.Math;

import se.kth.iv1350.saleprocess.integration.ItemDTO;

class SaleTest {

	@Test
	public void testCalculateTotalPriceNoItemInList() {
		int identifier = 4;
		int soldQuantity = 3;
		int price = 40;
		double vat = 1.06;
		String description = "Canned Tomatoes";
		ItemDTO item = new ItemDTO (identifier, soldQuantity, price, vat, description);
		
		Sale instance = new Sale();
		instance.addItemToSale(item);
		int expResult = (int)Math.round(price * vat * soldQuantity);
		int result = instance.getTotalPrice();
		assertEquals(expResult, result, "Total price calculated wrong with one item in the sale");
	}
	
	@Test
	public void testCalculateTotalPriceDuplicateItemInList() {
		int identifier = 3;
		int soldQuantity = 2;
		int price = 80;
		double vat = 1.25;
		String description = "Ground Beef";
		ItemDTO item = new ItemDTO (identifier, soldQuantity, price, vat, description);
		
		Sale instance = new Sale();
		instance.addItemToSale(item);
		int expResult = (int)Math.round(instance.getTotalPrice() + price * vat * soldQuantity);
		instance.addItemToSale(item);
		int result = instance.getTotalPrice();
		assertEquals(expResult, result, "Total price calculated wrong with duplicate items added to the sale");
	}
	
	@Test
	public void testCalculateTotalPriceDifferentItemInList() {
		int identifier = 6;
		int soldQuantity = 4;
		int price = 35;
		double vat = 1.25;
		String description = "Tooth Paste";
		ItemDTO item = new ItemDTO (identifier, soldQuantity, price, vat, description);
		ItemDTO itemInSale = new ItemDTO (2, 6, 50, 1.12, "Frozen Pizza");
		
		Sale instance = new Sale();
		instance.addItemToSale(itemInSale);
		int expResult = (int)Math.round(instance.getTotalPrice() + price * vat * soldQuantity);
		instance.addItemToSale(item);
		int result = instance.getTotalPrice();
		assertEquals(expResult, result, "Total price calculated wrong with different items in the sale");
	}
	
	@Test
	public void testAddItemToSaleCorrectQuantityOfItemInSale() {
		int identifier = 5;
		int soldQuantity = 1;
		int price = 130;
		double vat = 1.12;
		String description = "T-shirt";
		ItemDTO item = new ItemDTO (identifier, soldQuantity, price, vat, description);
		int itemIndex = 0;
		
		Sale instance = new Sale();
		instance.addItemToSale(item);
		int expResult = item.getQuantity();
		ItemDTO[] itemList = instance.getItemList();
		int result = itemList[itemIndex].getQuantity();
		assertEquals(expResult, result, "Wrong quantity of the singular item in the sale");
	}
	
	@Test
	public void testAddItemToSaleCorrectQuantityOfDuplicateItemInSale() {
		int identifier = 5;
		int soldQuantity = 1;
		int otherSoldQuantity = 3;
		int price = 130;
		double vat = 1.12;
		String description = "T-shirt";
		ItemDTO item = new ItemDTO (identifier, soldQuantity, price, vat, description);
		ItemDTO copyOfItem = new ItemDTO (identifier, otherSoldQuantity, price, vat, description);
		int itemIndex = 0;
		
		Sale instance = new Sale();
		instance.addItemToSale(item);
		instance.addItemToSale(copyOfItem);
		int expResult = item.getQuantity() + copyOfItem.getQuantity();
		ItemDTO[] itemList = instance.getItemList();
		int result = itemList[itemIndex].getQuantity();
		assertEquals(expResult, result, "Wrong quantity of the duplicate item in the sale");
	}
	
	@Test
	public void testAddItemToSaleItemListMatchesAddedItems() {
		ItemDTO item1 = new ItemDTO (3, 10, 80, 1.25, "Ground beef");
		ItemDTO item2 = new ItemDTO (6, 10, 35, 1.25, "Tooth Paste");
		ItemDTO item3 = new ItemDTO (1, 30, 14, 1.06, "Milk");
		ItemDTO item4 = new ItemDTO (4, 20, 40, 1.06, "Canned Tomatoes");
		ItemDTO item5 = new ItemDTO (5, 8, 130, 1.12, "T-shirt");
		ItemDTO item6 = new ItemDTO (2, 6, 50, 1.12, "Frozen Pizza");
		ItemDTO[] expectedItemList = {item1, item2, item3, item4, item5, item6};
		Sale instance = new Sale();
		
		instance.addItemToSale(item1);
		instance.addItemToSale(item2);
		instance.addItemToSale(item3);
		instance.addItemToSale(item4);
		instance.addItemToSale(item2);
		instance.addItemToSale(item5);
		instance.addItemToSale(item6);
		instance.addItemToSale(item5);
		ItemDTO[] resultItemList = instance.getItemList();
		boolean expResult = true;
		boolean result = true;
		
		for (int i=0; i < resultItemList.length; i++) {
			if (!resultItemList[i].equals(expectedItemList[i])) {
				result = false;
			}
		}
		assertEquals(expResult, result, "Equal item lists are not equal");
	}
}
