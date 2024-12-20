package edu.westga.cs1302.project2.model.comparator;

import edu.westga.cs1302.project2.model.Ingredient;

/**
 * compares ingredient names in reverse alphabetical order
 * @author rmeliss1
 * @version Project2
 */
public class IngredientNameAscendingComparator extends IngredientComparator {

	@Override
	public int compare(Ingredient o1, Ingredient o2) {
		return o2.getName().compareTo(o1.getName());
	}
	
	@Override
	public String toString() {
		return "Name Ascending";
	}
}
