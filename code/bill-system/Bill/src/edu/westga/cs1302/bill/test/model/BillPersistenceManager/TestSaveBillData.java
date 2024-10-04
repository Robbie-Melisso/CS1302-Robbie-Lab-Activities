package edu.westga.cs1302.bill.test.model.BillPersistenceManager;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.bill.model.Bill;
import edu.westga.cs1302.bill.model.BillItem;
import edu.westga.cs1302.bill.model.BillPersistenceManager;

class TestSaveBillData {

	@Test
	void testDefaultSave() throws IOException {
		Bill bill = new Bill();
		BillPersistenceManager.saveBillData(bill);
		
		File inputFile = new File(BillPersistenceManager.DATA_FILE);
		try (Scanner reader = new Scanner(inputFile)) {
			String line1 = reader.nextLine().strip();
			assertEquals(line1, "Server: " + Bill.NO_SERVER_DSG,"checking server line");
			assertFalse(reader.hasNext(),"should have no further text in file");
		}
		
	}
	
	@Test
	void testServerSave() throws IOException {
		Bill bill = new Bill();
		String name = "hello this is a test";
		
		bill.setServerName(name);
		BillPersistenceManager.saveBillData(bill);

		File inputFile = new File(BillPersistenceManager.DATA_FILE);
		try (Scanner reader = new Scanner(inputFile)) {
			String line1 = reader.nextLine().strip();
			assertEquals("Server: " + name, line1, "checking server line");
			assertEquals(name, line1.split(":")[1].strip());
			assertFalse(reader.hasNext(),"should have no further text in file");
		}
		
	}
	
	@Test
	void testOneItem() throws IOException {
		Bill bill = new Bill();
		String itemName = "item1";
		double price = 23.06;
		BillItem billItem1 = new BillItem(itemName, price);
		bill.addItem(billItem1);
		BillPersistenceManager.saveBillData(bill);
		
		File inputFile = new File(BillPersistenceManager.DATA_FILE);
		try (Scanner reader = new Scanner(inputFile)) {
			reader.nextLine();
			String line2 = reader.nextLine();
			assertEquals(itemName + ": " + price, line2);
			assertEquals(itemName.strip(), line2.split(":")[0].strip());
			assertEquals(price, Double.parseDouble(line2.split(":")[1].strip()));
		}
	}
	
	@Test
	void testMultipleItems() throws IOException {
		Bill bill = new Bill();
		String item1Name = "item1";
		String item2Name = "item2";
		String item3Name = "item3";
		double price1 = 23.06;
		double price2 = 67.1;
		double price3 = 16;
		BillItem billItem1 = new BillItem(item1Name, price1);
		BillItem billItem2 = new BillItem(item2Name, price2);
		BillItem billItem3 = new BillItem(item3Name, price3);
		bill.addItem(billItem1);
		bill.addItem(billItem2);
		bill.addItem(billItem3);
		BillPersistenceManager.saveBillData(bill);
		
		File inputFile = new File(BillPersistenceManager.DATA_FILE);
		try (Scanner reader = new Scanner(inputFile)) {
			String strippedLine = reader.nextLine().strip();
			assertEquals(strippedLine, "Server: " + Bill.NO_SERVER_DSG,"checking server line");
			
			strippedLine = reader.nextLine().strip();
			assertEquals(strippedLine, item1Name + ": " + price1);
			
			strippedLine = reader.nextLine().strip();
			assertEquals(strippedLine, item2Name + ": " + price2);
			
			strippedLine = reader.nextLine().strip();
			assertEquals(strippedLine, item3Name + ": " + price3);
			
			assertFalse(reader.hasNext());
		}
	}
}
