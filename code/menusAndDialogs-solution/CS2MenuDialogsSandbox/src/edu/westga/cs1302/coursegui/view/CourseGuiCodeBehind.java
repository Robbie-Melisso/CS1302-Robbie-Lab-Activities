package edu.westga.cs1302.coursegui.view;

import java.util.Optional;

import edu.westga.cs1302.coursegui.model.CourseLimitExceededException;
import edu.westga.cs1302.coursegui.model.Student;
import edu.westga.cs1302.coursegui.viewmodel.CourseGuiViewModel;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.converter.NumberStringConverter;

/**
 * The Class CodeBehind.
 * 
 * @author CS1302
 * @version 1.0
 */
public class CourseGuiCodeBehind {

	private CourseGuiViewModel viewmodel;
	private final AlertProperty alertProperty;
	
    @FXML
    private MenuItem openMenuItem;    
    @FXML
    private MenuItem exitMenuItem;
    @FXML
    private MenuItem capLimitMenuItem;
    @FXML
    private MenuItem aboutMenuItem;
    @FXML
    private MenuItem removeStudentMenuItem;

	@FXML
	private AnchorPane guiPane;

	@FXML
	private TextField idTextField;
	
	@FXML
	private Label idErrorLabel;

	@FXML
	private TextField nameTextField;

	@FXML
	private TextField gpaTextField;

	@FXML
	private Label capacityLabel;
	
	@FXML
	private ListView<Student> studentListView;

	@FXML
	private Button searchButton;

	@FXML
	private Button addButton;

	@FXML
	private Button removeButton;

	@FXML
	private Button removeAllButton;

	/**
	 * Instantiates a new code behind.
	 */
	public CourseGuiCodeBehind() {
		this.alertProperty = new AlertProperty();
		this.viewmodel = new CourseGuiViewModel();
	}

	@FXML
	void initialize() {
		this.bindToViewModel();
		this.bindButtonsDisableProperty();
		this.setupListenerForAlerts();
		this.setupEventHandlerForSearchButton();
		this.setupEventHandlerForAddButton();
		this.setupEventHandlerForRemoveButtons();
		this.setupListenerForListView();
		this.setupListenersForValidation();
		
		this.openMenuItem.setOnAction((event)->{System.out.println("open");});
		this.exitMenuItem.setOnAction((event)->{
			Stage stage = (Stage)(this.guiPane).getScene().getWindow();
			stage.close();
		});
		this.capLimitMenuItem.setOnAction((event)->{System.out.println("cap limit");});
		this.aboutMenuItem.setOnAction((event)->{System.out.println("about");});
		this.removeStudentMenuItem.setOnAction((event)->{System.out.println("remove student");});
	}

	private void bindButtonsDisableProperty() {

		this.searchButton.disableProperty().bind(this.idTextField.textProperty().isEmpty());
		this.addButton.disableProperty().bind(this.idTextField.textProperty().isEmpty()
				.or(this.nameTextField.textProperty().isEmpty().or(this.gpaTextField.textProperty().isEmpty())));
		this.removeButton.disableProperty()
				.bind(this.viewmodel.isEmptyCourseProperty().or(this.idTextField.textProperty().isEmpty()));
		this.removeAllButton.disableProperty().bind(this.viewmodel.isEmptyCourseProperty());
	}
	
	private void setupListenerForListView() {
		this.studentListView.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldStudent, newStudent) -> {
					if (newStudent != null) {
						this.idTextField.textProperty().set(newStudent.getId());
						this.nameTextField.textProperty().set(newStudent.getName());
						Double gpa = newStudent.getGpa();
						this.gpaTextField.textProperty().set(gpa.toString());
					} 
				});
	}
	
	private void setupListenersForValidation() {
		this.idTextField.textProperty().addListener((observable, oldValue, newValue) -> {
				if (newValue != null) {
					if (!newValue.matches("\\d{0,3}|(\\d{3}(-\\d{0,2}|-\\d{2}-\\d{0,4}))")) {
						this.idTextField.setText(oldValue);
						if (!oldValue.matches("\\d{3}-\\d{2}-\\d{4}")) {
							this.idErrorLabel.setVisible(true);
						}
					} else {
						this.idErrorLabel.setVisible(false);
						if (newValue.matches("\\d{3}|\\d{3}-\\d{2}") && oldValue.matches("\\d{2}|\\d{3}-\\d")) {
							this.idTextField.setText(this.idTextField.getText() + "-");
						}
					}
				}
		});

		this.gpaTextField.textProperty().addListener((observable, oldValue, newValue) -> {
				if (newValue != null) {
					if (!newValue.isEmpty() && !newValue.matches("[0-3]([.]\\d{0,2})?|4([.]0{0,2})?")) {
						this.gpaTextField.setText(oldValue);
					}
				}
		});
	}

	private void setupEventHandlerForSearchButton() {
		this.searchButton.setOnAction(event -> {
			try {
				if (!this.viewmodel.searchStudent()) {
					this.alertProperty.set(AlertProperty.ERROR, "Search error", "ERROR: No such a student");
				}
			} catch (IllegalArgumentException | NullPointerException ex) {
				this.alertProperty.set(AlertProperty.ERROR, "Search error",
						"ERROR: couldn't find the student due to " + ex.getMessage());
			}
		});
	}
	
	private void setupEventHandlerForAddButton() {
		this.addButton.setOnAction(event -> {
			try {
				if (!this.viewmodel.addStudent()) {
					this.alertProperty.set(AlertProperty.ERROR, "Add error", "ERROR: student was not added");
				}
			} catch (CourseLimitExceededException | IllegalArgumentException | NullPointerException ex) {
				this.alertProperty.set(AlertProperty.ERROR, "Add error",
						"ERROR: couldn't add the student due to " + ex.getMessage());
			}
		});
	}
	
	private void setupEventHandlerForRemoveButtons() {
		this.removeButton.setOnAction(event -> {
			try {
				if (!this.viewmodel.removeStudent()) {
					this.alertProperty.set(AlertProperty.ERROR, "Remove error", "ERROR: No such a student");
				}
			} catch (IllegalArgumentException | NullPointerException ex) {
				this.alertProperty.set(AlertProperty.ERROR, "Remove error",
						"ERROR: couldn't find the student due to " + ex.getMessage());
			}
		});

		this.removeAllButton.setOnAction(event -> {
			this.viewmodel.removeAllStudents();
		});
	}

	@FXML
	private void clearNameGPA() {
		this.viewmodel.clearNameGPA();
	}

	private void showAlert(Alert.AlertType alertType) {
		Alert alert = new Alert(alertType);
		Window owner = this.guiPane.getScene().getWindow();
		alert.initOwner(owner);
		if (!this.alertProperty.getTitle().isEmpty()) {
			alert.setTitle(this.alertProperty.getTitle());
		}
		alert.setHeaderText(this.alertProperty.getHeader());
		alert.setContentText(this.alertProperty.getContent());
		Optional<ButtonType> alertResult = alert.showAndWait();
		if (alertType == AlertType.CONFIRMATION && alertResult.get() == ButtonType.CANCEL) {
			this.alertProperty.setResult("cancel");
		} else {
			this.alertProperty.setResult("ok");
		}
		this.alertProperty.setType(AlertProperty.NO_ALERT);
	}

	private void bindToViewModel() {
		this.idTextField.textProperty().bindBidirectional(this.viewmodel.idProperty());
		this.nameTextField.textProperty().bindBidirectional(this.viewmodel.nameProperty());
		this.gpaTextField.textProperty().bindBidirectional(this.viewmodel.gpaProperty());
		this.capacityLabel.textProperty().bindBidirectional(this.viewmodel.capacityProperty(), new NumberStringConverter());

		this.studentListView.itemsProperty().bind(this.viewmodel.studentsProperty());
	}
	
	private void setupListenerForAlerts() {
		this.alertProperty.addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				if (newValue.intValue() == AlertProperty.ERROR) {
					CourseGuiCodeBehind.this.showAlert(AlertType.ERROR);
				} else if (newValue.intValue() == AlertProperty.INFORMATION) {
					CourseGuiCodeBehind.this.showAlert(AlertType.INFORMATION);
				} else if (newValue.intValue() == AlertProperty.CONFIRMATION) {
					CourseGuiCodeBehind.this.showAlert(AlertType.CONFIRMATION);
				}
			}
		});
	}
}
