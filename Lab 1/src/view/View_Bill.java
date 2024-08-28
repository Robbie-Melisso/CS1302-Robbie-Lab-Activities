package view;

import edu.westga.cs1302.lab1.model.Bill;
import edu.westga.cs1302.lab1.model.BillItem;
import java.util.Scanner;

/**
 * @author rmeliss1
 * @version main arg class version
 */
public class View_Bill {

	/**
	 * create a bill, add items to it, then display the bill
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Bill theTestBill = new Bill();
		BillItem item1 = new BillItem("large water", 3.0);
		BillItem item2 = new BillItem("boneless wings", 12.04);
		BillItem item3 = new BillItem("gucci crowbar", 399);
		theTestBill.addItem(item1);
		theTestBill.addItem(item2);
		theTestBill.addItem(item3);
		
		System.out.print(theTestBill.getText());
//		displayBill(theTestBill);
	}

	/**
	 * display bill to eclipse console
	 * @param toDisplay
	 */
	public void displayBill(Bill toDisplay) {
		System.out.print(toDisplay.getText());
	}
}
