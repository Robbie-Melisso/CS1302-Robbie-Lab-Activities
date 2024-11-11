package edu.westga.cs1302.investment_projector.view_model;

import java.util.ArrayList;

import edu.westga.cs1302.investment_projector.model.CompoundInterest;
import edu.westga.cs1302.investment_projector.model.Investment;
import edu.westga.cs1302.investment_projector.model.InvestmentProjector;
import edu.westga.cs1302.investment_projector.model.SimpleInterest;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;

/** Maps View to Model for InvestmentProjector system
 * 
 * @author CS 1302
 * @version Fall 2024
 */
public class InvestmentProjectorViewModel {

	private IntegerProperty capital;
	private DoubleProperty interestRate;
	private IntegerProperty years;
	private ListProperty<InvestmentProjector> interestTypes;
	private ListProperty<Investment> pastInvestments;
	private ObjectProperty<InvestmentProjector> selectedInterestType;
	private ObjectProperty<Investment> selectedInvestment;
	
	
	private DoubleProperty investmentProjection;
	
	/** Create a new vm
	 * 
	 * @precondition none
	 * @postcondition capital is 1, interest rate is 10%, years is 1, simple and compound interest types available, and simple is default
	 * 
	 */
	public InvestmentProjectorViewModel () {
		this.capital = new SimpleIntegerProperty(1);
		this.interestRate = new SimpleDoubleProperty(0.1);
		this.years = new SimpleIntegerProperty(1);
		this.investmentProjection = new SimpleDoubleProperty();
		
		ArrayList<InvestmentProjector> interestTypes = new ArrayList<InvestmentProjector>();
		interestTypes.add(new SimpleInterest());
		interestTypes.add(new CompoundInterest());
		this.interestTypes = new SimpleListProperty<InvestmentProjector>(
				FXCollections.observableArrayList(interestTypes));
		
		this.pastInvestments = new SimpleListProperty<Investment>(
				FXCollections.observableArrayList(
						new ArrayList<Investment>()
				)
			);
		this.selectedInterestType = new SimpleObjectProperty<InvestmentProjector>();
		this.selectedInterestType.setValue(this.interestTypes.get(0));
		this.selectedInvestment = new SimpleObjectProperty<Investment>(null);
		this.selectedInvestment.addListener((observable, oldValue, newValue)->{this.loadSelectedInvestment();});
	}
	
	public ObjectProperty<Investment> getSelectedInvestment() {
		return this.selectedInvestment;
	}
	
	public ListProperty getPastInvestments() {
		return this.pastInvestments;
	}
	
	/** Verify that capital value provided is valid
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @param capitalValue the value to check
	 * @return true if value is valid
	 */
	public boolean verifyCapital(int capitalValue) {
		return Investment.isValidCapital(capitalValue);
	}

	/** Verify that interest rate value provided is valid
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @param interestRateValue the value to check
	 * @return true if value is valid
	 */
	public boolean verifyInterestRate(double interestRateValue) {
		return Investment.isValidInterestRate(interestRateValue);
	}
	
	/** Verify that years value provided is valid
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @param yearsValue the value to check
	 * @return true if value is valid
	 */
	public boolean verifyYears(int yearsValue) {
		return Investment.isValidYears(yearsValue);
	}
	
	/** Returns the capital property
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the capital property
	 */
	public IntegerProperty getCapital() {
		return this.capital;
	}
	
	/** Returns the interest rate property
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the capital property
	 */
	public DoubleProperty getInterestRate() {
		return this.interestRate;
	}

	/** Returns the years property
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the years property
	 */
	public IntegerProperty getYears() {
		return this.years;
	}
	
	/** Returns the interest types property
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the interest types property
	 */
	public ListProperty getInterestTypes() {
		return this.interestTypes;
	}
	
	/** Returns the selected interest property
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the selected interest property
	 */
	public ObjectProperty getSelectedInterestType() {
		return this.selectedInterestType;
	}
	
	/** Returns the investment projection property
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the investment projection property
	 */
	public DoubleProperty getInvestmentProjection() {
		return this.investmentProjection;
	}
	
	/** projects investment given the current inputs
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @throws IllegalArgumentException if capital, interest rate, or years is invalid value
	 */
	public void projectInvestment() throws IllegalArgumentException {
		Investment investment = this.generateProjection();
		
		this.pastInvestments.getValue().add(investment);
	}

	private Investment generateProjection() {
		Investment investment;
		int capital = this.capital.getValue();
		double interestRate = this.interestRate.getValue();
		int years = this.years.getValue();
		investment = new Investment(capital, interestRate, years);
		double investmentProjection = this.selectedInterestType.getValue().projectInvestment(investment);
		this.investmentProjection.setValue(investmentProjection);
		return investment;
	}
	
	public void loadSelectedInvestment() {
		Investment investment = this.selectedInvestment.get();
		if (investment != null) {
			this.capital.setValue(investment.getCapital());
			this.interestRate.setValue(investment.getInterestRate());
			this.years.setValue(investment.getYears());
			this.generateProjection();
		}
	}
	
}
