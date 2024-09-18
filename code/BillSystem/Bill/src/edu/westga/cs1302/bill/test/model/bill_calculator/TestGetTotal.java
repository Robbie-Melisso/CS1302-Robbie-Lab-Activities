package edu.westga.cs1302.bill.test.model.bill_calculator;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.bill.model.Bill;
import edu.westga.cs1302.bill.model.BillCalculator;
import edu.westga.cs1302.bill.model.BillItem;

class TestGetTotal {
	
	Bill bill = new Bill();
	BillItem item1 = new BillItem("1", 5);
	BillItem item2 = new BillItem("2", 6);
	BillItem item3 = new BillItem("3", 7);
	BillItem item4 = new BillItem("7", 6.9);
	
	
	@Test
	void testCalculateWithAllThree() {
		bill.addItem(item1);
		bill.addItem(item2);
		bill.addItem(item3);
		
		double sub = (item1.getAmount()+ item2.getAmount() + item3.getAmount());
		
		assertEquals(BillCalculator.getTotal(bill.getItems()) , sub * BillCalculator.TAX_RATE + sub * BillCalculator.TIP_RATE + sub, 0.00001);
	}
	
	@Test
	void testCalculateWithTwo() {
		bill.addItem(item1);
		bill.addItem(item4);
		
		double sub =  (item1.getAmount() + item4.getAmount());
		
		assertEquals(BillCalculator.getTotal(bill.getItems()) , sub * BillCalculator.TAX_RATE + sub * BillCalculator.TIP_RATE + sub, 0.0001);
	}
}
