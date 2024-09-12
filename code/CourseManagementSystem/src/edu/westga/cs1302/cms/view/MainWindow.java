package edu.westga.cs1302.cms.view;

import java.awt.event.KeyEvent;

import edu.westga.cs1302.cms.model.Student;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.control.Label;

/** Code behind for the MainWindow of the application
 * 
 * @author CS 1302
 * @version Fall 2024
 */
public class MainWindow {
	@FXML
	private TextField creationNAME;
	@FXML
	private TextField creationGRADE;
	@FXML
	private TextField currentGRADE;
	@FXML
	private TextField currentNAME;
	@FXML
	private ListView<Student> students;
	@FXML
    private Label classGradeAverage;

	@FXML
	void addStudent(ActionEvent event) {
		String studentName = this.creationNAME.getText();
		String studentGrade = this.creationGRADE.getText();
		//textbox automatically returns an empty field as empty string, NOT NULL
		if (!studentGrade.equals("") && !studentGrade.equals(null)) {
			//parse double from creation grade box
			try {
				double grade = Double.parseDouble(studentGrade);
				Student student = new Student(studentName, grade);
				this.students.getItems().add(student);
				this.updateClassGradeAverage();
				
				this.creationNAME.setText("");
				this.creationGRADE.setText("");
			} catch (NumberFormatException errorObj) {
				Alert errorPopup = new Alert(Alert.AlertType.ERROR);
				errorPopup.setContentText("New grade is not in form of a double" + System.lineSeparator()
						+ errorObj.getMessage() + System.lineSeparator() + "re-enter grade and try again");
				errorPopup.showAndWait();
			} catch (IllegalArgumentException errorObj) {
				Alert errorPopup = new Alert(Alert.AlertType.ERROR);
				errorPopup.setContentText("Unable to create Student." + System.lineSeparator() 
				+ errorObj.getMessage() + System.lineSeparator() 
						+ "re-enter grade and try again");
				errorPopup.showAndWait();
			}
			
		} else if (studentGrade.equals("") || studentGrade.equals(null)) {
			//student grade is null
			try {
				Student student = new Student(studentName);
				this.students.getItems().add(student);
				this.updateClassGradeAverage();
			} catch (IllegalArgumentException errorObj) {
				Alert errorPopup = new Alert(Alert.AlertType.ERROR);
				errorPopup.setContentText("Unable to create Student." + System.lineSeparator() 
											+ errorObj.getMessage() + System.lineSeparator()
											+ "re-enter name and try again");
				errorPopup.showAndWait();
			}
		}	

	}

    @FXML
    void removeStudent(ActionEvent event) {
    	Student student = this.students.getSelectionModel().getSelectedItem();
    	if (!student.equals(null)) {
        	this.students.getItems().remove(student);
        	this.updateClassGradeAverage();
        	
        	this.currentNAME.setText("");
        	this.currentGRADE.setText("");
    	} else {
    		Alert errorPopup = new Alert(Alert.AlertType.ERROR);
    		errorPopup.setContentText("No student selected." + System.lineSeparator() + "Unable to remove.");
    		errorPopup.showAndWait();
    	}
    }
    
    @FXML
	void setGrade(ActionEvent event) {
		Student student = this.students.getSelectionModel().getSelectedItem();
		String grade = this.currentGRADE.getText();
		if (student != null && !grade.equals("") && grade != null) {
			// parse double from gradeCURRENT box
			try {
				double newgrade = Double.parseDouble(grade);
				student.setgrade(newgrade);
				this.outputCurrentStuTotextFields(student);
				this.updateClassGradeAverage();
			} catch (NumberFormatException errorObj) {
				Alert errorPopup = new Alert(Alert.AlertType.ERROR);
				errorPopup.setContentText("New grade is not in form of a double" + System.lineSeparator()
						+ errorObj.getMessage() + System.lineSeparator() + "re-enter grade and try again");
				errorPopup.showAndWait();
			} catch (IllegalArgumentException errorObj) {
				Alert errorPopup = new Alert(Alert.AlertType.ERROR);
				errorPopup.setContentText("entered grade is invalid" + System.lineSeparator()
						+ errorObj.getMessage() + System.lineSeparator() + "re-enter grade and try again");
				errorPopup.showAndWait();
			}
		} else if (grade.equals("") || grade.equals(null)) {
			Alert errorPopup = new Alert(Alert.AlertType.ERROR);
    		errorPopup.setContentText("No grade input." + System.lineSeparator() + "Unable to change grade");
    		errorPopup.showAndWait();
		} else {
    		Alert errorPopup = new Alert(Alert.AlertType.ERROR);
    		errorPopup.setContentText("No student selected." + System.lineSeparator() + "Unable to change grade");
    		errorPopup.showAndWait();
    	}
    		
	}

    @FXML
    void setName(ActionEvent event) {
    	Student student = this.students.getSelectionModel().getSelectedItem();
		String name = this.currentNAME.getText();
		if (this.students.getSelectionModel().getSelectedItem() != null) {
				try {
					student.setName(name);
					this.outputCurrentStuTotextFields(student);
				} catch (IllegalArgumentException errorObj) {
					Alert errorPopup = new Alert(Alert.AlertType.WARNING);
					errorPopup.setContentText("Unable to change name" + System.lineSeparator() + errorObj.getMessage());
					errorPopup.showAndWait();
				}
		} else {
			Alert errorPopup = new Alert(Alert.AlertType.WARNING);
			errorPopup.setContentText("No student selected." + System.lineSeparator() + "Unable to change grade");
			errorPopup.showAndWait();
		}
    }
    
    @FXML
    void selectStudent() {
    	if (this.students.getSelectionModel().getSelectedItem() != null) {
    		this.outputCurrentStuTotextFields(this.students.getSelectionModel().getSelectedItem());
    	} else {
    		Alert uDidWrong = new Alert(Alert.AlertType.WARNING);
    		uDidWrong.setContentText("No Student Selected" + System.lineSeparator() + "Select Student and try again");
    		uDidWrong.showAndWait();
    	}
    }
    
    /**output current student to current textFields 
     * 
     * @param student selected student in listview
     */
    void outputCurrentStuTotextFields(Student student) {
    	this.currentGRADE.setText(Double.toString(student.getGrade()));
    	this.currentNAME.setText(student.getName());
    }
    
    /**calculates class grade average and pushes it to the label classGradeAverage
     * 
     */
    void updateClassGradeAverage() {
    	if (this.students.getItems().size() > 0) {
    		//list is populated
    		double total = 0;
    		for (Student curr : this.students.getItems()) {
    			total += curr.getGrade();
    		}
    		double avg = total / this.students.getItems().size();
    		this.classGradeAverage.setText(Double.toString(avg));
    	} else {
    		this.classGradeAverage.setText("N / A");
    	}
    }
    
    @FXML
    void initialize() {
    	 assert this.currentGRADE != null : "fx:id=\"currentGRADE\" was not injected: check your FXML file 'MainWindow.fxml'.";
         assert this.currentNAME != null : "fx:id=\"currentName\" was not injected: check your FXML file 'MainWindow.fxml'.";
         assert this.creationGRADE != null : "fx:id=\"gradeCreation\" was not injected: check your FXML file 'MainWindow.fxml'.";
         assert this.creationNAME != null : "fx:id=\"name\" was not injected: check your FXML file 'MainWindow.fxml'.";
         assert this.students != null : "fx:id=\"students\" was not injected: check your FXML file 'MainWindow.fxml'.";
         assert this.classGradeAverage != null : "fx:id=\"classGradeAverage\" was not injected: check your FXML file 'MainWindow.fxml'.";

			this.students.setOnMouseClicked(event -> {
				if (this.students.getSelectionModel().getSelectedItem() != null) {
					Student student = this.students.getSelectionModel().getSelectedItem();
					this.outputCurrentStuTotextFields(student);
				}
			});
			this.students.setOnKeyTyped(event -> {
				if (event.getCode() == KeyCode.ENTER) {
					this.outputCurrentStuTotextFields(null);
				}
			});
    
    }
}
