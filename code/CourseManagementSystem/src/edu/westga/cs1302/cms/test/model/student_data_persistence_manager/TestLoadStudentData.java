package edu.westga.cs1302.cms.test.model.student_data_persistence_manager;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileWriter;
import java.io.IOException;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.cms.model.Student;
import edu.westga.cs1302.cms.model.StudentDataPersistenceManager;

class TestLoadStudentData {

	@Test
	void testEmptyFile() throws IOException {
		try (FileWriter writer = new FileWriter(StudentDataPersistenceManager.DATA_FILE)) {
			writer.write("");
		}
		
		Student[] students = StudentDataPersistenceManager.loadStudentData();
		
		assertEquals(0, students.length, "checking number of students loaded");
	}

	@Test
	void testOneStudentInFile() throws IOException {
		try (FileWriter writer = new FileWriter(StudentDataPersistenceManager.DATA_FILE)) {
			writer.write("aaa,1"+System.lineSeparator());
		}
		
		Student[] students = StudentDataPersistenceManager.loadStudentData();
		
		assertEquals(1, students.length, "checking number of students loaded");
		assertEquals("aaa", students[0].getName(), "checking name of the first student");
		assertEquals(1, students[0].getGrade(), "checking grade of the first student");
	}

	@Test
	void testMultipleStudentsInFile() throws IOException {
		try (FileWriter writer = new FileWriter(StudentDataPersistenceManager.DATA_FILE)) {
			writer.write("aaa,1"+System.lineSeparator());
			writer.write("bbb,2"+System.lineSeparator());
		}
		
		Student[] students = StudentDataPersistenceManager.loadStudentData();
		
		assertEquals(2, students.length, "checking number of students loaded");
		assertEquals("aaa", students[0].getName(), "checking name of the first student");
		assertEquals(1, students[0].getGrade(), "checking grade of the first student");
		assertEquals("bbb", students[1].getName(), "checking name of the second student");
		assertEquals(2, students[1].getGrade(), "checking grade of the second student");
	}

	@Test
	void testOneStudentInFileWithInvalidName() throws IOException {
		try (FileWriter writer = new FileWriter(StudentDataPersistenceManager.DATA_FILE)) {
			writer.write("a,1"+System.lineSeparator());
		}
		
		assertThrows(IOException.class, ()->{StudentDataPersistenceManager.loadStudentData();});
	}

	@Test
	void testOneStudentInFileWithInvalidAmount() throws IOException {
		try (FileWriter writer = new FileWriter(StudentDataPersistenceManager.DATA_FILE)) {
			writer.write("aaa,one"+System.lineSeparator());
		}
		
		assertThrows(IOException.class, ()->{StudentDataPersistenceManager.loadStudentData();});
	}

	@Test
	void testIncompleteStudentInFile() throws IOException {
		try (FileWriter writer = new FileWriter(StudentDataPersistenceManager.DATA_FILE)) {
			writer.write("aaa"+System.lineSeparator());
		}
		
		assertThrows(IOException.class, ()->{StudentDataPersistenceManager.loadStudentData();});
	}

}
