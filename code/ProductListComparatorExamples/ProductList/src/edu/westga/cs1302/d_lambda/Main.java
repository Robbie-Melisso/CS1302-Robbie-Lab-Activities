package edu.westga.cs1302.d_lambda;

import edu.westga.cs1302.a_separate_classes.model.Product;
import edu.westga.cs1302.a_separate_classes.model.ProductList;

/** Starting point of the application for the example code demonstrating 
 * separate classes implementing Comparator interface.
 * 
 * @author CS 1302
 * @version Fall 2024
 */
public class Main {

	/** Launches the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("LAMBDA");
		ProductList list = new ProductList();
		list.addProduct(new Product("apple", 2));
		list.addProduct(new Product("orange", 3));
		list.addProduct(new Product("banana", 1));
		
		list.printProducts();
		list.printProductsWithHighestPriceFirst();
		list.printProductsWithLowestPriceFirst();
	}

}
