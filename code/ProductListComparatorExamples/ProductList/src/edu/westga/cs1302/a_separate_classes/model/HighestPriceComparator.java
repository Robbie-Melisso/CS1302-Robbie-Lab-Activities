package edu.westga.cs1302.a_separate_classes.model;

import java.util.Comparator;

/** Comparator that orders highest price Product's first
 * 
 * @author CS 1302
 * @version Fall 2024
 */
public class HighestPriceComparator implements Comparator<Product> {

	@Override
	public int compare(Product thing1, Product thing2) {
		if (thing1.getPrice() < thing2.getPrice()) {
			return 1;
		} else if (thing1.getPrice() > thing2.getPrice()) {
			return -1;
		} else {
			return 0;
		}
	}

}
