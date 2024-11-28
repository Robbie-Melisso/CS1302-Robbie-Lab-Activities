package edu.westga.cs1302.project3.test.ViewModel;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.project3.model.Task;
import edu.westga.cs1302.project3.viewmodel.ViewModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

class TestAddRemoveTask {
//TaskManager already tested add methods, only testing list
	Task first = new Task("The First Task","Description 1");
	Task second = new Task("The Second Task","Description 2");

	StringProperty title = new SimpleStringProperty("");
	StringProperty desc = new SimpleStringProperty("");

	ViewModel vm = new ViewModel();
	
	@Test
	void testSunnyAddRemove() {
		assertTrue(vm.taskList().size() == 0);
		this.resetBindings();
		this.setFields(first);
		vm.addTask();
		assertTrue(vm.taskList().size() == 1);
		assertTrue(vm.taskList().get(0).equals(first));
		
		this.setFields(second);
		vm.addTask();

		assertTrue(vm.taskList().size() == 2);
		assertTrue(vm.taskList().get(1).equals(second));
		
		vm.removeTask(first);
		assertTrue(vm.taskList().size() == 1);
		assertTrue(vm.taskList().get(0).equals(second));
		
		vm.removeTask(second);
		assertTrue(vm.taskList().size() == 0);

	}

	@Test
	void testTwoSameTasks() {
		assertTrue(vm.taskList().size() == 0);
		this.resetBindings();
		this.setFields(second);
		vm.addTask();
		
		assertTrue(vm.taskList().size() == 1);
		assertTrue(vm.taskList().get(0).equals(second));
		
		assertThrows(IllegalStateException.class, () -> {
			vm.addTask();
		});
		
		vm.removeTask(second);
		assertTrue(vm.taskList().size() == 0);
	}

	@Test
	void testRemoveNonexistent() {
		assertTrue(vm.taskList().size() == 0);
		this.resetBindings();
		this.setFields(first);
		vm.addTask();
		assertTrue(vm.taskList().size() == 1);
		assertTrue(vm.taskList().get(0).equals(first));
		
		assertThrows(IllegalStateException.class, () -> {
			vm.removeTask(second);
		});
		
		vm.removeTask(first);
		assertTrue(vm.taskList().size() == 0);
	}

	@Test
	void testIllegalFields() {
		assertTrue(vm.taskList().size() == 0);
		this.resetBindings();
		
		title.set("");
		desc.set(first.getDescription());
		assertThrows(IllegalArgumentException.class, () -> {
			vm.addTask();
		});
		
		title.set("kamcla:ascak");
		desc.set(first.getDescription());
		assertThrows(IllegalArgumentException.class, () -> {
			vm.addTask();
		});
		
		desc.set("kamcla:ascak");
		title.set(first.getDescription());
		assertThrows(IllegalArgumentException.class, () -> {
			vm.addTask();
		});
		
		desc.set("");
		title.set(first.getDescription());
		assertThrows(IllegalArgumentException.class, () -> {
			vm.addTask();
		});
		
	}

	void setFields(Task task) {
		title.set(task.getTitle());
		desc.set(task.getDescription());
	}
	
	void resetBindings() {
		if(title.isBound()){
			title.unbind();
			desc.unbind();
			title.bindBidirectional(vm.taskBuildingTitle());
			desc.bindBidirectional(vm.taskBuildingDesc());
			title.set("");
			desc.set("");
		} else {
			title.bindBidirectional(vm.taskBuildingTitle());
			desc.bindBidirectional(vm.taskBuildingDesc());
			title.set("");
			desc.set("");
		}
	}
}
