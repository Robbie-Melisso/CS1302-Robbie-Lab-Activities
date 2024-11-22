package edu.westga.cs1302.project3.model;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;
import java.io.FileWriter;

public class TaskPersistenceManager {
	
	public static final String  REGEX = ":";

	/**
	 * 
	 * @param file
	 * @param map
	 * @throws IOException
	 * @throws IllegalArgumentException
	 */
	public static void saveToFile(File file, Map<String, Task> map) throws IOException, IllegalArgumentException {
		try (FileWriter writer = new FileWriter(file)) {
			//if (map == null) {
			//	throw new IllegalArgumentException();
			//}
			for (Map.Entry<String, Task> entry : map.entrySet()) {
				writer.write(entry.getValue().getTitle() + TaskPersistenceManager.REGEX + entry.getValue().getDescription() + System.lineSeparator());
			}
		}
		
	}
	
	/**
	 * 
	 * @param file
	 * @return
	 * @throws IOException
	 * @throws IllegalArgumentException
	 * @throws IllegalStateException
	 */
	public TaskManager loadFromFile(File file) throws IOException, IllegalArgumentException, IllegalStateException {
		TaskManager manager = new TaskManager();
		try (Scanner reader = new Scanner(file)) {
			while (reader.hasNextLine()) {
				String[] separatedLine = reader.nextLine().split(TaskPersistenceManager.REGEX);
				manager.addTask(new Task(separatedLine[0],separatedLine[1]));
			}
			return manager;
		}
	}
}
