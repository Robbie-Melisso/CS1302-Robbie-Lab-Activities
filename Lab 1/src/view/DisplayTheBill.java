package view;

import edu.westga.cs1302.lab1.model.Bill;
import edu.westga.cs1302.lab1.model.BillItem;

/**
 * create bill object and print it to console
 * @author rmeliss1
 * @version production steps 1 and 2
 */
public class DisplayTheBill {

	/**
	 * create a bill, add items to it, then display the bill
	 * @param args arguments for main method
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
	}

}
