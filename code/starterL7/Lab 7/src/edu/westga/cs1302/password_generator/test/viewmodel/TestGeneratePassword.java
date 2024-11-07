package edu.westga.cs1302.password_generator.test.viewmodel;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.password_generator.viewmodel.PasswordGeneratorViewModel;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

class TestGeneratePassword {


	StringProperty output;
	StringProperty input;
	BooleanProperty digits;
	BooleanProperty lowerCase;
	BooleanProperty upperCase;
	PasswordGeneratorViewModel generator = new PasswordGeneratorViewModel();
	
	private void initialize(PasswordGeneratorViewModel generator) {
		output = new SimpleStringProperty();
		output.bind(generator.outputProperty());
		input = new SimpleStringProperty();
		input.bindBidirectional(generator.minLengthProperty());
		digits = new SimpleBooleanProperty();
		digits.bindBidirectional(generator.mustIncludeDigits());
		lowerCase = new SimpleBooleanProperty();
		lowerCase.bindBidirectional(generator.mustIncludeLowerCaseLetters());
		upperCase = new SimpleBooleanProperty();
		upperCase.bindBidirectional(generator.mustIncludeUpperCaseLetters());
	}
	

	@Test
	void testInitialValues() {
		initialize(generator);
		
		assertEquals(output.get(),"");
		assertEquals(input.get(),"");
		assertFalse(digits.get());
		assertFalse(lowerCase.get());
		assertFalse(upperCase.get());
		
	}
	
	@Test
	void testDigitToggle() {

		initialize(generator);
		digits.set(true);
		input.set("2");
		generator.generatePassword();
		assertTrue(output.get().matches(".*\\d+.*"));
	}
	
	@Test
	void testUpperToggle() {
		initialize(generator);
		upperCase.set(true);
		input.set("2");
		generator.generatePassword();
		assertTrue(output.get().matches(".*[A-Z]+.*"));
	}
	
	@Test
	void testLowerToggle() {
		initialize(generator);
		lowerCase.set(true);
		input.set("2");
		generator.generatePassword();
		assertTrue(output.get().matches(".*[a-z]+.*"));
	}
	
	@Test
	void testInvalidLength() {
		initialize(generator);
		input.set("Oh hello there");
		generator.generatePassword();
		assertTrue(this.output.get().equals("Invalid length given, only type digits" + System.lineSeparator()));
		
		input.set("-2");
		generator.generatePassword();
		assertTrue(this.output.get().equals("Invalid length given, length must be at least 1" + System.lineSeparator()));
		
	}
	
	@Test
	void testValidLength() {
		initialize(generator);
		input.set("2");
		generator.generatePassword();
		assertTrue(output.get().length() >= 2);
		
		input.set("8");
		generator.generatePassword();
		assertTrue(output.get().length() >= 8);
		
		input.set("26");
		generator.generatePassword();
		assertTrue(output.get().length() >= 26);
		
		input.set("3");
		generator.generatePassword();
		assertTrue(output.get().length() >= 3);
	}

}
