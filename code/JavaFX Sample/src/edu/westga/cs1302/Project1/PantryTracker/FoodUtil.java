package edu.westga.cs1302.Project1.PantryTracker;

import javafx.collections.ObservableList;

/**utility class meant for static methods relating to the Food class
 * @author rmeliss1
 * @version 1
 */
public final class FoodUtil {

	/**private constructor prevents instantiation, which is not intended to be possible
	 * 
	 */
	private FoodUtil() {
		
	}
	
	/**takes in list of Food items and returns the total quantity of all items in the list
	 * 
	 * @param list observable list of Food items
	 * @return total quantities of all items in list combined
	 */
	public static int tallyAllQuantities(ObservableList<Food> list) {
    	int total = 0;
    	for (Food curr : list) {
    		total += curr.getQuantity();
    	}
    	
    	return total;
    }
}
