package edu.westga.cs1302.coursegui.viewmodel;

import java.util.Collection;

import edu.westga.cs1302.coursegui.model.Course;
import edu.westga.cs1302.coursegui.model.Student;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;

/**
 * Class ViewModel.
 *
 * @author CS 1302
 * @version Fall 2024
 */
public class CourseGuiViewModel {

	private final StringProperty idProperty;
	private final StringProperty nameProperty;
	private final DoubleProperty gpaProperty;
	private final DoubleProperty minGpaProperty;
	private final ListProperty<Student> studentListProperty;
	private final BooleanProperty isEmptyCourseProperty;
	
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
		this.gpaProperty = new SimpleDoubleProperty();
		this.minGpaProperty = new SimpleDoubleProperty();
		this.isEmptyCourseProperty = new SimpleBooleanProperty();
		this.isEmptyCourseProperty.setValue(true);
		
		this.course = new Course("CS3000");
		this.studentListProperty = new SimpleListProperty<Student>(FXCollections.observableArrayList(this.course));
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
	public DoubleProperty gpaProperty() {
		return this.gpaProperty;
	}
	
	/**
	 * Returns the min GPA property.
	 *
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the min GPA property
	 */
	public DoubleProperty minGpaProperty() {
		return this.minGpaProperty;
	}

	/**
	 * Returns the student list property.
	 *
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the student list property
	 */
	public ListProperty<Student> studentListProperty() {
		return this.studentListProperty;
	}

	/**
	 * Returns the is empty course property.
	 *
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the is empty course property
	 */
	public BooleanProperty isEmptyCourseProperty() {
		return this.isEmptyCourseProperty;
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
		double gpa = this.gpaProperty.get();
		Student aStudent = null;
		aStudent = new Student(id, name, gpa);
		
		if (this.course.add(aStudent)) {
			this.clear();
			this.updateDisplay();
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
		this.gpaProperty.set(student.getGpa());
	}

	/**
	 * Sets the student list to the students with the minimum GPA as specified by the minGpaProperty.
	 *
	 * @precondition none
	 * @postcondition none
	 */
	public void filterByMinGPA() {
		double minGpa = this.minGpaProperty.get();
		Collection<Student> filteredStudents = this.course.filterBy(student -> student.getGpa() >= minGpa);
		this.studentListProperty.set(FXCollections.observableArrayList(filteredStudents));
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
		this.gpaProperty.set(0);
	}

	private void updateDisplay() {
		this.studentListProperty.set(FXCollections.observableArrayList(this.course));
		this.isEmptyCourseProperty.setValue(this.course.size() == 0);
	}

}
