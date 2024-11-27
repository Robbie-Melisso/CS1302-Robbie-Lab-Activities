package edu.westga.cs1302.project3.model;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.io.FileWriter;

/**
 * save and load TaskManager objects to passed file
 * @author rmeliss1
 * @version Proj3.2.1
 */
public class TaskPersistenceManager {
	
	public static final String  REGEX = ":";
	
	/**Saves given task manager tasks to given file in text format
	 * 
	 * @param file object to use as save file
	 * @param data taskManager object to save representation of
	 * @throws IOException file unreadable
	 * @throws IllegalArgumentException passed null argument for data
	 */
	public static void saveToFile(File file, TaskManager data) throws IOException, IllegalArgumentException {
		try (FileWriter writer = new FileWriter(file)) {
			if (data == null) {
				throw new IllegalArgumentException("Null taskmanager object");
			}
			for (Map.Entry<String, Task> entry : data.getTaskList().entrySet()) {
				writer.write(TaskPersistenceManager.REGEX + entry.getValue().getTitle() + TaskPersistenceManager.REGEX
						+ entry.getValue().getDescription() /*+ TaskPersistenceManager.REGEX*/ + System.lineSeparator());
			}
		}
		
	}
	
	/** read task manager from given text file
	 * 
	 * @param file file to read from
	 * @return manager constructed taskManger object
	 * @throws IOException when file unreadable
	 * @throws IllegalArgumentException when file incorrectly formatted or populated
	 * @throws IllegalStateException when file contains multiple entries of identical task titles
	 */
	public static TaskManager loadFromFile(File file) throws IOException, IllegalArgumentException, IllegalStateException {
		TaskManager manager = new TaskManager();
		try (Scanner reader = new Scanner(file)) {
			reader.useDelimiter(REGEX);
			String title = "default title";
			String desc = "default description";
			while (reader.hasNext()) {
				
				try {
				title = reader.next();
				desc = reader.next();
				} catch (IndexOutOfBoundsException | NoSuchElementException err) {
					throw new IllegalStateException("File format invalid. Delimiter usage invalid" + System.lineSeparator()
					+ "delimeter should be at beginning of title, between title and description, and after description." + System.lineSeparator()
					+ title + desc + "  " + err.getClass());
				}
				manager.addTask(new Task(title, desc.stripTrailing()));
				
			}
			return manager;
		}
	}
}
