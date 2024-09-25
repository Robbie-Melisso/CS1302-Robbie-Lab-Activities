package edu.westga.cs1302.Project1.PantryTracker;

/**Data class for food
 * 
 * @author rmeliss1
 * @version 1
 */
public class Food {
	public static final String TO_STRING_SEPARATOR = " --- ";
	
	private final String name;
	private final String type;
	private int quantity;
	
	/**constructor with default quantity value of 0
	 * 
	 * @param name specific name of food item
	 * @param type general type of food
	 * @throws IllegalArgumentException when given null arguments
	 */
	public Food(String name, String type) throws IllegalArgumentException {
		if (name == null || type == null) {
			throw new IllegalArgumentException("Name or Type cannot be null");
		}
		if (name.length() < 2 || type.length() < 2) {
			throw new IllegalArgumentException("Name and type must be at least 2 characters");
		}
		this.name = name;
		this.type = type;
		this.quantity = 0;
	}

	/** return name
	 * 
	 * @return this.name
	 */
	public String getName() {
		return this.name;
	}
	
	/** return type
	 * 
	 * @return this.type
	 */
	public String getType() {
		return this.type;
	}
	
	/**return quantity
	 * 
	 * @return this.quantity
	 */
	public int getQuantity() {
		return this.quantity;
	}
	
	/** set quantity to given integer
	 * 
	 * @param quantity new amount for quantity
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	/**returns string containing name and quantity
	 * @return String representing name of item and quantity
	 */
	public String toString() {
		return this.name + TO_STRING_SEPARATOR + this.quantity;
	}
	
}
