package edu.westga.cs1302.password_generator.viewmodel;

import java.util.Random;

import edu.westga.cs1302.password_generator.model.PasswordGenerator;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * password generator view model
 * @author rmeliss1
 * @version Lab7
 */
public class PasswordGeneratorViewModel {
	
	private StringProperty minLengthProperty;
	private BooleanProperty mustIncludeDigits;
	private BooleanProperty mustIncludeLowerCaseLetters;
	private BooleanProperty mustIncludeUpperCaseLetters;
	private StringProperty outputProperty;
	
	/**
	 * creates initial state of view model
	 * 
	 */
	public PasswordGeneratorViewModel() {
		this.minLengthProperty = new SimpleStringProperty("");
		this.outputProperty = new SimpleStringProperty("");
		
		this.mustIncludeDigits = new SimpleBooleanProperty(false);
		this.mustIncludeLowerCaseLetters = new SimpleBooleanProperty(false);
		this.mustIncludeUpperCaseLetters = new SimpleBooleanProperty(false);
	}
	
	/**
	 * generates password according to given settings
	 * minLength, inc digits, inc lower case, inc upper case
	 * 
	 * outputs error message when invalid input is given
	 */
	public void generatePassword() {
		String errReport = "";
		try {
			int minLength = Integer.parseInt(this.minLengthProperty.getValue());
			
			PasswordGenerator generator = new PasswordGenerator(new Random().nextLong());
			try {
				generator.setMinimumLength(minLength);
				
				generator.setMustHaveAtLeastOneDigit(this.mustIncludeDigits.get());
				generator.setMustHaveAtLeastOneLowerCaseLetter(this.mustIncludeLowerCaseLetters.get());
				generator.setMustHaveAtLeastOneUpperCaseLetter(this.mustIncludeUpperCaseLetters.get());
		
				this.outputProperty.setValue(generator.generatePassword());
				
			} catch (IllegalArgumentException err) {
				errReport += "Invalid length given, length must be at least 1" + System.lineSeparator();
				this.outputProperty.setValue(errReport);
			}
			
		} catch (NumberFormatException err) {
			errReport += "Invalid length given, only type digits" + System.lineSeparator();
			this.outputProperty.setValue(errReport);
		}
		
	}
	
	/**
	 * get minimum length property
	 * @return this.minlengthproperty
	 */
	public StringProperty minLengthProperty() {
		return this.minLengthProperty;
	}
	
	/**
	 * get generated password
	 * @return this.outputProperty
	 */
	public StringProperty outputProperty() {
		return this.outputProperty;
	}

	/**
	 * tick box for requirement including upper case letters
	 * @return this.mustIncludeUpperCaseLetters
	 */
	public BooleanProperty mustIncludeUpperCaseLetters() {
		return this.mustIncludeUpperCaseLetters;
	}
	
	/**
	 * tick box for requirement including lower case letters
	 * @return this.mustIncludeLowerCAseLetters
	 */
	public BooleanProperty mustIncludeLowerCaseLetters() {
		return this.mustIncludeLowerCaseLetters;
	}

	/**
	 * tick box for generated password including digits
	 * @return this.mustIncludeDigits
	 */
	public BooleanProperty mustIncludeDigits() {
		return this.mustIncludeDigits;
	}
	
}
