package edu.westga.cs1302.cms.view;

import edu.westga.cs1302.cms.model.Student;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

/** Code behind for the MainWindow of the application
 * 
 * @author CS 1302
 * @version Fall 2024
 */
public class MainWindow {
	@FXML
	private TextField name;
	@FXML
	private ListView<Student> students;

	@FXML
	void addStudent(ActionEvent event) {
		String studentName = this.name.getText();
		try {
			Student student = new Student(studentName);
			this.students.getItems().add(student);
		} catch (IllegalArgumentException errorObj) {
			Alert errPopup = new Alert(Alert.AlertType.ERROR);
			errPopup.setContentText("Unable to create Student." + System.lineSeparator() 
			+ errorObj.getMessage() + System.lineSeparator() 
					+ "re-enter name and try again");
			errPopup.showAndWait();
		}

	}

    @FXML
    void removeStudent(ActionEvent event) {
    	Student student = this.students.getSelectionModel().getSelectedItem();
    	if (student != null) {
        	this.students.getItems().remove(student);	
    	} else {
    		Alert errorPopup = new Alert(Alert.AlertType.ERROR);
    		errorPopup.setContentText("No student selected." + System.lineSeparator() + "Unable to remove.");
    	}
    }

    @FXML
    void initialize() {
        assert this.name != null : "fx:id=\"name\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert this.students != null : "fx:id=\"students\" was not injected: check your FXML file 'MainWindow.fxml'.";

    }

}
