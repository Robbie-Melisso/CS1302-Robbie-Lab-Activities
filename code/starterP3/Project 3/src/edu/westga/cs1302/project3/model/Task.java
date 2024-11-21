package edu.westga.cs1302.project3.model;

/**Task Object, contains a title and description
 * 
 * @author rmeliss1
 * @version 1
 */
public class Task {
	
	private String title;
	private String description;
	
	/** contains a task with a name and a description of the task
	 * @param title short description of the task 
	 * @param description details about what is required for the task
	 * @throws IllegalArgumeneException when title or description is blank or null
	 */
	public Task(String title, String description) throws IllegalArgumentException {
		if (title.isBlank()) {
			throw new IllegalArgumentException("Given title cannot be empty");
		}
		if (description.isBlank()) {
			throw new IllegalArgumentException("Description cannot be empty");
		}
		
		this.title = title;
		this.description = description;
	}

	/**get title value
	 * @return this.title
	 */
	public String getTitle() {
		return this.title;
	}
	
	/**get task description
	 * @return this.description
	 */
	public String getDescription() {
		return this.description;
	}
}
