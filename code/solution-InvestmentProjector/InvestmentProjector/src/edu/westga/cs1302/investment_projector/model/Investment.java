package edu.westga.cs1302.investment_projector.model;

/** Stores information for an investment.
 * 
 * @author CS 1302
 * @version Fall 2024
 */
public class Investment {
	private int capital;
	private double interestRate;
	private int years;
	
	/** Checks if the provided value is a valid capital value.
	 * All capital values must be positive.
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @param capital the capital value to be checked
	 * @return true  if capital value is valid
	 * 		   false if capital value is not valid
	 */
	public static boolean isValidCapital(int capital) {
		return capital > 0;
	}
	
	/** Checks if the provided value is a valid years value.
	 * All years values must be positive.
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @param years the years value to be checked
	 * @return true  if years value is valid
	 * 		   false if years value is not valid
	 */
	public static boolean isValidYears(int years) {
		return years > 0;
	}
	
	/** Checks if the provided value is a valid interest rate value.
	 * All interest rate values must be positive and not exceed 1.0.
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @param interestRate the interest rate value to be checked
	 * @return true  if interest rate value is valid
	 * 		   false if interest rate value is not valid
	 */
	public static boolean isValidInterestRate(double interestRate) {
		return interestRate > 0.0 && interestRate <= 1.0;
	}
	
	/** Create a new Investment with the specified parameters
	 * 
	 * @precondition capital > 0 &&
	 * 				 0.0 < interestRate <= 1.0 &&
	 * 				 years > 0
	 * @postcondition getCapital() == capital &&
	 * 				  getInterestRate() == interestRate &&
	 * 				  getYears() == years
	 * 
	 * @param capital the capital for the investment
	 * @param interestRate the interest rate for the investment
	 * @param years the number of years for the investment to mature
	 */
	public Investment (int capital, double interestRate, int years) {
		if (!Investment.isValidCapital(capital)) {
			throw new IllegalArgumentException("Capital must be positive, but was " + capital);
		}
		if (!Investment.isValidInterestRate(interestRate)) {
			throw new IllegalArgumentException("Interest rate must be between 0.0 and 1.0, but was " + interestRate);
		}
		if (!Investment.isValidYears(years)) {
			throw new IllegalArgumentException("Years must be positive, but was " + years);
		}
		this.capital = capital;
		this.interestRate = interestRate;
		this.years = years;
	}
	
	/** Returns the capital for the investment
	 *  
	 *  @precondition none
	 *  @postcondition none
	 *  
	 * @return the capital for the investment
	 */
	public int getCapital() {
		return this.capital;
	}

	/** Returns the interest rate for the investment
	 *  
	 *  @precondition none
	 *  @postcondition none
	 *  
	 * @return the interest rate for the investment
	 */
	public double getInterestRate() {
		return this.interestRate;
	}

	/** Returns the number of years for the investment to mature
	 *  
	 *  @precondition none
	 *  @postcondition none
	 *  
	 * @return the number of years for the investment to mature
	 */
	public int getYears() {
		return this.years;
	}
	
	@Override
	public String toString() {
		return this.capital + "," + this.interestRate + "," + this.years;
	}

}
