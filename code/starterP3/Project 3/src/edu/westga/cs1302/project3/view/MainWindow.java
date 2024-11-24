package edu.westga.cs1302.project3.view;

import edu.westga.cs1302.project3.model.Task;
import edu.westga.cs1302.project3.viewmodel.ViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;

/** Codebehind for the Main Window of the application.
 * 
 * @author CS 1302
 * @version Fall 2024
 */
public class MainWindow {
    
    public static final String ABOUTMESSAGE = "This application allows you to make and track a list of tasks and their requirements." + System.lineSeparator()
    + "task sets can be loaded and saved to different files" + System.lineSeparator()
    + "the tasks must have unique text in their title positions.";
    
    @FXML private AnchorPane baseTaskWin;
    @FXML private MenuItem saveMenuItem;
    @FXML private MenuItem loadMenuItem;
	@FXML private MenuItem aboutMenuItem;
    @FXML private MenuItem addTasksMenuItem;
    @FXML private MenuItem addPeopleMenuItem;
    @FXML private MenuItem closeMenuItem;
    @FXML private Button openBuildWin;
    @FXML private Button deleteTask;
    @FXML private ListView<Task> tasksListView;

    private ViewModel vm;
    
	@FXML
	void initialize() {
		this.vm = new ViewModel();
		this.tasksListView.itemsProperty().bind(this.vm.taskList());
		
		/*
		 * TODO add save / load functions with file chooser window
		 * 
		 */
		this.closeMenuItem.setOnAction((event) -> {
			this.baseTaskWin.getScene().getWindow().hide(); });
		
		//FIXME find correct implementation for closing window through menuItem event
		//why do these not work
		//((Node) (event.getSource())).getScene().getWindow().hide();
		//this.closeMenuItem.setOnAction((event) -> {((Node) (event.getSource())).getScene().getWindow().hide();} );
		//this.closeMenuItem.setOnAction(((Node) (event.getSource())).getScene().getWindow().hide());
		
		this.aboutMenuItem.setOnAction((event) -> {
			Alert about = new Alert(Alert.AlertType.INFORMATION);
			about.setHeaderText("About");
			about.setContentText(ABOUTMESSAGE);
			about.showAndWait();
		});
	}
}
