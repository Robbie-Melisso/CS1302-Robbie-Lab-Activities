package edu.westga.cs1302.Project1.test.PantryTracker.FoodUtils;

import static org.junit.jupiter.api.Assertions.*;
import edu.westga.cs1302.Project1.PantryTracker.Food;
import edu.westga.cs1302.Project1.PantryTracker.FoodUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


import org.junit.jupiter.api.Test;

class TestTallyAllQuantities {

	String name = "name";
	String type = "type";
	int q1 = 15;
	int q2 = 4;
	int q3 = 2;
	int q4 = 19;
	Food food1 = new Food(name, type);
	Food food2 = new Food(name, type);
	Food food3 = new Food(name, type);
	Food food4 = new Food(name, type);
	
	@Test
	void testAtEmpty() {
		ObservableList<Food> list = FXCollections.observableArrayList();
		assertEquals(FoodUtil.tallyAllQuantities(list), 0);
		
	}
	
	@Test
	void testAtOneFood() {
		food1.setQuantity(q1);
		ObservableList<Food> list = FXCollections.observableArrayList();
		list.add(food1);
		assertEquals(FoodUtil.tallyAllQuantities(list), q1);

	}

	@Test
	void testAtFilled() {
		food1.setQuantity(q1);
		food2.setQuantity(q2);
		food3.setQuantity(q3);
		food4.setQuantity(q4);
		ObservableList<Food> list = FXCollections.observableArrayList();
		list.addAll(food1,food2,food3,food4);
		assertEquals(FoodUtil.tallyAllQuantities(list), q1+q2+q3+q4);

	}
}
