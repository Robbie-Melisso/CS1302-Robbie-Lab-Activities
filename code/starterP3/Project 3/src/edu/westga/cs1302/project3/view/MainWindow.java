package edu.westga.cs1302.project3.view;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import edu.westga.cs1302.project3.model.Task;
import edu.westga.cs1302.project3.viewmodel.ViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

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
		this.closeMenuItem.setOnAction((event) -> {
			this.baseTaskWin.getScene().getWindow().hide(); });
		
		//FIXME find correct implementation for closing window through MenuItem event
		//why do these not work
		//((Node) (event.getSource())).getScene().getWindow().hide();
		//this.closeMenuItem.setOnAction((event) -> {((Node) (event.getSource())).getScene().getWindow().hide();} );
		//this.closeMenuItem.setOnAction(((Node) (event.getSource())).getScene().getWindow().hide()); C:\Users\rmeliss1\Desktop\labs\CS1302-Robbie-Lab-Activities\code\starterP3\Project 3
		
		this.saveMenuItem.setOnAction((event) -> {
			FileChooser fileChooser = new FileChooser();
			fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));
			//Path filePath = Paths.get("user.dir", "Saves.dir");
			//fileChooser.setInitialDirectory(filePath.toFile());
			//fileChooser.setInitialDirectory(new File("Saves.dir"));
			//fileChooser.setInitialDirectory(System.getProperty("user.home"), ".Project 3/saves");
			fileChooser.setTitle("Save to file");
			File file = fileChooser.showSaveDialog(null);
			if (file != null) {
				try {
					this.vm.save(file);
				} catch (IOException err) {
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setHeaderText("File Unreadable");
					alert.setContentText("File unreadable or locked");
				}
			}
			
		});
		
		this.loadMenuItem.setOnAction((event) -> {
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Load Tasks");
			fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));
			File file = fileChooser.showOpenDialog(null);
			if (file != null) {
				try {
					this.vm.load(file);
				} catch (IllegalArgumentException | IllegalStateException | IOException err) {
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setContentText(err.getMessage());
					alert.showAndWait();
				}
			}
		});
		
		this.aboutMenuItem.setOnAction((event) -> {
			Alert about = new Alert(Alert.AlertType.INFORMATION);
			about.setHeaderText("About");
			about.setContentText(ABOUTMESSAGE);
			about.showAndWait();
		});
	}
}
