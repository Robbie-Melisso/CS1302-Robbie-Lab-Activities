package edu.westga.cs1302.Prokect2.test.model.RecipeUtilities;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.project2.model.Ingredient;
import edu.westga.cs1302.project2.model.Recipe;
import edu.westga.cs1302.project2.model.RecipeUtilities;

class TestOneString {

	@Test
	void test() {
		Ingredient one = new Ingredient("cowabunga","eat my shorts");
		Ingredient two = new Ingredient("hello there", "general kenobi");
		List<Ingredient> ingredients= new ArrayList<Ingredient>();
		ingredients.add(one);
		ingredients.add(two);
		assertEquals(RecipeUtilities.oneString(new Recipe("name",ingredients)), "name"+ "\n" +"cowabunga-eat my shorts,hello there-general kenobi" + "\n");
	}

}
