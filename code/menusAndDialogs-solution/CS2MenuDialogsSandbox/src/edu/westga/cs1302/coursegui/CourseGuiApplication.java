package edu.westga.cs1302.coursegui;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;


import javafx.fxml.FXMLLoader;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;

/**
 * CourseGuiApplication extends the JavaFX Application class to build the GUI and
 * start program execution.
 * 
 * @author CS 1302
 * @version 1.0
 */
public class CourseGuiApplication extends Application {

	private static final String WINDOW_TITLE = "Course Management Menus&Dialogs Sandbox";
	private static final String GUI_FXML = "view/CourseGui.fxml";

	/**
	 * Constructs a new Application object for the Student Management demo program.
	 * 
	 * @precondition none
	 * @postcondition the object is ready to execute
	 */
	public CourseGuiApplication() {
		super();
	}

	@Override
	public void start(Stage primaryStage) {
		try {
			Pane pane = this.loadGui();
			Scene scene = new Scene(pane);
			primaryStage.setScene(scene);
			primaryStage.setTitle(WINDOW_TITLE);
			primaryStage.show();
		} catch (IllegalStateException | IOException anException) {
			anException.printStackTrace();
		}
	}

	private Pane loadGui() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource(GUI_FXML));
		return (Pane) loader.load();
	}

	/**
	 * Launches the application.
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @param args
	 *            not used
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
