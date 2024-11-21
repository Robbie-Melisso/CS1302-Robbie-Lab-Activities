package edu.westga.cs1302.coursegui.model;

import edu.westga.cs1302.coursegui.resources.UI;

/**
 * The Class StudentId.
 * 
 * @author CS 1302
 * @version 1.0
 */
public class StudentId {
	private String id;

	/**
	 * Instantiates a new student id.
	 *
	 * @precondition id != null && id is not empty
	 * @postcondition get() == id
	 * 
	 * @param id
	 *            the student id
	 */
	public StudentId(String id) {
		this.checkId(id);
		this.id = id;
	}

	/**
	 * Gets the string representation of the student id.
	 *
	 * @precondition none
	 * @postcondition none
	 *
	 * @return the id
	 */
	public String get() {
		return this.id;
	}

	/**
	 * Sets the student id.
	 *
	 * @precondition id != null && id is not empty
	 * @postcondition get() == id
	 * 
	 * @param id
	 *            the student id
	 */
	public void set(String id) {
		this.checkId(id);
		this.id = id;
	}
	
	protected void checkId(String id) {
		if (id == null) {
			throw new IllegalArgumentException(UI.NULL_ID);
		}
		if (id.isEmpty()) {
			throw new IllegalArgumentException(UI.EMPTY_ID);
		}
	}
	
	@Override
	public String toString() {
		return this.id;
	}
}
