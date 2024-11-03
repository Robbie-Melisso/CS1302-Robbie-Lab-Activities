package edu.westga.cs1302.project2.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * manages reading of recipe book file
 * @author rmeliss1
 * @version Project2
 */
public class RecipeBookReader {

	/**
	 * read recipe book file given file location returns list of all recipes
	 * @param fileLoc textual file location for recipe book
	 * @return list of all recipes in recipe book
	 * @throws FileNotFoundException if file is not accessible
	 * @throws IllegalStateException when file incorrectly formatted, final line should be empty 
	 */
	public static List<Recipe> readAllRecipes(String fileLoc) throws FileNotFoundException, IllegalStateException {
		List<Recipe> compiledRecipes = new ArrayList<Recipe>();
		try (Scanner reader = new Scanner(new File(fileLoc))) {
			while (reader.hasNextLine()) {
				String nameLine = reader.nextLine().strip();
				if (nameLine.isBlank() || nameLine.isEmpty()) {
					//last line in file is empty line
					break;
				}
				String ingOneline = reader.nextLine().strip();
				String[] ingArr = ingOneline.split(",");
				List<Ingredient> ingredientsList = new ArrayList<Ingredient>();
				for (String ing : ingArr) {
					String[] separatedIngredient = ing.split("-");
					ingredientsList.add(new Ingredient(separatedIngredient[0].strip(), separatedIngredient[1]));
				}
				compiledRecipes.add(new Recipe(nameLine, ingredientsList));
			}
		} catch (NoSuchElementException err) {
			throw new IllegalStateException("File incorrectly formatted");
		}
		return compiledRecipes;
	}
	
	/**
	 * searches through file for all recipes, returns list of recipes that contain specified ingredient
	 * @param ingredient ingredient to search for
	 * @param fileLoc text representation of file of recipe book
	 * @return list of all recipes containing ingredient
	 * @throws FileNotFoundException if file is not accessible
	 * @throws IllegalStateException when file incorrectly formatted, final line should be empty 
	 */
	public static List<Recipe> getRelevantRecipes(Ingredient ingredient, String fileLoc) throws FileNotFoundException, IllegalStateException {
		List<Recipe> relevantRecipes = new ArrayList<Recipe>();
		List<Recipe> allRecipes = RecipeBookReader.readAllRecipes(fileLoc);
		for (Recipe recipe : allRecipes) {
			if (recipe.getIngredients().contains(ingredient)) {
				relevantRecipes.add(recipe);
			}
		}
		return relevantRecipes;
	}
		
}
