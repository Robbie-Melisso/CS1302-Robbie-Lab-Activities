package edu.westga.cs1302.bill.test.model.bill_calculator;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.bill.model.Bill;
import edu.westga.cs1302.bill.model.BillItem;
import edu.westga.cs1302.bill.model.BillCalculator;

class TestGetSubtotal {

	Bill bill = new Bill();
	BillItem item1 = new BillItem("1", 5);
	BillItem item2 = new BillItem("2", 6);
	BillItem item3 = new BillItem("3", 7);
	
	
	@Test
	void testCalculateWithAllThree() {
		bill.addItem(item1);
		bill.addItem(item2);
		bill.addItem(item3);
		
		assertEquals(BillCalculator.getSubtotal(bill.getItems()) , item1.getAmount()+ item2.getAmount() + item3.getAmount());
	}
	
	@Test
	void testCalculateWithTwo() {
		bill.addItem(item1);
		bill.addItem(item2);
		
		assertEquals(BillCalculator.getSubtotal(bill.getItems()), item1.getAmount() + item2.getAmount());
	}
	
	
	
}
