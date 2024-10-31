package edu.westga.cs1302.project2.model;

import java.util.List;

/**
 * static utility class for retrieving recipe as string
 * @author rmeliss1
 * @version Project2
 */
public class RecipeUtilities {

	/**
	 * gives entire recipe in one string, first line is name, second line comma separated ingredients
	 * @param recipe recipe to be passed off as string
	 * @return single String over two lines representing recipe
	 */
	public static String oneString(Recipe recipe) {
		return recipe.getName() + System.lineSeparator() + commaSeparatedIngredients(recipe);
	}
	
	private static String commaSeparatedIngredients(Recipe recipe) {
		String result = "";
		List<Ingredient> ingredients = recipe.getIngredients();
		for (Ingredient ing : ingredients) {
			result += ing.getName() + '-' + ing.getType() + ",";
		}
		return result;
	}
}
