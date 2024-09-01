package edu.westga.cs1302.lab2.TEST.testBill;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import edu.westga.cs1302.lab2.model.Bill;
import edu.westga.cs1302.lab2.model.BillItem;

class TestAddItem {
	
	@Test
	void catchNull() {
		Bill bill = new Bill();
		assertThrows(IllegalArgumentException.class, () -> {
			bill.addItem(null);
		});
	}
	
	@Test
	void invalidItemAdd() {
		Bill bill = new Bill();
		String name1 = "item 1";
		double amt1 = 0;
		assertThrows(IllegalArgumentException.class, () -> {
			bill.addItem(new BillItem(name1, amt1));
		});
		
	}

	@Test
	void oneItemAdded() {
		Bill bill = new Bill();
		String name1 = "item 1";
		double amt1 = 24.78;
		BillItem item1  = new BillItem(name1, amt1);
		bill.addItem(item1);
		assertEquals(bill.getItems().get(0), item1);
		assertEquals(bill.getItems().get(0).getName(), name1);
		assertEquals(bill.getItems().get(0).getAmount(), amt1);
	}
	
	@Test
	void multiItemAdded() {
		Bill bill = new Bill();
		String name1 = "item 1";
		double amt1 = 24.78;
		BillItem item1  = new BillItem(name1, amt1);
		bill.addItem(item1);
		
		String name2 = "item 2";
		double amt2 = 15.34;
		BillItem item2 = new BillItem(name2, amt2);
		bill.addItem(item2);
		
		assertEquals(bill.getItems().get(0), item1);
		assertEquals(bill.getItems().get(0).getName(), name1);
		assertEquals(bill.getItems().get(0).getAmount(), amt1);
		
		assertEquals(bill.getItems().get(1), item2);
		assertEquals(bill.getItems().get(1).getName(), name2);
		assertEquals(bill.getItems().get(1).getAmount(), amt2);
	}
	
}
