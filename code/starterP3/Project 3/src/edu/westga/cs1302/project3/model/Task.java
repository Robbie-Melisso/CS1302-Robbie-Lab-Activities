package edu.westga.cs1302.project3.model;

/**Task Object, contains a title and description
 * 
 * @author rmeliss1
 * @version Proj3.2
 */
public class Task {
	
	private String title;
	private String description;
	
	/** contains a task with a name and a description of the task
	 * fields may not be blank, may not be null, may not contain a colon
	 * @param title short description of the task 
	 * @param description details about what is required for the task
	 * @throws IllegalArgumeneException when title or description is blank or null
	 * @throws IllegalArgumentException when title or description contain a colon
	 */
	public Task(String title, String description) throws IllegalArgumentException {
		if (title.isBlank() || description.isBlank()) {
			throw new IllegalArgumentException("Task Fields cannot be empty");
		}
		//if (description.isBlank()) {
		//	throw new IllegalArgumentException("Description cannot be empty");
		//}
		if (title.contains(":") || description.contains(":")) {
			throw new IllegalArgumentException("Task may not contain colon");
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
	
	/**give text representation of task object meant to interface with display elements
	 * @return text representation of task
	 */
	@Override
	public String toString() {
		return this.title + System.lineSeparator() + "\t" + this.description;
	}
	
	/**text representation of task meant for save manager
	 * 
	 * @return colon separated representation of task
	 */
	public String textifier() {
		return this.title + ":" + this.description;

	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		return (this.getTitle().equals(((Task) obj).getTitle()) && (this.getDescription().equals(((Task) obj).getDescription())));
		//Task other = (Task) obj;
		//return ((this.getTitle() == other.getTitle()) && (this.getDescription() == other.getDescription()));
	}
}
