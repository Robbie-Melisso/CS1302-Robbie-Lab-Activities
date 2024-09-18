package edu.westga.cs1302.bill.model;

/**
 * contains the calculations for the bill
 * 
 * @author rmeliss1
 * @version 1
 */
public class BillCalculator {

	public static final double TAX_RATE = 0.1;
	public static final double TIP_RATE = 0.2;

	/**calculates the subtotal for the bill given the array of items
	 * 
	 * @param arr array of BillItems
	 * @return subtotal calculated sum cost of all items
	 */
	public static double getSubtotal(BillItem[] arr) {
		double subTotal = 0.0;
		for (BillItem item : arr) {
			if (item != null) {
				subTotal += item.getAmount();
			}
		}
		return subTotal;
	}

	/**
	 * calculates the tax for the bill
	 * 
	 * @param arr array of BillItems
	 * @return calculated tax from the items
	 */
	public static double getTax(BillItem[] arr) {
		return getSubtotal(arr) * TAX_RATE;
	}

	/**
	 * calculates tip for the bill
	 * 
	 * @param arr array of BillItems
	 * @return calculated tip
	 */
	public static double getTip(BillItem[] arr) {
		return getSubtotal(arr) * TIP_RATE;
	}

	/**
	 * calculates the complete bill total
	 * 
	 * @param arr array of BillItems
	 * @return calculated total cost of the bill
	 */
	public static double getTotal(BillItem[] arr) {
		return getSubtotal(arr) + getTax(arr) + getTip(arr);
	}

}
