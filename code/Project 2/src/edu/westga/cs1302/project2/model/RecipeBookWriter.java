package edu.westga.cs1302.project2.model;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * class to write recipes to recipe book file so long as no recipe with the same name exists already ---- does not check the contents of recipe
 * @author rmeliss1
 * @version Project2
 */
public class RecipeBookWriter {

	/**
	 * write recipe to book file after ensuring no duplicate name exists in file, throws IllegalStateException if duplicate name is found
	 * @param recipe
	 * @param fileLoc
	 * @throws FileNotFoundException
	 * @throws IllegalStateException
	 */
	public void writeToBook(Recipe recipe, String fileLoc) throws FileNotFoundException, IllegalStateException, IOException {
		
		/*
		 * check for duplicate name in file
		 */
		try (Scanner reader = new Scanner(fileLoc)) {
			String name = recipe.getName();
			while (reader.hasNextLine()) {
				String line = reader.nextLine();
				if (line.isBlank() || line.isEmpty()) {
					if (line.equalsIgnoreCase(recipe.getName()) || line.strip().equalsIgnoreCase(name)) {
						throw new IllegalStateException("duplicate recipe already exists");
					}
				}
			}
		}
		
		/**
		 * no duplicate found
		 */
		try (FileWriter writer = new FileWriter(fileLoc)) {
			
		}
	}
	
}
