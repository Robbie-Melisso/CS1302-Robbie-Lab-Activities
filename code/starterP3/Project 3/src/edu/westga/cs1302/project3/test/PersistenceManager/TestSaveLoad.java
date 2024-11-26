package edu.westga.cs1302.project3.test.PersistenceManager;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.project3.model.Task;
import edu.westga.cs1302.project3.model.TaskManager;
import edu.westga.cs1302.project3.model.TaskPersistenceManager;

//Save and load functions tested together to avoid overwritten data from interfering with load function testing
//testing load function on correct formatting of data must be done after testing correct save formatting
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
		clearFile();
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
	void testSaveErrors() {
		clearFile();
		TaskManager manager = new TaskManager();
		manager.addTask(t1);
		manager.addTask(t2);
		assertThrows(IllegalArgumentException.class, () -> {
			TaskPersistenceManager.saveToFile(file, null);
		});
	}
	
	@Test
	void testLoadErrors(){
		clearFile();
		TaskManager manager = new TaskManager();
		manager.addTask(t1);
		manager.addTask(t2);
		//make invalid file format
		try(FileWriter writer = new FileWriter(file)){
			writer.write("star destroyer 1:destroy the stars" + System.lineSeparator()
			+ "make Pizza: buy tomatoes, conquer Italy: control pasta facilites, starve resistance");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			assertThrows(IllegalStateException.class, () -> {
				TaskPersistenceManager.loadFromFile(file);
			});
		} catch (IllegalArgumentException err) {
			fail("Attempted to create a task object using a colon in one field, should not be possible");
		}
		
		//make another invalid file format using blank elements
		try(FileWriter writer = new FileWriter(file)){
			writer.write("Task1: go forth and conquer" + System.lineSeparator()
			+ "conquer: ");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertThrows(IllegalArgumentException.class, () -> {
			TaskPersistenceManager.loadFromFile(file);
		});
	}

	private void clearFile() {
		try(FileWriter writer = new FileWriter(file)){
			writer.write("");
		} catch (IOException err) {
			// TODO Auto-generated catch block
			err.printStackTrace();
		}
	}
}
