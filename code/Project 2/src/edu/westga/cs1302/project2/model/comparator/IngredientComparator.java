package edu.westga.cs1302.project2.model.comparator;

import java.util.Comparator;

import edu.westga.cs1302.project2.model.Ingredient;

/**compares ingredients
 * @author rmeliss1
 * @version Project2
 */
public abstract class IngredientComparator implements Comparator<Ingredient> {

	/**
	 * compare ingredients by set criteria, <0 if value of o1 is lower, >0 if o2 i
	 */
	@Override
	public abstract int compare(Ingredient o1, Ingredient o2);
	
	@Override
	public abstract String toString();
}
