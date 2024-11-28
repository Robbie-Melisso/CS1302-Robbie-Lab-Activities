package edu.westga.cs1302.project3.view;

import edu.westga.cs1302.project3.viewmodel.ViewModel;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/** Codebehind for the Add Task Window of the application.
 * 
 * @author CS 1302
 * @version Fall 2024 Project3 1
 */
public class AddTaskWindow {

    @FXML private TextArea addDesc;
    @FXML private TextField addTitle;
    @FXML private Button cancelButton;
    @FXML private Button submitButton;

    /**
     * create instance of window, binding to viewmodel fields
     * @param vm ViewModel of current application
     */
    public void initialize(ViewModel vm) {
    	this.addTitle.setText("");
    	this.addDesc.setText("");
    	
    	vm.freshWindow(this);
    	this.cancelButton.setOnAction((event) -> {
    		((Node) (event.getSource())).getScene().getWindow().hide();
    	});
    	
    	this.submitButton.setOnAction((event) -> {
    		try {
    			vm.addTask();
    			((Node) (event.getSource())).getScene().getWindow().hide();
    		} catch (IllegalArgumentException | IllegalStateException err) {
    			Alert alert = new Alert(Alert.AlertType.ERROR);
    			alert.setContentText(err.getMessage());
    			alert.showAndWait();
    		}
    	});
    }
    
    /**
     * get the title field
     * @return this.addTitle
     */
    public TextField title() {
    	return this.addTitle/*.textProperty()*/;
    }
    
    /**
     * get the description field
     * @return this.addDesc.textproperty
     */
    public TextArea description() {
    	return this.addDesc/*.textProperty()*/;
    }
    
}
