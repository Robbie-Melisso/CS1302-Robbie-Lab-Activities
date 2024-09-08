package edu.westga.cs1302.cms.view;

import edu.westga.cs1302.cms.model.Student;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;

/** Code behind for the MainWindow of the application
 * 
 * @author CS 1302
 * @version Fall 2024
 */
public class MainWindow {
	@FXML
	private TextField name;
	@FXML
	private TextField gradeCreation;
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
		String studentName = this.name.getText();
		String studentGrade = this.gradeCreation.getText();
		//textbox automatically returns an empty field as empty string, NOT NULL
		if (!studentGrade.equals("")) {
			//parse double from creation grade box
			try {
				double grade = Double.parseDouble(studentGrade);
				Student student = new Student(studentName, grade);
				this.students.getItems().add(student);
				this.updateClassGradeAverage();
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
			
		} else if (studentGrade.equals("")) {
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
    	if (student != null) {
        	this.students.getItems().remove(student);
        	this.updateClassGradeAverage();
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
		if (!student.equals(null) && !grade.equals("")) {
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
		} else if (!grade.equals("")) {
			Alert errorPopup = new Alert(Alert.AlertType.ERROR);
    		errorPopup.setContentText("No grade input." + System.lineSeparator() + "Unable to change grade");
    		errorPopup.showAndWait();
		} else {
    		Alert errorPopup = new Alert(Alert.AlertType.ERROR);
    		errorPopup.setContentText("No student selected." + System.lineSeparator() + "Unable to change grade");
    		errorPopup.showAndWait();
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
         assert this.gradeCreation != null : "fx:id=\"gradeCreation\" was not injected: check your FXML file 'MainWindow.fxml'.";
         assert this.name != null : "fx:id=\"name\" was not injected: check your FXML file 'MainWindow.fxml'.";
         assert this.students != null : "fx:id=\"students\" was not injected: check your FXML file 'MainWindow.fxml'.";
         assert this.classGradeAverage != null : "fx:id=\"classGradeAverage\" was not injected: check your FXML file 'MainWindow.fxml'.";

        this.students.setOnMouseClicked(event -> {
        	Student student = this.students.getSelectionModel().getSelectedItem();
        	this.outputCurrentStuTotextFields(student);
        });
    }
}
