package edu.westga.cs1302.bill.view;

import java.io.FileNotFoundException;
import java.io.IOException;
import edu.westga.cs1302.bill.model.Bill;
import edu.westga.cs1302.bill.model.BillItem;
import edu.westga.cs1302.bill.model.BillPersistenceManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/** The codebehind for the MainWindow of the application
 * 
 * @author CS 1302
 * @version Fall 2024
 */
public class MainWindow {
	public static final String[] SERVERS = {"Bob", "Alice", "Trudy"};
	private Bill bill;
	
    @FXML private TextField name;
    @FXML private TextField amount;
    @FXML private TextArea receiptArea;
    @FXML private ComboBox<String> serverName;

    @FXML
    void addItem(ActionEvent event) {
    	try {
	    	BillItem item = new BillItem(this.name.getText(), Double.parseDouble(this.amount.getText()));
	    	this.bill.addItem(item);
	    	this.name.clear();
	    	this.amount.clear();
	    	this.updateReceipt();
    	} catch (NumberFormatException numError) {
    		Alert alert = new Alert(Alert.AlertType.ERROR);
    		alert.setHeaderText("Unable to add new bill item");
    		alert.setContentText("Invalid amount provided, please correct and try again.");
    		alert.showAndWait();
    	} catch (IllegalArgumentException argError) {
    		Alert alert = new Alert(Alert.AlertType.ERROR);
    		alert.setHeaderText("Unable to add new bill item");
    		alert.setContentText(argError.getMessage());
    		alert.showAndWait();
    	}
    }

	private void updateReceipt() {
		this.receiptArea.setText(BillTextifier.getText(this.bill));
	}
    
    @FXML
    void selectServer(ActionEvent event) {
    	String name = this.serverName.getValue();
    	if (name != null) {
    		this.bill.setServerName(name);
	    	this.updateReceipt();
    	}
    }

	@FXML
	void saveBillData(ActionEvent event) {
		try {
			BillPersistenceManager.saveBillData(this.bill);
		} catch (IOException err) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setContentText("Unable to save data to file");
			alert.showAndWait();
		}
	}
	
	@FXML
	void loadBillData(ActionEvent event) {
		try {
			Bill savedBill = BillPersistenceManager.loadBillData();
			this.bill = savedBill;
			//pull server information, check for server selection options
			if (this.bill.getServerName().strip().equalsIgnoreCase(SERVERS[0])) {
				this.serverName.getSelectionModel().select(0);
			} else if (this.bill.getServerName().strip().equalsIgnoreCase(SERVERS[1])) {
				this.serverName.getSelectionModel().select(1);
			} else if (this.bill.getServerName().strip().equalsIgnoreCase(SERVERS[2])) {
				this.serverName.getSelectionModel().select(2);
			} else if (this.bill.getServerName().strip().equalsIgnoreCase(Bill.NO_SERVER_DSG)) {
				this.serverName.getSelectionModel().clearSelection();
			} else {
				throw new IllegalArgumentException("Invalid server name");
			}
			this.updateReceipt();
			
		} catch (FileNotFoundException nfErr) {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setContentText("No save data found" + System.lineSeparator() + "Using default configuration");
			alert.showAndWait();
		} catch (IOException ioErr) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setContentText("Invalid save data" + System.lineSeparator() + ioErr.getMessage());
			alert.showAndWait();
		} catch (IllegalArgumentException illErr) {
			Alert alert = new Alert(Alert.AlertType.WARNING);
			alert.setContentText(illErr.getMessage() + "saved server name not in selection.");
			alert.showAndWait();
		}
	}

    @FXML
	void initialize() {
		this.serverName.getItems().addAll(MainWindow.SERVERS);
		this.bill = new Bill();
		this.updateReceipt();
		
		this.loadBillData(null);
	}
}

