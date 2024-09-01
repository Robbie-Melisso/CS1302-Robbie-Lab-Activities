package edu.westga.cs1302.lab2.view;

import edu.westga.cs1302.lab2.model.Bill;
import edu.westga.cs1302.lab2.model.BillItem;

/**
 * create bill object and print it to console
 * @author rmeliss1
 * @version production steps 1 and 2
 */
public class BillDisplay {

//	/**
//	 * create a bill, add items to it, then display the bill
//	 * @param args arguments for main method
//	 */
//	public static void main(String[] args) {
//		Bill theTestBill = new Bill();
//		BillItem item1 = new BillItem("large water", 3.0);
//		BillItem item2 = new BillItem("boneless wings", 12.04);
//		BillItem item3 = new BillItem("gucci crowbar", 399);
//		theTestBill.addItem(item1);
//		theTestBill.addItem(item2);
//		theTestBill.addItem(item3);
//		
//		System.out.print(theTestBill.getText());
//	}
	
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
		double tax = subTotal * bill.TAX;
		double tip = subTotal * bill.TIP;
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
