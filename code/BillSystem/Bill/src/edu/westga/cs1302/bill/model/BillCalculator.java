package edu.westga.cs1302.bill.model;



public class BillCalculator {
	
public static final double TAX_RATE = 0.1;
public static final double TIP_RATE = 0.2;

public static double getSubtotal(BillItem[] arr) {
	double subTotal = 0.0;
	for (BillItem item : arr) {
		if (item != null) {
			subTotal += item.getAmount();
		}
	}
	return subTotal;
}

public static double getTax(BillItem[] arr) {
	return getSubtotal(arr) * TAX_RATE;
}

public static double getTip(BillItem[] arr) {
	return getSubtotal(arr) * TIP_RATE;
}

public static double getTotal(BillItem[] arr) {
	return getSubtotal(arr) + getTax(arr) + getTip(arr);
}

}
