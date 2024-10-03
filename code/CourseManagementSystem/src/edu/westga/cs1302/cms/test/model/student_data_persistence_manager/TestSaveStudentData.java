package edu.westga.cs1302.cms.test.model.student_data_persistence_manager;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.cms.model.Student;
import edu.westga.cs1302.cms.model.StudentDataPersistenceManager;

class TestSaveStudentData {

	@Test
	void testNullStudents() {
		assertThrows(IllegalArgumentException.class, ()->{StudentDataPersistenceManager.saveStudentData(null);});
	}
	
	@Test
	void testNoStudents() throws IOException {
		StudentDataPersistenceManager.saveStudentData(new Student[0]);
		
		File inputFile = new File(StudentDataPersistenceManager.DATA_FILE);
		try (Scanner reader = new Scanner(inputFile)) {
			assertFalse(reader.hasNextLine(), "checking that file is empty");
		}
	}
	
	@Test
	void testOneStudent() throws IOException {
		Student[] students = new Student[1];
		students[0] = new Student("aaa", 1);
		StudentDataPersistenceManager.saveStudentData(students);
		
		File inputFile = new File(StudentDataPersistenceManager.DATA_FILE);
		try (Scanner reader = new Scanner(inputFile)) {
			assertEquals("aaa,1", reader.nextLine(), "check first line");
			assertFalse(reader.hasNextLine(), "checking that we are at the end of the file");
		}
	}
	
	@Test
	void testListContainsASingleNullStudent() throws IOException {
		Student[] students = new Student[1];
		students[0] = null;
		StudentDataPersistenceManager.saveStudentData(students);
		
		File inputFile = new File(StudentDataPersistenceManager.DATA_FILE);
		try (Scanner reader = new Scanner(inputFile)) {
			assertFalse(reader.hasNextLine(), "checking that we are at the end of the file");
		}
	}
	
	@Test
	void testMultipleStudents() throws IOException {
		Student[] students = new Student[2];
		students[0] = new Student("aaa", 1);
		students[1] = new Student("bbb", 2);
		StudentDataPersistenceManager.saveStudentData(students);
		
		File inputFile = new File(StudentDataPersistenceManager.DATA_FILE);
		try (Scanner reader = new Scanner(inputFile)) {
			assertEquals("aaa,1", reader.nextLine(), "check first line");
			assertEquals("bbb,2", reader.nextLine(), "check second line");
			assertFalse(reader.hasNextLine(), "checking that we are at the end of the file");
		}
	}
	
	@Test
	void testMultipleStudentsOneIsNull() throws IOException {
		Student[] students = new Student[2];
		students[0] = null;
		students[1] = new Student("bbb", 2);
		StudentDataPersistenceManager.saveStudentData(students);
		
		File inputFile = new File(StudentDataPersistenceManager.DATA_FILE);
		try (Scanner reader = new Scanner(inputFile)) {
			assertEquals("bbb,2", reader.nextLine(), "check first line");
			assertFalse(reader.hasNextLine(), "checking that we are at the end of the file");
		}
	}

}
