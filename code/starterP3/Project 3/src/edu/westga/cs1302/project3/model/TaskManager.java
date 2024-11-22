package edu.westga.cs1302.project3.model;

import java.util.HashMap;
import java.util.Map;

/**Stores set of tasks, can add and remove tasks as necessary only allows unique task titles
 * @author rmeliss1
 * @version Proj3.1
 */
public class TaskManager {
	private Map<String, Task> taskMap;
	
	/**create new task manager, no tasks exist at the start
	 * 
	 */
	public TaskManager() {
		this.taskMap = new HashMap<String, Task>();
	}
	
	/**adds task to taskMap, only allows unique task titles
	 * 
	 * @param task task to be added
	 * @throws IllegalStateException when task with identical title already exists
	 */
	public void addTask(Task task) throws IllegalStateException {
		if (this.taskExists(task)) {
			throw new IllegalStateException("task with identical title already exists");
		} else {
			this.taskMap.put(task.getTitle(), task);
		}
	}
	
	/**remove task from taskMap, gives error if does not exist
	 * 
	 * @param task task to be removed
	 * @throws IllegalStateException when task with title cannot be found in map
	 */
	public void removeTask(Task task) throws IllegalStateException {
		if (!this.taskExists(task)) {
			throw new IllegalStateException("no task exists with given title");
		} else {
			this.taskMap.remove(task.getTitle());
		}
	}
	
	/**get map of created tasks
	 * 
	 * @return this.taskMap
	 */
	public Map<String, Task> getTaskList() {
		return this.taskMap;
	}
	
	/** search for existence of specified task
	 * 
	 * @param task search for corresponding title
	 * @return whether task with specified title exists
	 */
	public boolean taskExists(Task task) {
		return this.taskMap.containsKey(task.getTitle());
	}
}
