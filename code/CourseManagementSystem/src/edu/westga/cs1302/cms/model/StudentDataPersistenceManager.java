package edu.westga.cs1302.cms.model;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/** Supports saving and loading student data,
 * 
 * @author CS 1302
 * @version Fall 2024
 */
public class StudentDataPersistenceManager {
	public static final String DATA_FILE = "data.txt";
	
	/** Save the students!
	 * 
	 * Writes each student to DATA_FILE
	 * Each student is written on a separate line
	 * Each line uses the following format:
	 * 		name,grade
	 * 
	 * @precondition students != null
	 * @postcondition none
	 * 
	 * @param students the set of students to save
	 * @throws IOException 
	 */
	public static void saveStudentData(Student[] students) throws IOException {
		try (FileWriter writer = new FileWriter(DATA_FILE)) {
			for (Student currStudent : students) {
				writer.write(currStudent.getName() + "," + currStudent.getGrade() + System.lineSeparator());
			}
		}
	}

	/** Load the students!
	 * 
	 * Reads from DATA_FILE
	 * File is assumed to use the same format described by saveStudentData
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the set of students loaded
	 */
	public static Student[] loadStudentData() {
		List<Student> students = new ArrayList<Student>();
		
		return students.toArray(new Student[students.size()]);
	}
	
}
