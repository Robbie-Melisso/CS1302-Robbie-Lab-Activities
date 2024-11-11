package edu.westga.cs1302.investment_projector.view;

import edu.westga.cs1302.investment_projector.view_model.InvestmentProjectorViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.converter.NumberStringConverter;
import javafx.scene.control.ListView;

/** Codebehind for the MainWindow of the Application.
 * 
 * @author CS 1302
 * @version Fall 2024
 */
public class MainWindow {    
	@FXML private Button projectInvestmentButton;
    @FXML private TextField years;
    @FXML private TextField expectedResult;
    @FXML private TextField interestRate;
    @FXML private TextField capital;
    @FXML private ComboBox interestType;    
    @FXML private Label capitalError;
    @FXML private Label interestRateError;
    @FXML private Label yearsError;
    @FXML private ListView investments;
    
    private InvestmentProjectorViewModel vm;

    @FXML
    void initialize() {
        this.vm = new InvestmentProjectorViewModel();
        
        this.capital.textProperty().bindBidirectional(this.vm.getCapital(), new NumberStringConverter());
        this.interestRate.textProperty().bindBidirectional(this.vm.getInterestRate(), new NumberStringConverter());
        this.years.textProperty().bindBidirectional(this.vm.getYears(), new NumberStringConverter());
        
        this.interestType.setItems(this.vm.getInterestTypes());
        this.investments.setItems(this.vm.getPastInvestments());
        
        this.vm.getSelectedInterestType().bind(this.interestType.getSelectionModel().selectedItemProperty());
        this.interestType.setValue(this.interestType.getItems().get(0));
        
        this.vm.getSelectedInvestment().bind(this.investments.getSelectionModel().selectedItemProperty());
        
        this.expectedResult.textProperty().bindBidirectional(this.vm.getInvestmentProjection(), new NumberStringConverter());
        
        this.projectInvestmentButton.setOnAction(
        			(ActionEvent event)->{
        				try {
        					this.vm.projectInvestment();
        				} catch (IllegalArgumentException e) {
        					Alert alert = new Alert(Alert.AlertType.ERROR);
        					alert.setContentText(e.getMessage());
        					alert.showAndWait();
        				}
        			}
        );
        
        this.capital.textProperty().addListener(
        	(observable, oldValue, newValue) -> {
	        	try {
	        		if (this.vm.verifyCapital(Integer.parseInt(newValue))) {
	        			this.capitalError.setVisible(false);
	        		} else {
	        			this.capitalError.setVisible(true);
	        		}
	        	} catch (NumberFormatException e) {
	        		this.capitalError.setVisible(true);
	        	}
        	}
        );
        
        this.interestRate.textProperty().addListener(
            	(observable, oldValue, newValue) -> {
    	        	try {
    	        		if (this.vm.verifyInterestRate(Double.parseDouble(newValue))) {
    	        			this.interestRateError.setVisible(false);
    	        		} else {
    	        			this.interestRateError.setVisible(true);
    	        		}
    	        	} catch (NumberFormatException e) {
    	        		this.interestRateError.setVisible(true);
    	        	}
            	}
        );
        
        this.years.textProperty().addListener(
            	(observable, oldValue, newValue) -> {
    	        	try {
    	        		if (this.vm.verifyYears(Integer.parseInt(newValue))) {
    	        			this.yearsError.setVisible(false);
    	        		} else {
    	        			this.yearsError.setVisible(true);
    	        		}
    	        	} catch (NumberFormatException e) {
    	        		this.yearsError.setVisible(true);
    	        	}
            	}
       );
    }
}

