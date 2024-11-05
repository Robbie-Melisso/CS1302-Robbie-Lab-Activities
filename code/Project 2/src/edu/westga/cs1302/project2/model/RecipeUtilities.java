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
		int variable = 0;
		for (variable = 0; variable < ingredients.size() - 1; variable++) {
			result += ingredients.get(variable).toString() + ",";
		}
		result += ingredients.get(variable /*+ 1*/);
		result += System.lineSeparator();
		return result;
	}

	/**
	 * create textified version of recipe list for display in textArea of GUI
	 * @param recipes list of recipes to textify
	 * @return single string of recipes, one line for name, next line ingredients, empty line before next recipe
	 */
	public static String recipeTextifier(List<Recipe> recipes) {
		String textified = "";
		for (Recipe recipe : recipes) {
			textified += RecipeUtilities.oneString(recipe) + "\n";
		}
		//recipes.forEach(toTextify -> textified += RecipeUtilities.oneString(toTextify) + "\n");
		return textified;
	}
}
