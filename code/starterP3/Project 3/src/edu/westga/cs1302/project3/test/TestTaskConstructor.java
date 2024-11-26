package edu.westga.cs1302.project3.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import edu.westga.cs1302.project3.model.Task;

class TestTaskConstructor {
	String tit1 = "turkey time";
	String desc1 = "cook turkey, eat turkey";
	
	String tit2 = "break time";
	String desc2 = "do schoolwork, do more schoolwork";
	
	@Test
	void testSunyDay() {
		Task turkey = new Task(tit1, desc1);
		
		assertEquals(turkey.getTitle(),tit1);
		assertEquals(turkey.getDescription(),desc1);
	}
	
	@Test
	void testBlankFields() {
		assertThrows(IllegalArgumentException.class, () -> {
			Task relax = new Task(" ",desc2);
		});
		assertThrows(IllegalArgumentException.class, () -> {
			Task relax = new Task(tit2," ");
		});
	}

}
