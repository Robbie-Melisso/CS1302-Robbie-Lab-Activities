package edu.westga.cs1302.coursegui.view;

import java.util.Optional;

import edu.westga.cs1302.coursegui.model.Student;
import edu.westga.cs1302.coursegui.viewmodel.CourseGuiViewModel;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Window;
import javafx.util.converter.NumberStringConverter;

/**
 * The Class CodeBehind.
 * 
 * @author CS 1302
 * @version Fall 2024
 */
public class CourseGuiCodeBehind {

	private CourseGuiViewModel viewmodel;
	private final AlertProperty alertProperty;

	@FXML
	private AnchorPane guiPane;

	@FXML
	private TextField idTextField;
	
	@FXML
	private Label idFormatLabel;

	@FXML
	private TextField nameTextField;

	@FXML
	private TextField gpaTextField;

	@FXML
	private Label gpaFormatLabel;
	
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

	@FXML
    private TextField minGpaTextField;

	@FXML
	private Button filterButton;
	
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
		this.setupListenerForAlerts();
		this.setUpListenerForAddButton();
		this.setupChangeListenersForValidation();
		this.setupFocusListenerForId();
		this.setupChangeListenerListView();
	}
	
	private void setUpListenerForAddButton() {
		this.addButton.setOnAction(event -> {
			try {
				if (!this.viewmodel.addStudent()) {
					this.alertProperty.set(AlertProperty.ERROR, "Add error", "ERROR: student was not added");
				}
			} catch (IllegalArgumentException | NullPointerException ex) {
				this.alertProperty.set(AlertProperty.ERROR, "Add error",
						"ERROR: couldn't add the student due to " + ex.getMessage());
			}	
		});
	}
	private void setupFocusListenerForId() {
		this.idTextField.focusedProperty().addListener((observable, oldValue, newValue) -> {
			if (!newValue) {
				String id = this.idTextField.textProperty().getValue();
				if (!id.matches("\\d{3}-\\d{2}-\\d{4}")) {
					this.idTextField.requestFocus();
					this.idFormatLabel.setVisible(true);
				}
			}

		});

	}
	private void setupChangeListenersForValidation() {
		this.idTextField.textProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue != null) {
				if (!newValue.matches("\\d{0,3}|(\\d{3}(-\\d{0,2}|-\\d{2}-\\d{0,4}))")) {
					this.idTextField.setText(oldValue);
					if (!oldValue.matches("\\d{3}-\\d{2}-\\d{4}")) {
						this.idFormatLabel.setVisible(true);
					}
				} else {
					this.idFormatLabel.setVisible(false);
					if (newValue.matches("\\d{3}|\\d{3}-\\d{2}") && oldValue.matches("\\d{2}|\\d{3}-\\d")) {
						this.idTextField.setText(this.idTextField.getText() + "-");
					}
				}
			}
		});

		this.gpaTextField.textProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue != null) {
				if (!newValue.isEmpty() && !newValue.matches("[0-3](\\.\\d{0,2})?|4(\\.0{0,2})?")) {
					this.gpaTextField.setText(oldValue);
					if (!oldValue.matches("[0-4](\\.\\d{2})")) {
						this.gpaFormatLabel.setVisible(true);
					}
				} else {
					this.gpaFormatLabel.setVisible(false);
				}
			}
		});

	}

	private void setupChangeListenerListView() {
		this.studentListView.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldStudent, newStudent) -> {
					if (newStudent != null) {
						this.idTextField.setText(newStudent.getId());
						this.nameTextField.setText(newStudent.getName());
						Double gpa = newStudent.getGpa();
						this.gpaTextField.setText(gpa.toString());
					}
				});
	}
	
	@FXML
	private void handleSearch() {
		try {
			if (!this.viewmodel.searchStudent()) {
				this.alertProperty.set(AlertProperty.ERROR, "Search error", "ERROR: No such a student");
			}
		} catch (IllegalArgumentException | NullPointerException ex) {
			this.alertProperty.set(AlertProperty.ERROR, "Search error",
					"ERROR: couldn't find the student due to " + ex.getMessage());
		}

	}

	@FXML
	private void handleRemoveStudent() {
		try {
			if (!this.viewmodel.removeStudent()) {
				this.alertProperty.set(AlertProperty.ERROR, "Remove error", "ERROR: No such a student");
			}
		} catch (IllegalArgumentException | NullPointerException ex) {
			this.alertProperty.set(AlertProperty.ERROR, "Remove error",
					"ERROR: couldn't find the student due to " + ex.getMessage());
		}

	}

	@FXML
	private void handleRemoveAllStudents() {
		this.viewmodel.removeAllStudents();
	}

	@FXML
	private void clearNameGPA() {
		this.viewmodel.clearNameGPA();
	}
	
	@FXML
    void handleMinGPAFilter(ActionEvent event) {
		this.viewmodel.filterByMinGPA();
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
		this.gpaTextField.textProperty().bindBidirectional(this.viewmodel.gpaProperty(), new NumberStringConverter());
		this.studentListView.itemsProperty().bind(this.viewmodel.studentListProperty());
		this.minGpaTextField.textProperty().bindBidirectional(this.viewmodel.minGpaProperty(), new NumberStringConverter());
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
