package edu.westga.cs1302.project3.view;

import java.io.File;
import java.io.IOException;

import edu.westga.cs1302.project3.Main;
import edu.westga.cs1302.project3.model.Task;
import edu.westga.cs1302.project3.viewmodel.ViewModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
//import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

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
			//((Node) (event.getSource())).getScene().getWindow().hide();
			this.baseTaskWin.getScene().getWindow().hide();
			});
		
		//FIXME find correct implementation for closing window through MenuItem event
		//why do these not work
		//((Node) (event.getSource())).getScene().getWindow().hide();
		//this.closeMenuItem.setOnAction((event) -> {((Node) (event.getSource())).getScene().getWindow().hide();} );
		//this.closeMenuItem.setOnAction(((Node) (event.getSource())).getScene().getWindow().hide()); C:\Users\rmeliss1\Desktop\labs\CS1302-Robbie-Lab-Activities\code\starterP3\Project 3
		
		this.saveMenuItem.setOnAction((event) -> {
			this.performSaveTasksFunction();
			
		});
		this.loadMenuItem.setOnAction((event) -> {
			this.performLoadTasksFunction();
		});
		
		this.deleteTask.setOnAction((event) -> {
			if (this.tasksListView.getSelectionModel().getSelectedItem() != null) {
				this.vm.removeTask(this.tasksListView.getSelectionModel().getSelectedItem());
			}
		});
		
		this.addTasksMenuItem.setOnAction((event) -> {
			this.performAddTasksWindowGeneration();
		});
		this.openBuildWin.setOnAction((event) -> {
			this.performAddTasksWindowGeneration();
		});
		
		this.aboutMenuItem.setOnAction((event) -> {
			MainWindow.performAboutMenu();
		});
	}

	private void performLoadTasksFunction() {
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
	}

	private void performSaveTasksFunction() {
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
	}

	private void performAddTasksWindowGeneration() {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource(Main.ADD_TASK_WINDOW));
		try {
			loader.load();
			Parent parent = loader.getRoot();
			Scene scene = new Scene(parent);
			Stage addTaskStage = new Stage();
			addTaskStage.setTitle(Main.ADD_WINDOW_TITLE);
			addTaskStage.setScene(scene);
			addTaskStage.initModality(Modality.WINDOW_MODAL);
			
			AddTaskWindow propertyCodebehind = (AddTaskWindow) loader.getController();

			//fields unbound
			propertyCodebehind.initialize(this.vm);
			addTaskStage.showAndWait();
		} catch (IOException error) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setContentText("Unable to load properties window.");
			alert.showAndWait();
		}
	}
	
	/**
	 * performs about menuItem action
	 */
	//fxml complained that initialize was too many lines long
	private static void performAboutMenu() {
		Alert about = new Alert(Alert.AlertType.INFORMATION);
		about.setHeaderText("About");
		about.setContentText(ABOUTMESSAGE);
		about.showAndWait();
	}
	
	/**
	 * get current viewModel object
	 * @return this.vm
	 */
	public ViewModel getViewModel() {
		return this.vm;
	}
}
