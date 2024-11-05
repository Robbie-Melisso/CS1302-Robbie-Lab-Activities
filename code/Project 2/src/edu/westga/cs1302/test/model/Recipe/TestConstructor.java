package edu.westga.cs1302.test.model.Recipe;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.project2.model.Ingredient;
import edu.westga.cs1302.project2.model.Recipe;

class TestConstructor {
	Ingredient one = new Ingredient("cowabunga","eat my shorts");
	Ingredient two = new Ingredient("hello there", "general kenobi");
	List<Ingredient> ingredients= new ArrayList<Ingredient>();
	
	@Test
	void testSunnyDay() {
		ingredients.add(one);
		ingredients.add(two);
		Recipe recipe = new Recipe("recipe", ingredients);
		assertEquals(recipe.getName(),"recipe");
		assertTrue(recipe.getIngredients().get(0).equals(one));
	}

	@Test
	void testInvalidName() {
		ingredients.add(one);
		ingredients.add(two);
		
		assertThrows(IllegalArgumentException.class, () -> new Recipe(" hello", ingredients));
		assertThrows(IllegalArgumentException.class, () -> new Recipe("hello ", ingredients));
		assertThrows(IllegalArgumentException.class, () -> new Recipe("", ingredients));
		assertThrows(IllegalArgumentException.class, () -> new Recipe(null, ingredients));
		
		
	}
	
	@Test
	void testInvalidList() {
		List<Ingredient> empty = new ArrayList<Ingredient>();
		assertThrows(IllegalArgumentException.class, ()-> new Recipe("name", empty));
	}
}
