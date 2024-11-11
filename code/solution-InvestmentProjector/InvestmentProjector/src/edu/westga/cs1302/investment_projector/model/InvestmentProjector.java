package edu.westga.cs1302.investment_projector.model;

/** Projects the expected value of the investment after the specified number of years.
 * 
 * @author CS 1302
 * @version Fall 2023
 */
public abstract class InvestmentProjector {
	
	/** Projects the expected value of the investment after the specified number of years.
	 * 
	 * @precondition investment != null
	 * @postcondition none
	 * 
	 * @param investment the investment to project
	 * 
	 * @return the expected value of the investment after the specified number of years
	 */
	public abstract double projectInvestment (Investment investment);
	
	/** Returns a string with the type of interest that is used when calculated the investment projection.
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return string with the type of interest that is used when calculated the investment projection
	 */
	public abstract String toString();
}
