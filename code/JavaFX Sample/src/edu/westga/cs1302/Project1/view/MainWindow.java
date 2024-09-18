package edu.westga.cs1302.Project1.view;

import java.net.URL;
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
    private ComboBox<?> creationComboSelect;

    @FXML 
    private TextField creationNameField; 

    @FXML 
    private ListView<?> pantryList; 

    @FXML 
    void initialize() {
        assert this.creationComboSelect != null : "fx:id=\"creationComboSelect\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert this.creationNameField != null : "fx:id=\"creationNameField\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert this.pantryList != null : "fx:id=\"pantryList\" was not injected: check your FXML file 'MainWindow.fxml'.";

    }

}