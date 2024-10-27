package edu.westga.cs1302.project2.view;

import edu.westga.cs1302.project2.model.Ingredient;
import edu.westga.cs1302.project2.model.IngredientNameAscendingComparator;
import edu.westga.cs1302.project2.model.IngredientNameDescendingComparator;
import edu.westga.cs1302.project2.model.IngredientTypeAscendingComparator;
import edu.westga.cs1302.project2.model.IngredientTypeDescendingComparator;
import edu.westga.cs1302.project2.model.IngredientComparator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

/**
 * Codebehind for the Main Window of the application.
 * 
 * @author CS 1302
 * @version Fall 2024
 */
public class MainWindow {
	@FXML private ComboBox<String> ingredientType;
	@FXML private ComboBox<IngredientComparator> sortingCombo;
	@FXML private ListView<Ingredient> ingredientsList;
	@FXML private TextField ingredientName;

	@FXML
	void addIngredient(ActionEvent event) {
		try {
			this.ingredientsList.getItems()
					.add(new Ingredient(this.ingredientName.getText(), this.ingredientType.getValue()));
			this.ingredientName.clear();
			this.ingredientType.getSelectionModel().clearSelection();
			if (this.sortingCombo.getSelectionModel().getSelectedItem() != null) {
				this.sort(event);
			}
		} catch (IllegalArgumentException error) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText("Unable to add ingredient");
			alert.setContentText(error.getMessage());
			alert.showAndWait();
		}
	}

	@FXML
	void removeIngredient(ActionEvent event) {
		Ingredient selectedIngredient = this.ingredientsList.getSelectionModel().getSelectedItem();
		if (selectedIngredient != null) {
			this.ingredientsList.getItems().remove(selectedIngredient);
			if (this.sortingCombo.getSelectionModel().getSelectedItem() != null) {
				this.sort(event);
			}
		}
	}

	@FXML
	void sort(ActionEvent event) {
		this.ingredientsList.getItems().sort(this.sortingCombo.getSelectionModel().getSelectedItem());
	}

	@FXML
	void initialize() {
		this.ingredientType.getItems().add("Vegetable");
		this.ingredientType.getItems().add("Meat");
		this.ingredientType.getItems().add("Bread");
		this.ingredientType.getItems().add("Fruit");
		this.ingredientType.getItems().add("Spice");

		this.sortingCombo.getItems().add(new IngredientNameDescendingComparator());
		this.sortingCombo.getItems().add(new IngredientNameAscendingComparator());
		this.sortingCombo.getItems().add(new IngredientTypeDescendingComparator());
		this.sortingCombo.getItems().add(new IngredientTypeAscendingComparator());

		this.sortingCombo.getSelectionModel().selectedItemProperty().addListener((options, oldvalue, newvalue) -> {
			if (newvalue != null) {
				this.sort(null);
			}
		});
	}
}
