package edu.westga.cs1302.Prokect2.test.model.RecipeUtilities;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.project2.model.Ingredient;
import edu.westga.cs1302.project2.model.Recipe;
import edu.westga.cs1302.project2.model.RecipeUtilities;

class TestRecipeTextifier {
	Ingredient one = new Ingredient("cowabunga","eat my shorts");
	Ingredient two = new Ingredient("hello there", "general kenobi");
	List<Ingredient> ingredients= new ArrayList<Ingredient>();
	@Test
	void test() {
		ingredients.add(one);
		ingredients.add(two);
		Recipe recipe = new Recipe("name1", ingredients);
		List<Ingredient> ingredient2 = new ArrayList<Ingredient>();
		ingredient2.add(two);
		ingredient2.add(one);
		Recipe recipe2 = new Recipe("name2", ingredient2);
		List<Recipe> recipes = new ArrayList<Recipe>();
		recipes.add(recipe);
		recipes.add(recipe2);
		String textified = RecipeUtilities.recipeTextifier(recipes);
		assertEquals(textified, RecipeUtilities.oneString(recipe) + "\n" + RecipeUtilities.oneString(recipe2) + "\n");
	}

}
