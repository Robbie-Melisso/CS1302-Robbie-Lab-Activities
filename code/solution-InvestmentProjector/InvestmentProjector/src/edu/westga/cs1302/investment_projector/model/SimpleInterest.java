package edu.westga.cs1302.investment_projector.model;

/** Projects the expected value of the investment using simple interest after the specified number of years.
 * 
 * @author CS 1302
 * @version Fall 2023
 */
public class SimpleInterest extends InvestmentProjector {
	
	@Override
	public double projectInvestment (Investment investment) {
		if (investment == null) {
			throw new IllegalArgumentException("Investment must not be null");
		}
		double balance = investment.getCapital();
		for (int i = 0; i < investment.getYears(); i++) {
			balance += investment.getCapital() * investment.getInterestRate();
		}
		return balance;
	}
	
	@Override
	public String toString() {
		return "Simple Interest";
	}
}
