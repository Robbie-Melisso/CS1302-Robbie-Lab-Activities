package edu.westga.cs1302.project2.model;

/**
 * compares two ingredients by alphabetical order
 * @author rmeliss1
 * @version Project2
 */
public class IngredientAlphabeticalDescendingComparator extends IngredientComparator {

	@Override
	public int compare(Ingredient o1, Ingredient o2) {
		return o1.getName().compareToIgnoreCase(o2.getName());
	}

	@Override
	public String toString() {
		return "Alphabetical Descending";
	}
}
