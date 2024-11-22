package edu.westga.cs1302.password_generator.view;

import java.io.IOException;

import edu.westga.cs1302.password_generator.Main;
import edu.westga.cs1302.password_generator.viewmodel.ViewModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

/** Codebehind for the MainWindow of the Application.
 * 
 * @author CS 1302
 * @version Fall 2024
 */
public class MainWindow {
    @FXML private Button propertyButton;
    @FXML private TextArea output;
    @FXML private Label errorTextLabel;
    @FXML private Button generatePasswordButton;
    
    private ViewModel vm;
    
    @FXML
    void initialize() {
    	this.vm = new ViewModel();
    	this.output.textProperty().bind(this.vm.getPassword());
    	this.errorTextLabel.textProperty().bind(this.vm.getErrorText());
    	
    	this.generatePasswordButton.setOnAction(
    			(event) -> { 
    				this.vm.generatePassword();
    			} 
    	);
    	this.propertyButton.setOnAction(
    			(event) -> {
    				FXMLLoader loader = new FXMLLoader();
    				loader.setLocation(Main.class.getResource(Main.PROPERTY_WINDOW));
    				try {
						loader.load();
	    				Parent parent = loader.getRoot();
	    				Scene scene = new Scene(parent);
	    				Stage setPropertyStage = new Stage();
	    				setPropertyStage.setTitle(Main.PROPERTY_WINDOW_TITLE);
	    				setPropertyStage.setScene(scene);
	    				setPropertyStage.initModality(Modality.APPLICATION_MODAL);
	    				
	    				PropertyWindow propertyCodebehind = (PropertyWindow) loader.getController();
	    				propertyCodebehind.bindToVM(this.vm);
	    				
	    				setPropertyStage.showAndWait();
					} catch (IOException error) {
						Alert alert = new Alert(AlertType.ERROR);
						alert.setContentText("Unable to load properties window.");
						alert.showAndWait();
					}
    			}
    	);
    }
}
