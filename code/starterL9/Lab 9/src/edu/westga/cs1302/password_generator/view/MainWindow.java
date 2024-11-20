package edu.westga.cs1302.password_generator.view;

import edu.westga.cs1302.password_generator.viewmodel.ViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/** Codebehind for the MainWindow of the Application.
 * 
 * @author CS 1302
 * @version Fall 2024
 */
public class MainWindow {

    @FXML private CheckBox mustIncludeDigits;
    @FXML private CheckBox mustIncludeLowerCaseLetters;
    @FXML private CheckBox mustIncludeUpperCaseLetters;
    @FXML private TextField minimumLength;
    //@FXML private TextArea output;
    @FXML private ListView<String> outputList;
    @FXML private Label errorTextLabel;
    @FXML private Button generatePasswordButton;
    @FXML private MenuItem closeMenuItem;
    @FXML private MenuItem aboutMenuItem;
    @FXML private AnchorPane guiPane;
    
    private ViewModel vm;
    
    @FXML
    void initialize() {
    	this.vm = new ViewModel();
    	this.vm.getRequireDigits().bind(this.mustIncludeDigits.selectedProperty());
    	this.vm.getRequireLowercase().bind(this.mustIncludeLowerCaseLetters.selectedProperty());
    	this.vm.getRequireUppercase().bind(this.mustIncludeUpperCaseLetters.selectedProperty());
    	this.minimumLength.setText(this.vm.getMinimumLength().getValue());
    	this.vm.getMinimumLength().bind(this.minimumLength.textProperty());
    	
    	//this.output.textProperty().bind(this.vm.getPassword());
    	this.outputList.itemsProperty().bind(this.vm.getPasswordList());
    	this.errorTextLabel.textProperty().bind(this.vm.getErrorText());
    	
    	this.generatePasswordButton.setOnAction(
    			(event) -> { 
    				this.vm.generatePassword();
    			} 
    	);
    	this.closeMenuItem.setOnAction((event) -> {
    		((Stage) this.guiPane.getScene().getWindow()).close();
    		//((Node) (event.getSource())).getScene().getWindow().hide();
    	});
    	this.aboutMenuItem.setOnAction((event) -> {
    		Alert alert = new Alert(Alert.AlertType.INFORMATION);
    		alert.setHeaderText("About Password Generator");
    		alert.setContentText("Set minimum length with text field, only accepts positive integer input" + System.lineSeparator() 
    		+ "checkboxes toggle various required settings (leaving unchecked does not exclude them from generated password" + System.lineSeparator()
    		+ "generated passcode may be longer than minimum length provided");
    		alert.initModality(Modality.NONE);
    		alert.show();
    	});
    
    	this.minimumLength.textProperty().addListener((observable, newvalue, oldvalue) -> {
    		try {
    			if (this.vm.verifyMinLength()) {
    				this.errorTextLabel.setVisible(false);
    				this.generatePasswordButton.disableProperty().set(false);
    			} else {
    				this.errorTextLabel.setVisible(true);
    				this.generatePasswordButton.disableProperty().set(true);
    			}
    		} catch (NumberFormatException err) {
    			Alert alert = new Alert(Alert.AlertType.ERROR);
    			alert.setContentText(err.getMessage());
    			alert.showAndWait();
    		} catch (IllegalArgumentException err) {
    			Alert alert = new Alert(Alert.AlertType.ERROR);
    			alert.setContentText(err.getMessage());
    			alert.showAndWait();
    		}
    	});
    	
    }
}
