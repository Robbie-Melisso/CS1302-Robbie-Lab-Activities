package edu.westga.cs1302.bill.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;

/** Supports saving and loading bill data,
 * 
 * @author CS 1302
 * @version Fall 2024
 */
public class BillPersistenceManager {
	
	public static final String DATA_FILE = "data.txt";
	
	/** Save the bill!
	 * 
	 * Writes all bill data to DATA_FILE
	 * 
	 * @precondition bill != null
	 * @postcondition none
	 * 
	 * @param bill the bill to save
	 * @throws IOException 
	 */
	public static void saveBillData(Bill bill) throws IOException {
		try (FileWriter filewrite = new FileWriter(DATA_FILE)) {
			/*
			 * FIXME add save data for server name
			 */
			filewrite.write("Server: " + bill.getServerName() + System.lineSeparator());
			
			for (BillItem item : bill.getItems()) {
				filewrite.write(item.getName() + ": " + item.getAmount() + System.lineSeparator());
			}
		}
		
	}

	/** Load the bill!
	 * 
	 * Reads from DATA_FILE
	 * File is assumed to use the same format as saveBillData
	 * 
	 * --string name --double amount
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the bill loaded
	 * @throws FileNotFoundException when unable to access file
	 * @throws IOException when provided invalid save data
	 * 
	 */
	public static Bill loadBillData() throws IOException, FileNotFoundException {
		List<BillItem> items = new ArrayList<BillItem>();
		File inputFile = new File(DATA_FILE);
		String server = Bill.NO_SERVER_DSG;
		try (Scanner reader = new Scanner(inputFile)) {
			
			//server information has unique error throw
			if (reader.hasNextLine()) {
				String strippedLine = "";
				try {
					strippedLine = reader.nextLine().strip();
					server = strippedLine.split(":")[1].strip();
				} catch (IndexOutOfBoundsException missErr) {
					throw new IOException("unable to read data or missing server name on line 1: " + strippedLine);
				}
			}
			//error checking for bill item data
			for (int lineNumber = 2; reader.hasNextLine(); lineNumber++) {
				String line = reader.nextLine().strip();
				String[] parts = line.split(":");
				String name = parts[0].strip();
				try {
					double price = Double.parseDouble(parts[1].strip());
					BillItem nextItem = new BillItem(name, price);
					items.add(nextItem);
				} catch (NumberFormatException numErr) {
					throw new IOException("Unable to read price (not valid double) on line " + System.lineSeparator() + lineNumber + ": " + line);
				} catch (IllegalArgumentException illErr) {
					throw new IOException("unable to create Billitem (invalid parameters) on line: " + System.lineSeparator()  + lineNumber + ": " + line + System.lineSeparator() + illErr.getMessage());
				} catch (IndexOutOfBoundsException missErr) {
					throw new IOException("unable to read data (missing name/price) on line " + System.lineSeparator() + lineNumber + ": " + line);
				}
			}
		} catch (FileNotFoundException err) {
			throw new FileNotFoundException("File unusable to load");
		}
		
		Bill createdBill = new Bill();
		try {
			if (!server.equalsIgnoreCase(Bill.NO_SERVER_DSG)) {
				createdBill.setServerName(server);
			}
			for (BillItem item : items) {
				createdBill.addItem(item);
			}
		} catch (IllegalStateException stateErr) {
			throw new IOException("invalid number of items (greater than max number): " + Bill.MAX_NUMBER_OF_ITEMS);
		} catch (IllegalArgumentException illErr) {
			throw new IOException("null item provided to bill");
		}
		
		return createdBill;
	}

}
