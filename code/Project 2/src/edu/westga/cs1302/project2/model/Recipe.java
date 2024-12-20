package edu.westga.cs1302.project2.model;

import java.util.List;

/**
 * store information for a single recipe, holding a list of ingredients and a unique name
 * @author rmeliss1
 * @version project2
 */
public class Recipe {
	private String name;
	private List<Ingredient> ingredients;
	
	/**
	 * create new recipe using name and list of ingredients
	 * @param name ingredient name
	 * @param ingredients list of ingredients in recipe
	 * @throws IllegalArgumentException when name is empty, ingredients is empty, name starts or ends with space
	 */
	public Recipe(String name, List<Ingredient> ingredients) throws IllegalArgumentException {
		if (name == null || name.isEmpty()) {
			throw new IllegalArgumentException("Recipe name is invalid");
		}
		if (ingredients.isEmpty() || ingredients == null) {
			throw new IllegalArgumentException("Invalid ingredient List provided, must have at least one ingredient");
		}
		if (name.startsWith(" ") || name.endsWith(" ")) {
			throw new IllegalArgumentException("Invalid recipe name, name cannot start or end with space");
		}
		this.name = name;
		this.ingredients = ingredients;
	}

	/**get name for recipe
	 * @return recipe name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * get list of all ingredients
	 * @return list of all ingredients
	 */
	public List<Ingredient> getIngredients() {
		return this.ingredients;
	}
	
	@Override
	/**
	 * gives recipe as string to allow for display in UI in standardized way
	 */
	public String toString() {
		return RecipeUtilities.oneString(new Recipe(this.name, this.ingredients));
	}
}
