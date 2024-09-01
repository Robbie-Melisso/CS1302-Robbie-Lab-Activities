package edu.westga.cs1302.lab2.view;

import edu.westga.cs1302.lab2.model.Bill;
import edu.westga.cs1302.lab2.model.BillItem;

/**
 * create bill object and print it to console
 * @author rmeliss1
 * @version production steps 1 and 2
 */
public class BillDisplay {

	/** Return a String containing the list of bill items and total for the bill.
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @param bill bill object to pull items from for text extraction
	 * 
	 * @return a String containing the list of bill items and total for the bill
	 */
	public String getText(Bill bill) {
		String text = "ITEMS" + System.lineSeparator();
		double subTotal = 0.0;
		for (BillItem item : bill.getItems()) {
			text += item.getName() + " - " + this.formatDecimalTwoPlaces(item.getAmount()) + System.lineSeparator();
			subTotal += item.getAmount();
		}
		
		text += System.lineSeparator();
		text += "SUBTOTAL - $" + this.formatDecimalTwoPlaces(subTotal) + System.lineSeparator();
		double tax = subTotal * Bill.TAX;
		double tip = subTotal * Bill.TIP;
		text += "TAX - $" + this.formatDecimalTwoPlaces(Bill.TAX) + System.lineSeparator();
		text += "TIP - $" + this.formatDecimalTwoPlaces(Bill.TIP) + System.lineSeparator();
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
