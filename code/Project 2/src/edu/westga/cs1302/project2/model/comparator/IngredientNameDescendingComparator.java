package edu.westga.cs1302.project2.model.comparator;

import edu.westga.cs1302.project2.model.Ingredient;

/**
 * compares two ingredients by alphabetical order
 * @author rmeliss1
 * @version Project2
 */
public class IngredientNameDescendingComparator extends IngredientComparator {

	@Override
	public int compare(Ingredient o1, Ingredient o2) {
		return o1.getName().compareTo(o2.getName());
	}

	@Override
	public String toString() {
		return "Name Descending";
	}
}
