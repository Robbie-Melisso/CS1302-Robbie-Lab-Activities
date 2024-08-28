package edu.westga.cs1302.lab1.model;

import java.util.ArrayList;

/** Stores information for a bill.
 * 
 * @author CS 1302
 * @version Fall 2024
 */
public class Bill {
	public static final double TIP = 0.2;
	public static final double TAX = 0.1;
	private ArrayList<BillItem> items;
	
	/** Create a new empty Bill
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 */
	public Bill() {
		this.items = new ArrayList<BillItem>();
	}
	
	/** Adds the item to the bill
	 * 
	 * @precondition item != null
	 * @postcondition item is added to the list of items in the bill
	 * 
	 * @param item the item to be added to the bill
	 */
	public void addItem(BillItem item) {
		if (item == null) {
			throw new IllegalArgumentException("item must not be null.");
		}
		this.items.add(item);
	}
	
	/** Return a String containing the list of bill items and total for the bill.
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return a String containing the list of bill items and total for the bill
	 */
	public String getText() {
		String text = "ITEMS" + System.lineSeparator();
		double subTotal = 0.0;
		for (BillItem item : this.items) {
			text += item.getName() + " - " + this.formatDecimalTwoPlaces(item.getAmount()) + System.lineSeparator();
			subTotal += item.getAmount();
		}
		
		text += System.lineSeparator();
		text += "SUBTOTAL - $" + this.formatDecimalTwoPlaces(subTotal) + System.lineSeparator();
		double tax = subTotal * TAX;
		double tip = subTotal * TIP;
		text += "TAX - $" + this.formatDecimalTwoPlaces(tax) + System.lineSeparator();
		text += "TIP - $" + this.formatDecimalTwoPlaces(tip) + System.lineSeparator();
		text += "TOTAL - $" + this.formatDecimalTwoPlaces((subTotal + tip + tax));
		
		return text;
	}
	
	/**
	 * helper method to change decimal to string formatted to two decimal places
	 * @param fix decimal value to format
	 * @return formatted string to two decimal places
	 */
	private String formatDecimalTwoPlaces(double fix) {
		return String.format("%.2f", fix);
	}

}
