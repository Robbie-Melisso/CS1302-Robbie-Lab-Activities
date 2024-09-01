package edu.westga.cs1302.lab2.TEST.testBillDisplay;

import static org.junit.jupiter.api.Assertions.*;
import edu.westga.cs1302.lab2.model.Bill;
import edu.westga.cs1302.lab2.model.BillItem;
import edu.westga.cs1302.lab2.view.BillDisplay;

import org.junit.jupiter.api.Test;

class TestGetText {
	
	
	
	@Test
	void testSeparateLines() {
		Bill bill = new Bill();
		String name1 = "grocery 1";
		String name2 = "sports 1";
		double amt1 = 12.2;
		double amt2 = 11;
		BillItem item1 = new BillItem(name1, amt1);
		BillItem item2 = new BillItem(name2, amt2);
		bill.addItem(item1);
		bill.addItem(item2);
		
//		String text = getText(bill);
		
		
		fail("Not yet implemented");
	}

}
