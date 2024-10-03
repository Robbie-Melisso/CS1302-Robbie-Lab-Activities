package edu.westga.cs1302.cms.view;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import edu.westga.cs1302.cms.model.GradeCalculator;
import edu.westga.cs1302.cms.model.Student;
import edu.westga.cs1302.cms.model.StudentDataPersistenceManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

/**
 * Code behind for the MainWindow of the application
 * 
 * @author CS 1302
 * @version Fall 2024
 */
public class MainWindow {
	private static final String NO_STUDENTS_AVAILABLE_MESSAGE = "N/A";
	@FXML
	private TextField name;
	@FXML
	private TextField grade;
	@FXML
	private ListView<Student> students;
	@FXML
	private Label avgGrade;

	@FXML
	void addStudent(ActionEvent event) {
		String studentName = this.name.getText();
		try {
			int grade = Integer.parseInt(this.grade.getText());
			Student student = new Student(studentName, grade);
			this.students.getItems().add(student);
			this.name.clear();
			this.grade.clear();
		} catch (NumberFormatException errorThing) {
			Alert errorPopup = new Alert(Alert.AlertType.ERROR);
			errorPopup.setContentText(
					"Unable to create student: " + errorThing.getMessage() + ". Please reenter grade and try again.");
			errorPopup.showAndWait();
		} catch (IllegalArgumentException errorObject) {
			Alert errorPopup = new Alert(Alert.AlertType.ERROR);
			errorPopup.setContentText(
					"Unable to create student: " + errorObject.getMessage() + ". Please reenter name and try again.");
			errorPopup.showAndWait();
		}
		this.updateAverageGrade();
	}

	@FXML
	void removeStudent(ActionEvent event) {
		Student student = this.students.getSelectionModel().getSelectedItem();
		if (student != null) {
			this.students.getItems().remove(student);
		} else {
			Alert errorPopup = new Alert(Alert.AlertType.ERROR);
			errorPopup.setContentText("No student selected. Unable to remove.");
			errorPopup.showAndWait();
		}
		this.updateAverageGrade();
	}

	private void updateAverageGrade() {
		try {
			double avgGrade = GradeCalculator.calculateAverageGrade(this.students.getItems());
			this.avgGrade.setText(Double.toString(avgGrade));
		} catch (IllegalArgumentException invalidStudentsError) {
			this.avgGrade.setText(NO_STUDENTS_AVAILABLE_MESSAGE);
		}
	}

	@FXML
	void viewGrade(ActionEvent event) {
		Student selectedStudent = this.students.getSelectionModel().getSelectedItem();
		if (selectedStudent != null) {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText("Name: " + selectedStudent.getName());
			alert.setContentText("Grade: " + Integer.toString(selectedStudent.getGrade()));
			alert.showAndWait();
		}
	}

	@FXML
	void saveStudentData(ActionEvent event) {
		try {
			List<Student> students = this.students.getItems();
			StudentDataPersistenceManager.saveStudentData(students.toArray(new Student[students.size()]));
		} catch (IOException writeError) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setContentText("Unable to save data to file!");
			alert.showAndWait();
		}
	}

	@FXML
	void initialize() {
		assert this.name != null : "fx:id=\"name\" was not injected: check your FXML file 'MainWindow.fxml'.";
		assert this.grade != null : "fx:id=\"grade\" was not injected: check your FXML file 'MainWindow.fxml'.";
		assert this.avgGrade != null : "fx:id=\"avgGrade\" was not injected: check your FXML file 'MainWindow.fxml'.";
		assert this.students != null : "fx:id=\"students\" was not injected: check your FXML file 'MainWindow.fxml'.";

		try {
			Student[] students = StudentDataPersistenceManager.loadStudentData();
			this.students.getItems().addAll(students);
			this.updateAverageGrade();
		} catch (FileNotFoundException fileError) {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setContentText("No save data file found, loading with no student data.");
			alert.showAndWait();
		} catch (IOException parseError) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText("File not in valid format.");
			alert.setContentText(parseError.getMessage());
			alert.showAndWait();
		}
		
	}

}
