package edu.westga.cs1302.password_generator.test.viewmodel.viewmodel;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.password_generator.viewmodel.ViewModel;

class TestGeneratePassword {

	@Test
	void testMinimumLengthNotANumber() {
		ViewModel vm = new ViewModel();
		vm.getMinimumLength().setValue("apple");
		
		vm.generatePassword();
		assertTrue(vm.getGenerated().getValue().isEmpty());
		assertEquals("Invalid Minimum Length: must be a positive integer, but was apple", vm.getErrorText().getValue(), "checking the error text property");
	}
	
	@Test
	void testMinimumLengthNotAValidNumber() {
		ViewModel vm = new ViewModel();
		vm.getMinimumLength().setValue("-2");
		
		vm.generatePassword();
		
		assertTrue(vm.getGenerated().get().isEmpty());
		assertEquals("Invalid Minimum Length: minimum length must be at least 1", vm.getErrorText().getValue(), "checking the error text property");
	}
	
	@Test
	void testValidInputProvided() {
		ViewModel vm = new ViewModel();
		vm.getMinimumLength().setValue("2");
		
		vm.generatePassword();
		
		assertTrue(vm.getGenerated().getValue().get(0).length() >= 2, "checking the password property has an appropriate number of characters");
		assertEquals("", vm.getErrorText().getValue(), "checking the error text property");
	}
	
	@Test
	void testMultipleValidInputs() {
		ViewModel vm = new ViewModel();
		vm.getMinimumLength().setValue("2");
		
		vm.generatePassword();
		
		assertTrue(vm.getGenerated().getValue().get(0).length() >= 2, "checking the password property has an appropriate number of characters");
		assertEquals("", vm.getErrorText().getValue(), "checking the error text property");
		
		String pass1 = vm.getGenerated().get().get(0);
		
		vm.getMinimumLength().setValue("16");
		
		vm.generatePassword();
		
		assertTrue(vm.getGenerated().getValue().get(0).length() >= 16, "checking the password property has an appropriate number of characters");
		assertEquals("", vm.getErrorText().getValue(), "checking the error text property");
		assertEquals(pass1, vm.getGenerated().get().get(1));
		
	}

}
