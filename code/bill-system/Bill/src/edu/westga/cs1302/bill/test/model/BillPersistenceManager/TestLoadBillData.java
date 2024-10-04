package edu.westga.cs1302.bill.test.model.BillPersistenceManager;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileWriter;
import java.io.IOException;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.bill.model.Bill;
import edu.westga.cs1302.bill.model.BillPersistenceManager;


class TestLoadBillData {

	String name1 = "itEm1";
	String name2 = "iteM2";
	String name3 = "Item3";
	double price1 = 56.099;
	double price2 = 15.6;
	double price3 = 74;
	
	String regex = ": ";
	
	private String createLine(String name, double price) {
		return name + regex + price + System.lineSeparator();	
	}
	
	@Test
	void testOneItemInFile() throws IOException {
		try (FileWriter writer = new FileWriter(BillPersistenceManager.DATA_FILE)) {
			writer.write("Server" + regex + Bill.NO_SERVER_DSG + System.lineSeparator()
						+createLine(name1, price1));
		}
		
		Bill bill = BillPersistenceManager.loadBillData();
		
		assertEquals(Bill.NO_SERVER_DSG, bill.getServerName());
		assertEquals(1, bill.getItems().length, "checking number of items loaded");
		assertEquals(name1, bill.getItems()[0].getName());
		assertEquals(price1, bill.getItems()[0].getAmount());
	}
	
	@Test
	void testMultipleItemsInFile() throws IOException {
		try (FileWriter writer = new FileWriter(BillPersistenceManager.DATA_FILE)) {
			writer.write("Server" + regex + Bill.NO_SERVER_DSG + System.lineSeparator());
			writer.append(createLine(name1,price1));
			writer.append(createLine(name2,price2));
			writer.append(createLine(name3,price3));
		}
		
		Bill bill = BillPersistenceManager.loadBillData();
		
		assertEquals(3, bill.getItems().length, "checking number of items loaded");
		assertEquals(name1, bill.getItems()[0].getName(), "checking name of the first item");
		assertEquals(price1, bill.getItems()[0].getAmount(), "checking price of the first item");
		assertEquals(name2, bill.getItems()[1].getName(), "checking name of the first item");
		assertEquals(price2, bill.getItems()[1].getAmount(), "checking price of the first item");
		assertEquals(name3, bill.getItems()[2].getName(), "checking name of the first item");
		assertEquals(price3, bill.getItems()[2].getAmount(), "checking price of the first item");
	}
	
	@Test
	void testInvalidName() throws IOException {
		try (FileWriter writer = new FileWriter(BillPersistenceManager.DATA_FILE)) {
			writer.write("Server" + regex + Bill.NO_SERVER_DSG + System.lineSeparator());
			writer.append(createLine(name1,price1));
			writer.append(createLine("",price2));
			writer.append(createLine(name3,price3));
		}
		assertThrows(IOException.class, ()->{BillPersistenceManager.loadBillData();});

	}
	
	@Test
	void testInvalidPrice() throws IOException {
		try (FileWriter writer = new FileWriter(BillPersistenceManager.DATA_FILE)) {
			writer.write("Server" + regex + Bill.NO_SERVER_DSG + System.lineSeparator());
			writer.append(createLine(name1,price1));
			writer.append(name2 + regex + price2 + "S" + System.lineSeparator());
			writer.append(createLine(name3,price3));
		}
		assertThrows(IOException.class, ()->{BillPersistenceManager.loadBillData();});
		
		try (FileWriter writer = new FileWriter(BillPersistenceManager.DATA_FILE)) {
			writer.write("Server" + regex + Bill.NO_SERVER_DSG + System.lineSeparator());
			writer.append(createLine(name1,price1));
			writer.append(name2 + regex + System.lineSeparator());
			writer.append(createLine(name3,price3));
		}
		assertThrows(IOException.class, ()->{BillPersistenceManager.loadBillData();});
		
		try (FileWriter writer = new FileWriter(BillPersistenceManager.DATA_FILE)) {
			writer.write("Server" + regex + Bill.NO_SERVER_DSG + System.lineSeparator());
			writer.append(createLine(name1,price1));
			writer.append(createLine(name2,0));
			writer.append(createLine(name3,price3));
		}
		assertThrows(IOException.class, ()->{BillPersistenceManager.loadBillData();});
	}
	
	@Test
	void testInvalidServer() throws IOException {
		try (FileWriter writer = new FileWriter(BillPersistenceManager.DATA_FILE)) {
			writer.write("Server" + regex + System.lineSeparator());
			writer.append(createLine(name1,price1));
			writer.append(createLine(name2,price2));
			writer.append(createLine(name3,price3));
		}
		assertThrows(IOException.class, ()->{BillPersistenceManager.loadBillData();});
		
		try (FileWriter writer = new FileWriter(BillPersistenceManager.DATA_FILE)) {
			writer.write("Server Ollie" + System.lineSeparator());
			writer.append(createLine(name1,price1));
			writer.append(createLine(name2,price2));
			writer.append(createLine(name3,price3));
		}
		assertThrows(IOException.class, ()->{BillPersistenceManager.loadBillData();});

	}
	
	@Test
	void testValidServer() throws IOException {
		try (FileWriter writer = new FileWriter(BillPersistenceManager.DATA_FILE)) {
			writer.write("Server" + regex + "Trisha" + System.lineSeparator());
			writer.append(createLine(name1,price1));
			writer.append(createLine(name2,price2));
			writer.append(createLine(name3,price3));
		}
		Bill bill = BillPersistenceManager.loadBillData();
		assertEquals("Trisha",bill.getServerName());
		
		try (FileWriter writer = new FileWriter(BillPersistenceManager.DATA_FILE)) {
			writer.write("Server" + regex + "Ollie" + System.lineSeparator());
			writer.append(createLine(name1,price1));
			writer.append(createLine(name2,price2));
			writer.append(createLine(name3,price3));
		}
		bill = BillPersistenceManager.loadBillData();
		assertEquals("Ollie",bill.getServerName());
	}
	
	@Test
	void testEmptyFile() throws IOException {
		try (FileWriter writer = new FileWriter(BillPersistenceManager.DATA_FILE)) {
			writer.write("");
		}
		
		Bill bill = BillPersistenceManager.loadBillData();
		
		assertEquals(Bill.NO_SERVER_DSG, bill.getServerName());
		assertEquals(0, bill.getItems().length);
	}
	
}
