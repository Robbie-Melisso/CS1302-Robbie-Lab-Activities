package edu.westga.cs1302.Project1.view;

import java.net.URL;

import edu.westga.cs1302.Project1.PantryTracker.Food;

import javafx.scene.control.Label;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

/** codebehind class for UI
 * @author rmeliss1
 * @version 0
 */
public class MainWindow {

    @FXML 
    private ResourceBundle resources;
    @FXML 
    private URL location;
    
    @FXML 
    private ComboBox<String> creationComboSelect;
    @FXML 
    private TextField creationNameField; 
    @FXML 
    private ListView<Food> pantryList; 
    
    @FXML
    private Label errLabelCreation;
    @FXML
    private Label errLabelListview;
    
    @FXML
    private Label selectedName;

    @FXML
    private Label selectedQuantity;
    
    /**
     * creates new food item and adds it to pantry list
     * 
     */
    public void createFood() {
    	try {
    		this.cancelErrMsgs();
    		String type = this.creationComboSelect.getSelectionModel().getSelectedItem();
    		String name = this.creationNameField.getText();
    		Food food = new Food(name, type);
    		this.pantryList.getItems().add(food);
    	} catch (IllegalArgumentException err) {
    		this.errLabelCreation.setText(err.getMessage());
    	}
    }
    
    /**zeros out error labels
     * intended to be run at the start of any action, to clear previous and potentially unhelpful error messages
     * 
     */
    public void cancelErrMsgs() {
    	this.errLabelCreation.setText("");
    	this.errLabelListview.setText("");
    }
    
    @FXML 
    void initialize() {
        assert this.creationComboSelect != null : "fx:id=\"creationComboSelect\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert this.creationNameField != null : "fx:id=\"creationNameField\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert this.pantryList != null : "fx:id=\"pantryList\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert this.errLabelCreation != null : "fx:id=\"errLabelCreation\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert this.errLabelListview != null : "fx:id=\"errLabelListview\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert this.selectedName != null : "fx:id=\"selectedName\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert this.selectedQuantity != null : "fx:id=\"selectedQuantity\" was not injected: check your FXML file 'MainWindow.fxml'.";

        this.creationComboSelect.getItems().addAll(
        		"Vegetable",
        		"Meat",
        		"Bread",
        		"Fruit",
        		"Dessert",
        		"Ingredient"
        		);
        
    }

}