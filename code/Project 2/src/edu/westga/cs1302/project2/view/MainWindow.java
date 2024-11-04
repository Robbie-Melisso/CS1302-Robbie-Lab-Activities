package edu.westga.cs1302.project2.view;

import java.io.File;
import java.io.IOException;

import edu.westga.cs1302.project2.model.Ingredient;
import edu.westga.cs1302.project2.model.Recipe;
import edu.westga.cs1302.project2.model.RecipeBookReader;
import edu.westga.cs1302.project2.model.comparator.IngredientComparator;
import edu.westga.cs1302.project2.model.comparator.IngredientNameAscendingComparator;
import edu.westga.cs1302.project2.model.comparator.IngredientNameDescendingComparator;
import edu.westga.cs1302.project2.model.comparator.IngredientTypeAscendingComparator;
import edu.westga.cs1302.project2.model.comparator.IngredientTypeDescendingComparator;
import edu.westga.cs1302.project2.model.RecipeBookWriter;
import edu.westga.cs1302.project2.model.RecipeUtilities;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
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
	@FXML private ListView<Ingredient> recipeCreateIngredients;
	@FXML private TextField recipeCreateName;
	@FXML private TextArea recipeDisplayArea;
	
	private final File recipeBook = new File("data.txt");
	
	@FXML
	void createIngredient(ActionEvent event) {
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
	void deleteIngredient(ActionEvent event) {
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
	void addIngredient(ActionEvent event) {
		if (!(this.ingredientsList.getSelectionModel().getSelectedItem() == null)) {
			this.recipeCreateIngredients.getItems().add(this.ingredientsList.getSelectionModel().getSelectedItem());
			this.ingredientsList.getSelectionModel().clearSelection();
		}
	}
	
	@FXML
	void removeIngredient(ActionEvent event) {
		this.recipeCreateIngredients.getItems().remove(this.recipeCreateIngredients.getSelectionModel().getSelectedItem());
	}
	
	@FXML
	void createRecipe(ActionEvent event) {
		try {
			RecipeBookWriter.writeToBook(new Recipe(this.recipeCreateName.getText(), this.recipeCreateIngredients.getItems()),
					this.recipeBook);
			this.recipeCreateName.setText("");
			this.recipeCreateIngredients.getItems().removeAll(this.recipeCreateIngredients.getItems());
		} catch (IOException errObj) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setContentText(errObj.getMessage());
			alert.showAndWait();
		} catch (IllegalStateException errObj) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText("Duplicate recipe name found");
			alert.setContentText(errObj.getMessage());
			alert.showAndWait();
		} catch (IllegalArgumentException errObj) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setContentText(errObj.getMessage());
			alert.showAndWait();
		}
		
	}
	
	@FXML
	void displayRelevantRecipes(ActionEvent event) {

		if ((this.ingredientsList.getSelectionModel().getSelectedItem() == null)) {
			Alert alert = new Alert(Alert.AlertType.WARNING);
			alert.setHeaderText("No ingredient selected");
			alert.setContentText("Cannot display recipes with select ingredient without select ingredient");
			alert.showAndWait();
		}   else {
			try {
				this.recipeDisplayArea.setText(RecipeUtilities.recipeTextifier(
						RecipeBookReader.getRelevantRecipes(this.ingredientsList.getSelectionModel().getSelectedItem(), this.recipeBook)));
				this.ingredientsList.getSelectionModel().clearSelection();
			} catch (IllegalStateException | IllegalArgumentException | IOException errObj) {
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setHeaderText("Invalid save file");
				alert.setContentText(errObj.getMessage());
				alert.showAndWait();
			}
		}
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
		this.sortingCombo.getSelectionModel().selectFirst();
		
	}
}
