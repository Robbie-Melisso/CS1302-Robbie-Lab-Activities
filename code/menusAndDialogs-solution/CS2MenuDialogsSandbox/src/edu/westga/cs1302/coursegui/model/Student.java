package edu.westga.cs1302.coursegui.model;

import edu.westga.cs1302.coursegui.resources.UI;

/**
 * The Class Student represents the id, name, and GPA of a student.
 * 
 * @author CS1302
 * @version 1.0
 */
public class Student implements Comparable<Student> {
	private final StudentId id;
	private final String name;
	private final double gpa;

	/**
	 * Creates a new Student object with the specified id, name, and gpa.
	 * 
	 * @precondition id != null && id.lenth() > 0 && name != null &&
	 *               name.lenth() > 0 && gpa >=0 && gpa <=4
	 * @postcondition this.getId()==id && this.getName()==name && this.getGpa() == gpa
	 * 
	 * @param id
	 *            the id of the student
	 * @param name
	 *            the name of the student
	 * @param gpa
	 *            the student's GPA
	 */
	public Student(String id, String name, double gpa) {		
		if (name == null) {
			throw new IllegalArgumentException(UI.NULL_NAME);
		}
		if (name.length() == 0) {
			throw new IllegalArgumentException(UI.EMPTY_NAME);
		}
		if (gpa < 0) {
			throw new IllegalArgumentException(UI.GPA_LESS_THAN_ZERO);
		}

		if (gpa > 4) {
			throw new IllegalArgumentException(UI.GPA_GREATER_THAN_FOUR);
		}

		this.id = new StudentId(id) {
			@Override
			protected void checkId(String ssn) {
				super.checkId(ssn);
				if (!ssn.matches("\\d{3}-\\d{2}-\\d{4}")) {
					throw new IllegalArgumentException(UI.INVALID_SSN);
				}
			}
		};
		
		this.name = name;
		this.gpa = gpa;
	}

	/**
	 * Returns the name of the student.
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Returns the id of the student
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the student's id
	 */
	public String getId() {
		return this.id.get();
	}

	/**
	 * Returns the GPA of the student.
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the GPA of the student.
	 */
	public double getGpa() {
		return this.gpa;
	}

	@Override
	public String toString() {
		return this.getId() + " " + this.getName() + " " + this.getGpa();
	}
	
	@Override
	public int compareTo(Student student) {
		return this.getId().compareTo(student.getId());
	}
}
