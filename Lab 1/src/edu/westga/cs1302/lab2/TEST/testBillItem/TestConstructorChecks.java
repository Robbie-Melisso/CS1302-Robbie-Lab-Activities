package edu.westga.cs1302.lab2.TEST.testBillItem;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.assertThrows;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.lab2.model.BillItem;

class TestConstructorChecks {

	
	
	@Test
	public void testNullNameCatch() {
		String name = "test1";
		double validAMT = 25.6;
		
		assertThrows(IllegalArgumentException.class,
				() -> {
					BillItem testItemFail = new BillItem(null, validAMT); 
					}
				);
		BillItem testItemPass = new BillItem(name, validAMT);
		assertEquals(name, testItemPass.getName());
	}
	
	@Test
	public void testAmountCatch() {
		String name = "test1";
		double validAMT = 25.6;
		double invalidAMT = -2.5;
		double invalidBorderAMT = 0;
		
		assertThrows(IllegalArgumentException.class,
				() -> {
					 new BillItem(name, invalidAMT); 
					}
				);
		BillItem testItemPass = new BillItem(name, validAMT);
		assertEquals(validAMT, testItemPass.getAmount());
		
		assertThrows(IllegalArgumentException.class,
				() -> {
					BillItem testItemBorderFail = new BillItem(name, invalidBorderAMT);
					}
				);
		
	}

}
