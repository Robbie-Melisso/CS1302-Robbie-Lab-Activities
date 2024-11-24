package edu.westga.cs1302.project3.test.TaskManager;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.project3.model.Task;
import edu.westga.cs1302.project3.model.TaskManager;

class TestAddTask {
	String tit1 = "turkey time";
	String desc1 = "cook turkey, eat turkey";
	
	String tit2 = "break time";
	String desc2 = "do schoolwork, do more schoolwork";
	
	Task t1 = new Task(tit1,desc1);
	Task t2 = new Task(tit2, desc2);
	
	Task d1 = new Task(tit1, "Hi there");
	@Test
	void test() {
		TaskManager manager = new TaskManager();
		assertTrue(manager.getTaskList().size() == 0);
		assertTrue(manager.addTask(t1));
		assertTrue(manager.addTask(t2));
		assertTrue(manager.getTaskList().size() == 2);
		assertEquals(manager.getTaskList().get(tit1), t1);
	}
	
	@Test
	void testDuplicates() {
		TaskManager manager = new TaskManager();
		assertTrue(manager.getTaskList().size() == 0);
		assertTrue(manager.addTask(t1));
		assertTrue(manager.addTask(t2));
		assertTrue(manager.getTaskList().size() == 2);
		assertThrows(IllegalStateException.class, () -> {
			manager.addTask(d1);
		});
	}

}
