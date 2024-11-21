package edu.westga.cs1302.coursegui.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.function.Predicate;

import edu.westga.cs1302.coursegui.resources.UI;

/**
 * The Class Course.
 * 
 * @author CS 1302
 * @version Fall 2024
 */
public class Course implements Collection<Student> {

	private String name;
	private final Map<String, Student> students;

	/**
	 * Instantiates a new course.
	 * 
	 * @precondition none
	 * @postcondition getName() == "default" && size() == 0
	 * 
	 * @param name the name of the course
	 */
	public Course() {
		this.name = "default";
		this.students = new HashMap<String, Student>();
	}

	/**
	 * Instantiates a new course.
	 * 
	 * @precondition name != null
	 * @postcondition getName() == name && size() == 0
	 * 
	 * @param name the name of the course
	 */
	public Course(String name) {
		if (name == null) {
			throw new IllegalArgumentException(UI.NULL_NAME);
		}
		if (name.isEmpty()) {
			throw new IllegalArgumentException(UI.EMPTY_NAME);
		}
		this.name = name;
		this.students = new HashMap<String, Student>();
	}

	/**
	 * Gets the name of this course.
	 *
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the student with specified id
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Gets the student with specified id.
	 *
	 * @precondition id != null
	 * @postcondition none
	 * 
	 * @param id the id
	 * @return the student with specified id; returns null if there is no student
	 *         with the id
	 */
	public Student getById(String id) {
		if (id == null) {
			throw new IllegalArgumentException(UI.NULL_ID);
		}

		return this.students.get(id);
	}

	/**
	 * Checks whether a student with the specified id exists
	 *
	 * @precondition id != null
	 * @postcondition none
	 * 
	 * @param id the id
	 * @return true, if the course contains the student with the specified id
	 */
	public boolean containsId(String id) {
		if (id == null) {
			throw new IllegalArgumentException(UI.NULL_ID);
		}

		return this.students.containsKey(id);
	}

	/**
	 * Removes the student with the specified id
	 *
	 * @precondition id != null
	 * @postcondition this.size() == this.size()@prev - 1
	 * 
	 * @param id the id
	 * @return true, if the student with the specified id has been removed
	 */
	public boolean removeById(String id) {
		if (id == null) {
			throw new IllegalArgumentException(UI.NULL_STUDENT);
		}

		return this.students.remove(id) != null;
	}

	/**
	 * Adds the student.
	 *
	 * @precondition student != null
	 * @postcondition this.size() == this.size()@prev + 1
	 * 
	 * @param false if the student could not
	 *              be added; true otherwise
	 */
	@Override
	public boolean add(Student student) {
		if (student == null) {
			throw new NullPointerException(UI.NULL_STUDENT);
		}

		return this.students.put(student.getId(), student) == null;
	}

	@Override
	public boolean contains(Object student) {
		if (student == null) {
			throw new NullPointerException(UI.NULL_STUDENT);
		}
		return this.students.containsValue(student);
	}

	@Override
	public boolean remove(Object student) {
		if (student == null) {
			throw new NullPointerException(UI.NULL_STUDENT);
		}
		return this.students.remove(((Student) student).getId()) != null;
		// or
		//return this.students.remove(((Student)student).getId(), student) ;
		

	}

	@Override
	public boolean isEmpty() {
		return this.students.isEmpty();
	}

	@Override
	public int size() {
		return this.students.size();
	}
	
	////////////////////////////////////////////////////////

	@Override
	public void clear() {
		this.students.clear();
	}

	@Override
	public boolean addAll(Collection<? extends Student> students) {
		if (students == null) {
			throw new NullPointerException(UI.NULL_COLLECTION);
		}

		if (students.contains(null)) {
			throw new NullPointerException(UI.COLLECTION_CONTAINS_NULL);
		}

		boolean changed = false;
		for (Student student : students) {
			if (this.add(student)) {
				changed = true;
			}
		}
		return changed;
	}

	@Override
	public boolean containsAll(Collection<?> students) {
		if (students == null) {
			throw new NullPointerException(UI.NULL_COLLECTION);
		}
		if (students.contains(null)) {
			throw new NullPointerException(UI.COLLECTION_CONTAINS_NULL);
		}

		
		for (Object student : students) {
			if (this.students.get(((Student)student).getId()) == null) {
				return false;
			}
		}
		return true;
	}

	@Override
	public Iterator<Student> iterator() {
		return this.students.values().iterator();
	}

	@Override
	public boolean removeAll(Collection<?> students) {
		if (students == null) {
			throw new NullPointerException(UI.NULL_COLLECTION);
		}
		if (students.contains(null)) {
			throw new NullPointerException(UI.COLLECTION_CONTAINS_NULL);
		}
		boolean changed = false;
		for(Object student: students) {
			if (this.remove(student)) {
				changed = true;
			}
		}
		return changed;
	}

	@Override
	public boolean retainAll(Collection<?> students) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Object[] toArray() {
		return this.students.values().toArray();
	}

	@Override
	public <T> T[] toArray(T[] students) {
		return this.students.values().toArray(students);
	}

	/**
	 * Gets the students that meet the filter requirement
	 * 
	 * @param filter to be applied to the students
	 * @return the collection of students meeting the filter
	 */
	public Collection<Student> filterBy(Predicate<Student> filter) {
		Collection<Student> filteredStudents = new ArrayList<Student>();
		for (Student currStudent : this.students.values()) {
			if (filter.test(currStudent)) {
				filteredStudents.add(currStudent);
			}
		}
		return filteredStudents;
	}
}
