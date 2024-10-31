package edu.westga.cs1302.b_inner_classes.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/** Manages a collection of Products.
 * 
 * @author CS 1302
 * @version Fall 2024
 */
public class ProductList {
	/** Comparator that orders highest price Product's first
	 * 
	 * @author CS 1302
	 * @version Fall 2022
	 */
	private class HighestPriceComparator implements Comparator<Product> {

		@Override
		public int compare(Product thing1, Product thing2) {
			if (thing1.getPrice() < thing2.getPrice()) {
				return 1;
			} else if (thing1.getPrice() > thing2.getPrice()) {
				return -1;
			} else {
				return 0;
			}
		}

	}
	
	
	/** Comparator that orders lowest price Product's first
	 * 
	 * @author CS 1302
	 * @version Fall 2022
	 */
	private class LowestPriceComparator implements Comparator<Product> {

		@Override
		public int compare(Product thing1, Product thing2) {
			if (thing1.getPrice() > thing2.getPrice()) {
				return 1;
			} else if (thing1.getPrice() < thing2.getPrice()) {
				return -1;
			} else {
				return 0;
			}
		}

	}
	
	private List<Product> products;
	
	/** Prints a list with all products ordered as they were added to the list
	 * 
	 * @precondition none
	 * @postcondition none
	 */
	public void printProducts() {
		System.out.println("NOT SORTED");
		this.printProducts(this.products);
		System.out.println();
	}
	
	/** Prints a list with all products ordered lowest price to highest
	 * 
	 * @precondition none
	 * @postcondition none
	 */
	public void printProductsWithLowestPriceFirst() {
		List<Product> tmpProducts = new ArrayList<Product>(this.products);
		Collections.sort(tmpProducts, new LowestPriceComparator());
		System.out.println("LOWEST PRICE FIRST");
		this.printProducts(tmpProducts);
		System.out.println();
	}
	
	/** Prints a list with all products ordered highest price to lowest
	 * 
	 * @precondition none
	 * @postcondition none
	 */
	public void printProductsWithHighestPriceFirst() {
		List<Product> tmpProducts = new ArrayList<Product>(this.products);
		Collections.sort(tmpProducts, new HighestPriceComparator());
		System.out.println("HIGHEST PRICE FIRST");
		this.printProducts(tmpProducts);
		System.out.println();
	}

	private void printProducts(List<Product> tmpProducts) {
		for (Product currProduct : tmpProducts) {
			System.out.println("NAME: " + currProduct.getName() + " PRICE: " + currProduct.getPrice());
		}
	}
	
	/** Create a new product list
	 * 
	 * @precondition none
	 * @postcondition list has no products
	 * 
	 */
	public ProductList() {
		this.products = new ArrayList<Product>();
	}
	
	/** Adds a new product to the list
	 * 
	 * @precondition product != null
	 * @postcondition the product has been added to the list
	 * 
	 * @param product the product to add to the list
	 */
	public void addProduct(Product product) {
		if (product == null) {
			throw new IllegalArgumentException("product must not be null");
		}
		this.products.add(product);
	}
}