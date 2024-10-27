package edu.westga.cs1302.project2.model;

/**
 * compares ingredient names in reverse alphabetical order
 * @author rmeliss1
 * @version Project2
 */
public class IngredientAlphabeticalAscendingComparator extends IngredientComparator {

	@Override
	public int compare(Ingredient o1, Ingredient o2) {
		return o2.getName().compareToIgnoreCase(o1.getName());
	}
	
	@Override
	public String toString() {
		return "Alphabetical Ascending";
	}
}
