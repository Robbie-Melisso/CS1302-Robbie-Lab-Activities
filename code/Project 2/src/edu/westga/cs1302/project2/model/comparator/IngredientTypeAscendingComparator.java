package edu.westga.cs1302.project2.model.comparator;

import edu.westga.cs1302.project2.model.Ingredient;

/**
 * compares by Type in ascending order, then by alphabetical name
 * @author rmeliss1
 * @version Project2
 */
public class IngredientTypeAscendingComparator extends IngredientComparator {

	@Override
	public int compare(Ingredient o1, Ingredient o2) {
		if (o2.getType().compareToIgnoreCase(o1.getType()) != 0) {
			return o2.getType().compareToIgnoreCase(o1.getType());
		} else {
			IngredientNameDescendingComparator comp = new IngredientNameDescendingComparator();
			return comp.compare(o1, o2);
		}
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Type Ascending";
	}

}
