package edu.westga.cs1302.password_generator.view;

import edu.westga.cs1302.password_generator.viewmodel.ViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

public class PropertyWindow {
    @FXML private CheckBox mustIncludeDigits;
    @FXML private CheckBox mustIncludeLowerCaseLetters;
    @FXML private CheckBox mustIncludeUpperCaseLetters;
    @FXML private TextField minimumLength;
    
    public void bindToVM(ViewModel vm) {
    	this.mustIncludeDigits.setSelected(vm.getRequireDigits().getValue());
    	this.mustIncludeLowerCaseLetters.setSelected(vm.getRequireLowercase().getValue());
    	this.mustIncludeUpperCaseLetters.setSelected(vm.getRequireUppercase().getValue());
    	vm.getRequireDigits().bind(this.mustIncludeDigits.selectedProperty());
    	vm.getRequireLowercase().bind(this.mustIncludeLowerCaseLetters.selectedProperty());
    	vm.getRequireUppercase().bind(this.mustIncludeUpperCaseLetters.selectedProperty());
    	this.minimumLength.setText(vm.getMinimumLength().getValue());
    	vm.getMinimumLength().bind(this.minimumLength.textProperty());
    }
}
