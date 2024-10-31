package edu.westga.cs1302.a_separate_classes.model;

/** Stores information for a single Product
 * 
 * @author CS 1302
 * @version Fall 2024
 */
public class Product {
	private String name;
	private int price;
	
	/** Returns the name of the product
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the name of the product
	 */
	public String getName() {
		return this.name;
	}
	
	/** Returns the price of the product
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the price of the product
	 */
	public int getPrice() {
		return this.price;
	}
	
	/** Create a new Product
	 * 
	 * @precondition name != null &&
	 * 				 price > 0
	 * @postcondition getName() == name &&
	 * 				  getPrice() == price
	 * 
	 * @param name name of the Product
	 * @param price price of the Product
	 */
	public Product(String name, int price) {
		if (name == null) {
			throw new IllegalArgumentException("name must not be null");
		}
		if (price <= 0) {
			throw new IllegalArgumentException("price must be positive");
		}
		this.name = name;
		this.price = price;
	}
}
