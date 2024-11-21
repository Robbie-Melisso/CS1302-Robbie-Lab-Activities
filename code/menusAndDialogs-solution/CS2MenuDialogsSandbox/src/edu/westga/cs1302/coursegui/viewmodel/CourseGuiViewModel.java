package edu.westga.cs1302.coursegui.viewmodel;

import edu.westga.cs1302.coursegui.model.Course;
import edu.westga.cs1302.coursegui.model.Student;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;

/**
 * Class ViewModel.
 *
 * @author CS1302
 * @version 1.0
 */
public class CourseGuiViewModel {

	private final StringProperty idProperty;
	private final StringProperty nameProperty;
	private final StringProperty gpaProperty;
	private final StringProperty displayProperty;
	private final BooleanProperty isEmptyCourseProperty;
	private final IntegerProperty courseCapacityProperty;
	private final ListProperty<Student> studentsProperty;

	private Course course;

	/**
	 * Instantiates a new view model.
	 * 
	 * @precondition none
	 * @postcondition idProperty() != null
	 *                && nameProperty() != null && gpaProperty() != null &&
	 *                displayProperty() != null
	 * 
	 */
	public CourseGuiViewModel() {
		this.idProperty = new SimpleStringProperty("");
		this.nameProperty = new SimpleStringProperty("");
		this.gpaProperty = new SimpleStringProperty("");
		this.displayProperty = new SimpleStringProperty();
		this.isEmptyCourseProperty = new SimpleBooleanProperty();
		this.courseCapacityProperty = new SimpleIntegerProperty();
		
		this.course = new Course("CS3000");
		this.updateEmptyCourseProperty();		
		this.studentsProperty = new SimpleListProperty<Student>(FXCollections.observableArrayList(this.course));
		this.courseCapacityProperty.setValue(this.course.getCapacity());
	}

	/**
	 * returns id property.
	 *
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the id property
	 */
	public StringProperty idProperty() {
		return this.idProperty;
	}

	/**
	 * Returns the name property.
	 *
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the name property
	 */
	public StringProperty nameProperty() {
		return this.nameProperty;
	}

	/**
	 * Returns the GPA property.
	 *
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the GPA property
	 */
	public StringProperty gpaProperty() {
		return this.gpaProperty;
	}

	/**
	 * Returns the display property.
	 *
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the display property
	 */
	public StringProperty displayProperty() {
		return this.displayProperty;
	}
	
	/**
	 * Returns the empty course property.
	 *
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the name property
	 */
	public BooleanProperty isEmptyCourseProperty() {
		return this.isEmptyCourseProperty;
	}
	
	/**
	 * Returns the capacity property.
	 *
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the capacity property
	 */
	public IntegerProperty capacityProperty() {
		return this.courseCapacityProperty;
	}
	
	/**
	 * Returns the list of students property.
	 *
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the name property
	 */
	public ListProperty<Student> studentsProperty() {
		return this.studentsProperty;
	}
	
	
	private void updateEmptyCourseProperty() {
		if (this.course.size() == 0) {
			this.isEmptyCourseProperty.setValue(true);
		} else {
			this.isEmptyCourseProperty.setValue(false);
		}
	}
	

	/**
	 * Adds the student.
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return true, if student added, false otherwise
	 * 
	 */
	public boolean addStudent() {
		String id = this.idProperty().get();
		String name = this.nameProperty.get();
		double gpa = Double.parseDouble(this.gpaProperty.get());
		Student aStudent = null;
		aStudent = new Student(id, name, gpa);
		
		if (this.course.add(aStudent)) {
			this.clear();
			this.updateDisplay();
			this.updateEmptyCourseProperty();
			this.studentsProperty.set(FXCollections.observableArrayList(this.course));
			return true;
		}

		return false;
	}

	/**
	 * Search for a student.
	 *
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return true, if student found, false otherwise
	 */
	public boolean searchStudent() {
		String id = this.idProperty().get();
		Student student = this.course.getById(id);
		
		if (student != null) {
			this.setStudent(student);
			return true;
		}
		
		return false;
	}

	/**
	 * Remove a student.
	 *
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return true, if student removed, false otherwise
	 */
	public boolean removeStudent() {
		String id = this.idProperty().get();
		
		if (this.course.removeById(id)) {
			this.clear();
			this.updateDisplay();
			this.updateEmptyCourseProperty();
			this.studentsProperty.set(FXCollections.observableArrayList(this.course));			
			return true;
		}
		
		return false;
	}

	/**
	 * Remove all student.
	 *
	 * @precondition none
	 * @postcondition none
	 */
	public void removeAllStudents() {
		this.course.clear();
		this.updateDisplay();
		this.updateEmptyCourseProperty();
		this.studentsProperty.set(FXCollections.observableArrayList(this.course));			
		this.clear();
	}

	/**
	 * Sets the name and GPA of the specified student.
	 *
	 * @precondition none
	 * @postcondition none
	 * 
	 * @param student
	 *            the student
	 */
	private void setStudent(Student student) {
		this.nameProperty.set(student.getName());
		this.gpaProperty.set(Double.toString(student.getGpa()));
	}

	/**
	 * Clears the id, name, and GPA
	 * 
	 * @precondition none
	 * @postcondition idProperty().get() == "" nameProperty().get() == ""
	 *                && gpaProperty().get() == ""
	 */
	private void clear() {
		this.idProperty.set("");
		this.clearNameGPA();

	}

	/**
	 * Clears the name and GPA.
	 * 
	 * @precondition none
	 * @postcondition nameProperty().get() == "" && gpaProperty().get() == ""
	 */
	public void clearNameGPA() {
		this.nameProperty.set("");
		this.gpaProperty.set("");
	}

	private void updateDisplay() {
		String display = "";
		for (Student student : this.course) {
			display += student + System.lineSeparator();
		}
		this.displayProperty.set(display);
	}
	
	/**
	 * Removes the student.
	 *
	 * @param student the student
	 * @return true, if successful
	 */
	public boolean removeStudent(Student student) {
	
		if (this.course.removeById(student.getId())) {
			this.clear();
			this.updateDisplay();
			this.updateEmptyCourseProperty();
			this.studentsProperty.set(FXCollections.observableArrayList(this.course));			
			return true;
		}
		
		return false;
		
	}
	
	/**
	 * Updates course capacity.
	 */
	public void updateCapacity() {
		this.course.setCapacity(this.capacityProperty().get());
		
	}

}
