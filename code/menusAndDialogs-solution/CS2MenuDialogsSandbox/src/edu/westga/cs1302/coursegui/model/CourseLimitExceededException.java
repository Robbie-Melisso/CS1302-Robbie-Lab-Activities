package edu.westga.cs1302.coursegui.model;

/**
 * The Class CourseLimitExceededException.
 * 
 * @author CS1302
 * @version 1.0
 */
public class CourseLimitExceededException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new course limit exceeded exception.
	 * 
	 * @precondition none
	 * @postcondition none
	 *
	 * @param message the message
	 */
	public CourseLimitExceededException(String message) {
		super(message);
	}

}
