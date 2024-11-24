package edu.westga.cs1302.project3.test.PersistenceManager;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.project3.model.Task;
import edu.westga.cs1302.project3.model.TaskManager;
import edu.westga.cs1302.project3.model.TaskPersistenceManager;

class TestSaveLoad {
	String tit1 = "turkey time";
	String desc1 = "cook turkey, eat turkey";
	
	String tit2 = "break time";
	String desc2 = "do schoolwork, do more schoolwork";
	
	Task t1 = new Task(tit1, desc1);
	Task t2 = new Task(tit2, desc2);
	
	File file = new File("data.txt");
	@Test
	void test() {
		TaskManager manager = new TaskManager();
		manager.addTask(t1);
		manager.addTask(t2);
		try {
			TaskPersistenceManager.saveToFile(file, manager);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			TaskManager loaded = TaskPersistenceManager.loadFromFile(file);
			assertTrue(loaded.getTaskList().size() == 2);
			
			assertTrue(loaded.getTaskList().get(tit1).equals(t1));
			assertEquals(t2, loaded.getTaskList().get(tit2));
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	void testError() {
		fail("not implemented");
	}

}
