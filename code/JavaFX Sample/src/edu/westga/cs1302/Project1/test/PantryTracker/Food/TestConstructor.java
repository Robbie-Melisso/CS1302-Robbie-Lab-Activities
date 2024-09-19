/**
 * 
 */
package edu.westga.cs1302.Project1.test.PantryTracker.Food;

import static org.junit.jupiter.api.Assertions.*;
import edu.westga.cs1302.Project1.PantryTracker.Food;

import org.junit.jupiter.api.Test;

/**
 * 
 */
class TestConstructor {
	
	@Test
	void sunnyDayTest() {
		String name = "pepsi";
		String type = "cola";
		Food testFood = new Food(name, type);
		assertEquals(name, testFood.getName());
		assertEquals(type, testFood.getType());
		assertEquals(0, testFood.getQuantity());
	}
	
	@Test
	void testNullValues() {
		String name = "pepsi";
		String type = "cola";
		
		assertThrows(IllegalArgumentException.class, ()-> {
			new Food(null, type);
		});
		assertThrows(IllegalArgumentException.class, ()-> {
			new Food(name, null);
		});
			
	}	

}
