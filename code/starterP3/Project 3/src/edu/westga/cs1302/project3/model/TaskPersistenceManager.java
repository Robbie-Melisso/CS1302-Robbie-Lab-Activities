package edu.westga.cs1302.project3.model;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;
import java.io.FileWriter;

/**
 * save and load TaskManager objects to passed file
 * @author rmeliss1
 * @version Proj3.1
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
				writer.write(entry.getValue().getTitle() + TaskPersistenceManager.REGEX + entry.getValue().getDescription() + System.lineSeparator());
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
			while (reader.hasNextLine()) {
				String[] separatedLine = reader.nextLine().split(TaskPersistenceManager.REGEX);
				manager.addTask(new Task(separatedLine[0], separatedLine[1]));
			}
			return manager;
		}
	}
}
