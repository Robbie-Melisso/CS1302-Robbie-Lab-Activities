package edu.westga.cs1302.cms.model;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Supports saving and loading student data,
 * 
 * @author CS 1302
 * @version Fall 2024
 */
public abstract class StudentDataPersistenceManager {
	public static final String DATA_FILE = "data.txt";

	/**
	 * Save the students!
	 * 
	 * Writes each student to DATA_FILE Each student is written on a separate line
	 * Each line uses the following format: name,grade
	 * 
	 * @precondition students != null
	 * @postcondition none
	 * 
	 * @param students the set of students to save
	 * @throws IOException
	 */
	public abstract void saveStudentData(Student[] students) throws IOException;
	
	/**
	 * Load the students!
	 * 
	 * Reads from DATA_FILE File is assumed to use the same format described by
	 * saveStudentData
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the set of students loaded
	 * @throws FileNotFoundException file at DATA_FILE location does not exist
	 * @throws IOException           invalid or missing name/grade found when trying
	 *                               to create a student
	 */
	public abstract Student[] loadStudentData() throws FileNotFoundException, IOException ;
	
	@Override
	public String toString() {
		return "CSV";
	}

}
