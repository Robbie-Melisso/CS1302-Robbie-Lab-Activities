/**
 * 
 */
package edu.westga.cs1302.Project1.test.PantryTracker.Food;

import edu.westga.cs1302.Project1.PantryTracker.Food;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * 
 */
class TestToString {

	@Test
	void testOutputAtZero() {
		String name = "pepsi";
		String type = "cola";
		Food obj1 = new Food(name,type);
		
		assertEquals(obj1.toString(), name + Food.TO_STRING_SEPARATOR + 0);
	}

}
