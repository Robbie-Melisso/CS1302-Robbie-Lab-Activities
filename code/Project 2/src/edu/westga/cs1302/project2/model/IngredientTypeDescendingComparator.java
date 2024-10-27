package edu.westga.cs1302.project2.model;

/**
 * compares ingredients by their type in ascending order, then name in alphabetical order
 * @author rmeliss1
 * @version Project2
 */
public class IngredientTypeDescendingComparator extends IngredientComparator {

	@Override
	public int compare(Ingredient o1, Ingredient o2) {
		if (o1.getType().compareToIgnoreCase(o2.getType()) != 0) {
			return o1.getType().compareToIgnoreCase(o2.getType());
		} else {
			IngredientNameDescendingComparator comp = new IngredientNameDescendingComparator();
			return comp.compare(o1, o2);
		}
		
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Type Descending";
	}

}
