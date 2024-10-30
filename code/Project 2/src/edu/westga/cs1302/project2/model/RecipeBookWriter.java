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
	 * does not accept recipe with duplicate name with different casing
	 * @param recipe The recipe to be written to the file
	 * @param fileLoc the file to be used as recipe book
	 * @throws FileNotFoundException
	 * @throws IllegalStateException
	 */
	public void writeToBook(Recipe recipe, String fileLoc) throws FileNotFoundException, IllegalStateException, IOException {
		
		/*
		 * check for duplicate name in file
		 */
		try (Scanner reader = new Scanner(fileLoc)) {
			while (reader.hasNextLine()) {
				String line = reader.nextLine();
				if (line.isBlank() || line.isEmpty()) {
					if (line.equalsIgnoreCase(recipe.getName()) || line.strip().equalsIgnoreCase(recipe.getName())) {
						throw new IllegalStateException("Duplicate recipe name already exists, Not allowed to have identical name multiple times, not case sensitive");
					}
				}
			}
		}
		
		/**
		 * no duplicate found, write to book
		 */
		try (FileWriter writer = new FileWriter(fileLoc)) {
			writer.append(RecipeUtilities.oneString(recipe));
		}
	}
	
}
