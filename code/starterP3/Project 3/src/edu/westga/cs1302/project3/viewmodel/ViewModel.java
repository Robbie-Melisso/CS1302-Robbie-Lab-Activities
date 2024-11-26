package edu.westga.cs1302.project3.viewmodel;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import edu.westga.cs1302.project3.model.Task;
import edu.westga.cs1302.project3.model.TaskManager;
import edu.westga.cs1302.project3.model.TaskPersistenceManager;
import edu.westga.cs1302.project3.view.AddTaskWindow;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;

/**
 * operational model of task manager application
 * 
 * @author rmeliss1
 * @version Proj3 2
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
	public Boolean addTask() throws IllegalStateException, IllegalArgumentException {
		Task created = new Task(this.taskBuildingTitle().getValue(), this.taskBuildingDesc().getValue());
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
	
	/**Save tasks to given file
	 * 
	 * @param file file to be saved to
	 * @throws IOException when file unreadable
	 */
	public void save(File file) throws IOException {
		try {
			TaskPersistenceManager.saveToFile(file, this.manager);
		} catch (IllegalArgumentException err) {
			//caught here because it is impossible for ViewModel to pass null manager object, initialized at object creation 
			err.printStackTrace();
		}
	}
	
	/**load data from given file
	 * 
	 * @param file file to read from\
	 * @throws IOException when file unreadable
	 * @throws IllegalArgumentException when file incorrectly formatted or populated
	 * @throws IllegalStateException when file contains multiple entries of identical task titles
	 */
	public void load(File file) throws IllegalArgumentException, IllegalStateException, IOException {
		//this.manager = new TaskManager();
		//TaskManager temp = TaskPersistenceManager.loadFromFile(file);
		this.manager = TaskPersistenceManager.loadFromFile(file);
		this.taskListProprty.clear();
		for (Map.Entry<String, Task> entry : this.manager.getTaskList().entrySet()) {
			this.taskListProprty.add(entry.getValue());
		}
	}
	
	/**
	 * new instance of task window, new bindings, observed value is on the window not the viewmodel
	 * @param win current AddTaskWindow codebehind instance
	 */
	public void freshWindow(AddTaskWindow win) {
		
		//this.taskBuildingTitle.set("");
		//this.taskBuildingDesc.set("");
		if (this.taskBuildingTitle.isBound()) {
			this.taskBuildingTitle.unbind();
		}
		if (this.taskBuildingDesc.isBound()) {
			this.taskBuildingDesc.unbind();
		}
		this.taskBuildingTitle.bind(win.title().textProperty());
		this.taskBuildingDesc.bind(win.description().textProperty());
		//why if you just return the textproperty does this not work
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
	public StringProperty taskBuildingTitle() {
		return this.taskBuildingTitle;
	}
	
	/**get text of task construction description
	 * 
	 * @return String text of new build description
	 */
	public StringProperty taskBuildingDesc() {
		return this.taskBuildingDesc;
	}
	
}
