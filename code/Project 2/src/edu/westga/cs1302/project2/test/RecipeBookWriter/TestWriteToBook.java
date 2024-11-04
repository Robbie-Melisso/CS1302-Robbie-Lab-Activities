package edu.westga.cs1302.project2.test.RecipeBookWriter;

import java.io.FileWriter;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.project2.model.Ingredient;
import edu.westga.cs1302.project2.model.Recipe;
import edu.westga.cs1302.project2.model.RecipeBookWriter;
import edu.westga.cs1302.project2.model.RecipeUtilities;

class TestWriteToBook {
	Ingredient one = new Ingredient("cowabunga","eat my shorts");
	Ingredient two = new Ingredient("hello there", "general kenobi");
	List<Ingredient> ingredients= new ArrayList<Ingredient>();
	
	

	@Test
	void testSunnyDay() throws IOException {
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
		try (FileWriter writer = new FileWriter(new File("data.txt"))){
			writer.write("");
		}
		try {
			RecipeBookWriter.writeToBook(recipe, new File("data.txt"));
			RecipeBookWriter.writeToBook(recipe2, new File("data.txt"));
		} finally {
			try (Scanner reader = new Scanner(new File("data.txt"))){
				assertEquals(reader.nextLine()+reader.nextLine(),RecipeUtilities.oneString(recipe));
			}
//		try (Scanner reader = new Scanner(new File("data.txt"))){
//			assertEquals(RecipeUtilities.oneString(recipe) + RecipeUtilities.oneString(recipe2), reader.nextLine() + reader.nextLine() + reader.nextLine());
		}
	}
	
	@Test
	void testDuplicateName() throws FileNotFoundException, IllegalStateException, IOException {
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
		try (FileWriter writer = new FileWriter("data.txt")){
			writer.write("");
		}
		RecipeBookWriter.writeToBook(recipe, new File("data.txt"));
		assertThrows(IllegalStateException.class, ()-> RecipeBookWriter.writeToBook(recipe, new File("data.txt")));
		
	}
	
	
	
		
		
}
