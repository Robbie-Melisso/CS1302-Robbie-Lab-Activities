package edu.westga.cs1302.cms.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Supports saving and loading student data,
 * 
 * @author CS 1302
 * @version Fall 2024
 */
public class StudentDataPersistenceManager {
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
	public static void saveStudentData(Student[] students) throws IOException {
		if (students == null) {
			throw new IllegalArgumentException("Must provide an array of students");
		}
		try (FileWriter writer = new FileWriter(DATA_FILE)) {
			for (Student currStudent : students) {
				if (currStudent != null) {
					writer.write(currStudent.getName() + "," + currStudent.getGrade() + System.lineSeparator());
				}
			}
		}
	}

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
	public static Student[] loadStudentData() throws FileNotFoundException, IOException {
		List<Student> students = new ArrayList<Student>();
		File inputFile = new File(DATA_FILE);
		try (Scanner reader = new Scanner(inputFile)) {
			for (int lineNumber = 1; reader.hasNextLine(); lineNumber++) {
				String baseLine = reader.nextLine();
				String strippedLine = baseLine.strip();
				String[] parts = strippedLine.split(",");
				try {
					String name = parts[0];
					int grade = Integer.parseInt(parts[1]);
					Student nextStudent = new Student(name, grade);
					students.add(nextStudent);
				} catch (NumberFormatException numError) {
					throw new IOException(
							"Unable to read grade (was not a valid int) on line " + lineNumber + " : " + strippedLine);
				} catch (IllegalArgumentException studentDataError) {
					throw new IOException(
							"Unable to create student, bad name/grade on line " + lineNumber + " : " + strippedLine);
				} catch (IndexOutOfBoundsException studentDataError) {
					throw new IOException(
							"Missing either name and/or grade on line " + lineNumber + " : " + strippedLine);
				}
			}
		}

		return students.toArray(new Student[students.size()]);
	}

}
