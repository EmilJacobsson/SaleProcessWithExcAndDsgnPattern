package se.kth.iv1350.saleprocess.controller;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import se.kth.iv1350.saleprocess.integration.InvalidIdentifierException;
import se.kth.iv1350.saleprocess.integration.InventoryRegistryException;
import se.kth.iv1350.saleprocess.integration.Printer;
import se.kth.iv1350.saleprocess.integration.RegistryCreator;

class ControllerTest {
private Controller instance;
private RegistryCreator creator;

	@BeforeEach
	void setUp() throws IOException {
		creator = new RegistryCreator();
		Printer printer = new Printer();
		instance = new Controller(creator, printer);
		instance.startSale();
	}

	@AfterEach
	void tearDown() {
		instance = null;
		creator = null;
	}

	@Test
	void testRegisterItemThatDoesNotExist() {
		int invalidIdentifier = 9;
		int soldQuantity = 3;
		
		try {
			instance.registerItem(invalidIdentifier, soldQuantity);
			fail("Managed to register a non-existent item.");
		} catch (OperationFailedException exc) {
			fail("Got exception:");
			exc.printStackTrace();
		} catch (InvalidIdentifierException exc) {
			String invalidIdentifierString = Integer.toString(invalidIdentifier);
			assertTrue(exc.getMessage().contains(invalidIdentifierString), 
					   "The exception message is wrong, it does not contain the specified invalid identifier: " 
			           + exc.getMessage());
			assertTrue(exc.getInvalidIdentifier() == invalidIdentifier, "Wrong identifier is specified: " 
			           + exc.getInvalidIdentifier());
		}
	}

	@Test
	void testRegisterItemSimulateADatabaseError() {
		int identifierForDatabaseError = 5;
		int soldQuantity = 1;
		
		try {
			instance.registerItem(identifierForDatabaseError, soldQuantity);
		} catch (InvalidIdentifierException exc) {
			fail("Got exception:");
			exc.printStackTrace();
		} catch (OperationFailedException exc) {
			assertTrue(exc.getCause() instanceof InventoryRegistryException,
					   "The exception has a wrong root cause. \nThe expected root cause is InventoryRegistryException, "
					   + "but the root cause is " + exc.getCause().getClass().getCanonicalName());
			
			String identifierForDatabaseErrorString = Integer.toString(identifierForDatabaseError);
			assertTrue(exc.getCause().getMessage().contains(identifierForDatabaseErrorString), 
					   "The exception message of the root cause is wrong, it does not contain the specified identifier: "
					   + exc.getCause().getMessage());
		}
	}
}
