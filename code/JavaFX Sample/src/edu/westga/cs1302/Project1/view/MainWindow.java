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
 * @version 2
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
    private Label errLabelSelected;
    
    @FXML
    private Label selectedName;

    @FXML
    private TextField selectedQuantity;
    
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
    		this.creationNameField.setText("");
    	} catch (IllegalArgumentException err) {
    		this.errLabelCreation.setText(err.getMessage());
    	}
    }
    
    /**zeros out error labels
     * intended to be run at the start of any action, to clear previous and potentially unhelpful error messages
     * 
     */
    private void cancelErrMsgs() {
    	this.errLabelCreation.setText("");
    	this.errLabelListview.setText("");
    	this.errLabelSelected.setText("");
    }
    
    /**
     * outputs food selected in listview, if no item is selected, creates error message
     */
    public void outputCurrentFood() {
    	this.cancelErrMsgs();
    	if (this.pantryList.getSelectionModel().getSelectedItem() != null) {
    		this.selectedName.setText(this.pantryList.getSelectionModel().getSelectedItem().getName());
    		this.selectedQuantity.setText("" + this.pantryList.getSelectionModel().getSelectedItem().getQuantity());
    	} else {
    		this.errLabelListview.setText("select item within listView");
    	}
    }

	/**set quantity of selected item to number in selected quantity text field gives
	 * error message when not provided an integer gives error message when provided
	 * integer below zero
	 */
	public void setSelectedQuantity() {
		this.cancelErrMsgs();
		if (this.pantryList.getSelectionModel().getSelectedItem() != null) {
			// parse double from selected quantity box
			try {
				int newQuantity = Integer.parseInt(this.selectedQuantity.getText());
				try {
					this.pantryList.getSelectionModel().getSelectedItem().setQuantity(newQuantity);
					this.pantryList.refresh();
					this.outputCurrentFood();
				} catch (IllegalArgumentException err) {
					this.errLabelSelected.setText(err.getMessage());
				}
			} catch (NumberFormatException errorObj) {
				this.errLabelSelected.setText(errorObj.getMessage());
			}

		} else {
			this.errLabelSelected.setText("No Item Selected");
		}
	}

    /**increase quantity of selected item by one, refresh selection and list display
     *gives error if no item selected
     */
    public void increaseQuantitySelected() {
    	this.cancelErrMsgs();
    	if (this.pantryList.getSelectionModel().getSelectedItem() != null) {
    		this.pantryList.getSelectionModel().getSelectedItem().setQuantity(
    				this.pantryList.getSelectionModel().getSelectedItem().getQuantity() + 1);
    		this.pantryList.refresh();
    		this.outputCurrentFood();
    	} else {
    		this.errLabelSelected.setText("No Item Selected");
    	}
    }
    
    /**decrease quantity of selected item by one, refresh selection and list display
     *gives error if no item selected 
     *gives error if attempting to decrease below zero
     */
    public void decreaseQuantitySelected() {
    	this.cancelErrMsgs();
    	if (this.pantryList.getSelectionModel().getSelectedItem() != null) {
    		try {
				this.pantryList.getSelectionModel().getSelectedItem()
						.setQuantity(this.pantryList.getSelectionModel().getSelectedItem().getQuantity() - 1);
				this.pantryList.refresh();
				this.outputCurrentFood();
    		} catch (IllegalArgumentException err) {
    			this.errLabelSelected.setText(err.getMessage());
    		}
    	} else {
    		this.errLabelSelected.setText("No Item Selected");
    	}
    }
    
    /**delete selected food entry, clear display fields, deselect list to prevent accidental deletions
     * 
     */
    public void removeFood() {
    	this.cancelErrMsgs();
    	if (this.pantryList.getSelectionModel().getSelectedItem() != null) {
    		this.pantryList.getItems().remove(
    				this.pantryList.getSelectionModel().getSelectedItem());
    		this.pantryList.refresh();
    		this.selectedName.setText("");
    		this.selectedQuantity.setText("");
    		this.pantryList.getSelectionModel().clearSelection();
    	} else {
    		this.errLabelSelected.setText("No Item Selected");
    	}
    }
    
    @FXML 
    void initialize() {
        assert this.creationComboSelect != null : "fx:id=\"creationComboSelect\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert this.creationNameField != null : "fx:id=\"creationNameField\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert this.pantryList != null : "fx:id=\"pantryList\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert this.errLabelCreation != null : "fx:id=\"errLabelCreation\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert this.errLabelListview != null : "fx:id=\"errLabelListview\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert this.errLabelSelected != null : "fx:id=\"errLabelSelected\" was not injected: check your FXML file 'MainWindow.fxml'.";
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
        
        this.pantryList.setOnMouseClicked(event -> {
			if (this.pantryList.getSelectionModel().getSelectedItem() != null) {
				this.outputCurrentFood();
			}
		});
        
    }

}