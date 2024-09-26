package edu.westga.cs1302.Project1.test.PantryTracker.Food;

import static org.junit.jupiter.api.Assertions.*;
import edu.westga.cs1302.Project1.PantryTracker.Food;

import org.junit.jupiter.api.Test;

class TestSetQuantity {
	Food food = new Food("name", "type");
	@Test
	void testSunnyDay() {
		assertEquals(food.getQuantity(), 0);
		food.setQuantity(18);
		assertEquals(food.getQuantity(), 18);
	}
	
	@Test
	void testThrowsError() {
		assertThrows(IllegalArgumentException.class, ()->{
			food.setQuantity(-10);
		});
	}
	

}
