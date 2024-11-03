package edu.westga.cs1302.project2.model;

/** Store information for a single Ingredient
 * 
 * @author CS 1302
 * @version Fall 2024
 */
public class Ingredient {
	private String name;
	private String type;
	
	/** Create a new ingredient with the specified name and type
	 * 
	 * @precondition name != null && !name.isEmpty() &&
	 * 				 type != null && !type.isEmpty()
	 * @postcondition getName() == name &&
	 * 				  getType() == type
	 * 
	 * @param name the name of the ingredient
	 * @param type the type for the ingredient
	 * @throws IllegalArgumentException when name is empty or null, type is empty or null, name starts or ends with space, or contains comma or dash
	 */
	public Ingredient(String name, String type) {
		if (name == null || name.isEmpty()) {
			throw new IllegalArgumentException("Must provide a valid name.");
		}
		if (type == null || type.isEmpty()) {
			throw new IllegalArgumentException("Must provide a valid type.");
		}
		if (name.startsWith(" ") || name.endsWith(" ")) {
			throw new IllegalArgumentException("Cannot have ingredient start or end with space \" \"");
		}
		if (name.contains("-") || name.contains(",")) {
			throw new IllegalArgumentException("Ingredient name cannot contain dash \"-\" or comma \",\"");
		}
		this.name = name;
		this.type = type;
	}
	
	/** Return the name of the ingredient
	 * 
	 * @return the name of the ingredient
	 */
	public String getName() {
		return this.name;
	}
	
	/** Return the type of the ingredient
	 * 
	 * @return the type of the ingredient
	 */
	public String getType() {
		return this.type;
	}
	
	@Override
	public String toString() {
		return this.name + "-" + this.type;
	}
	
}
