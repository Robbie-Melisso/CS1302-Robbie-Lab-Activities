package edu.westga.cs1302.lab2.TEST.testBillDisplay;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
		
		BillDisplay display = new BillDisplay();
		String text = display.getText(bill);
		String[] lineByLine = text.split(System.lineSeparator());
		
		double sub = amt1 + amt2;
		
		assertEquals("ITEMS", lineByLine[0]);
		assertEquals(name1 + " - " + "12.20", lineByLine[1]);
		assertEquals(name2 + " - " + "11.00", lineByLine[2]);
		assertEquals("SUBTOTAL - $" + "23.20", lineByLine[4]);
		assertEquals("TAX - $" + String.format("%.2f", (Bill.TAX * sub)), lineByLine[5]);
		assertEquals("TIP - $" + String.format("%.2f", (Bill.TIP * sub)), lineByLine[6]);
		assertEquals("TOTAL - $" + String.format("%.2f", (((Bill.TIP * sub) + (Bill.TAX * sub) + sub))), lineByLine[7]);
		
	}

}
