package edu.westga.cs1302.project3.viewmodel;

import edu.westga.cs1302.project3.model.Task;
import edu.westga.cs1302.project3.model.TaskManager;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;

/**
 * operational model of task manager application
 * 
 * @author rmeliss1
 * @version Proj3.1
 */
public class ViewModel {

	private StringProperty taskBuildingTitle;
	private StringProperty taskBuildingDesc;
	private TaskManager manager;
	private ListProperty<Task> taskListProprty;
	
	/**
	 * construct new ViewModel
	 */
	public ViewModel() {
		this.taskBuildingTitle = new SimpleStringProperty("");
		this.taskBuildingDesc = new SimpleStringProperty("");
		this.taskListProprty = new SimpleListProperty<Task>(FXCollections.observableArrayList());
		
		this.manager = new TaskManager();
		
		//BIND TEXT FROM VIEWMODEL SIDE HERE
		this.defaultStart();
	}
	
	private void defaultStart() {
		this.taskBuildingTitle.set("task 1");
		this.taskBuildingDesc.set("fix all things, pass exam");
		this.addTask();
		
		this.taskBuildingTitle.set("task 2");
		this.taskBuildingDesc.set("make notes, pass final");
		this.addTask();
	}
	
	/**Build new Task from textFields
	 * 
	 * @return verification of completed addition
	 * @throws IllegalStateException when duplicate task title exists 
	 */
	public Boolean addTask() throws IllegalStateException {
		Task created = new Task(this.taskBuildingTitle(), this.taskBuildingDesc());
			this.manager.addTask(created);
			this.taskListProprty.add(created);
			
			return true;
	}
	
	/**remove specified task from data set
	 * 
	 * @param removing the task to be removed, meant to be taken from GUI selection model
	 * @throws IllegalStateException when unable to find specified task
	 */
	public void removeTask(Task removing) throws IllegalStateException {
		try {
			this.manager.removeTask(removing);
			this.taskListProprty.remove(removing);
		} catch (IllegalStateException err) {
			throw new IllegalStateException("could not find selected task");
		}
	}
	
	/**get task list
	 * 
	 * @return this.taskListProperty
	 */
	public ListProperty<Task> taskList() {
		return this.taskListProprty;
	}
	
	/**get text of task construction title
	 * 
	 * @return String text of new build title
	 */
	public String taskBuildingTitle() {
		return this.taskBuildingTitle.get();
	}
	
	/**get text of task construction description
	 * 
	 * @return String text of new build description
	 */
	public String taskBuildingDesc() {
		return this.taskBuildingDesc.get();
	}
	
}
