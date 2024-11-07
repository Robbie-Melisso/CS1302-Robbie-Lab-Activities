package edu.westga.cs1302.password_generator.view;

//import java.util.Random;

//import edu.westga.cs1302.password_generator.model.PasswordGenerator;
import edu.westga.cs1302.password_generator.viewmodel.PasswordGeneratorViewModel;
//import javafx.event.ActionEvent;
//import javafx.event.EventHandler;
import javafx.fxml.FXML;
//import javafx.scene.control.Alert;
//import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;

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
    @FXML private TextArea output;
    
    @FXML private Button generateButton;
    
    private PasswordGeneratorViewModel viewModel;

    /**
     * instantiates code behind for GUI model
     */
    public MainWindow() {
    	this.viewModel = new PasswordGeneratorViewModel();
    }
    
    @FXML
    void initialize() {    	
    	this.bindComponentsToViewModel();
    }

    private void bindComponentsToViewModel() {
    	this.minimumLength.textProperty().bindBidirectional(this.viewModel.minLengthProperty());
    	this.output.textProperty().bind(this.viewModel.outputProperty());
    	
    	this.mustIncludeDigits.selectedProperty().bindBidirectional(this.viewModel.mustIncludeDigits());
    	this.mustIncludeLowerCaseLetters.selectedProperty().bindBidirectional(this.viewModel.mustIncludeLowerCaseLetters());
    	this.mustIncludeUpperCaseLetters.selectedProperty().bindBidirectional(this.viewModel.mustIncludeUpperCaseLetters());
    	//this.submitButtonkk.setOnAction((event) -> { this.viewModel.sayGreeting(); } );
    	//    	this.generateButton.setOnAction((event) -> {
    	//    		this.viewModel.generatePassword(); });
    	this.generateButton.setOnAction((event) -> {
    		this.viewModel.generatePassword(); });
    	
    }
}
