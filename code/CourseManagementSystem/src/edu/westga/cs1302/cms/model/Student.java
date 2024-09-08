package edu.westga.cs1302.cms.model;

/** Stores and manages information for a single student.
 * 
 * @author CS 1302
 * @version Fall 2024
 */
public class Student {
	private String name;
	private double grade;
	
	/** Create a new student with the specified name and default grade of zero
	 * 
	 * @precondition name != null && name.length() >= 3
	 * @postcondition getName() == name
	 * 
	 * @throws IllegalArgumentException when precondition is violated
	 * 
	 * @param name the name of the new student
	 */
	public Student(String name) throws IllegalArgumentException {
		if (name == null) {
			throw new IllegalArgumentException("Name must be provided.");
		}
		if (name.length() < 3) {
			throw new IllegalArgumentException("Name must have at least 3 characters.");
		}
		this.name = name;
		this.grade = 0;
	}
	
	/** Create a new student with the specified name
	 * 
	 * @precondition name != null && name.length() >= 3
	 * @postcondition getName() == name
	 * 
	 * @throws IllegalArgumentException when precondition is violated
	 * 
	 * @param name the name of the new student
	 * @param grade the grade of student
	 */
	public Student(String name, double grade) throws IllegalArgumentException {
		if (name == null) {
			throw new IllegalArgumentException("Name must be provided.");
		}
		if (name.length() < 3) {
			throw new IllegalArgumentException("Name must have at least 3 characters.");
		}
		if (grade < 0 || grade > 100) {
			throw new IllegalArgumentException("grade must be between 0 and 100");
		}
		this.name = name;
		this.grade = grade;
	}
	
	/**setter for object field grade
	 * 
	 * @param newgrade grade to set object grade to
	 * @throws IllegalArgumentException
	 */
	public void setgrade(double newgrade) throws IllegalArgumentException {
		if (newgrade < 0 || newgrade > 100) {
			throw new IllegalArgumentException("grade must be between 0 and 100");
		}
		this.grade = newgrade;
	}
	
	/** Return the name of the student
	 * 
	 * @return the name of the student
	 */
	public String getName() {
		return this.name;
	}
	
	/**return student grade
	 * 
	 * @return student grade
	 */
	public double getGrade() {
		return this.grade;
	}
	
	/**leave alone, used to display name on list
	 * 
	 */
	@Override
	public String toString() {
		return this.name;
	}
	
}
