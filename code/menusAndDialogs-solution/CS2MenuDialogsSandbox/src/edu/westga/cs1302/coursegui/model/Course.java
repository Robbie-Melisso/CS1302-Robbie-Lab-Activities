package edu.westga.cs1302.coursegui.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import edu.westga.cs1302.coursegui.resources.UI;

/**
 * The Class Course.
 * 
 * @author CS1302
 * @version 1.0
 */
public class Course implements Collection<Student> {

	public static final int INITIAL_COURSE_CAPACITY = 20;

	private String name;
	private final Map<String, Student> students;
	private int capacity;

	/**
	 * Instantiates a new course.
	 * 
	 * @precondition name != null
	 * @postcondition getName() == name && size() == 0
	 * 
	 * @param name
	 *            the name of the course
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
		this.capacity = INITIAL_COURSE_CAPACITY;
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
	 * Gets the capacity.
	 *
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the course capacity
	 */
	public int getCapacity() {
		return capacity;
	}

	/**
	 * Sets the capacity.
	 * 
	 * @precondition capacity >= 0
	 *
	 * @param capacity the course capacity to set
	 */
	public void setCapacity(int capacity) {
		if (capacity <= 0) {
			throw new IllegalArgumentException(UI.COURSE_CAPACITY_GREATER_THAN_OR_EQUAL_TO_ZERO);
		}
		
		this.capacity = capacity;
	}
	
	/**
	 * Gets the student with specified id.
	 *
	 * @precondition id != null
	 * @postcondition none
	 * 
	 * @param id
	 *            the id
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
	 * @param id 
	 *          the id
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
	 * @param id 
	 *          the id
	 * @return true, if the student with the specified id has been removed
	 */
	public boolean removeById(String id) {
		if (id == null) {
			throw new NullPointerException(UI.NULL_STUDENT);
		}
		
		return this.students.remove(id) != null;
	}
	
	/**
	 * Adds the student.
	 *
	 * @precondition student != null
	 * @postcondition this.size() == this.size()@prev + 1
	 * 
	 * @param false if the student is already contained in this course or could not
	 *              be added; true otherwise
	 */
	@Override
	public boolean add(Student student) {
		if (student == null) {
			throw new IllegalArgumentException(UI.NULL_STUDENT);
		}
		
		if (this.size() == this.capacity) {
			throw new CourseLimitExceededException(UI.COURSE_CAPACITY_EXCEEDED);
		}
		
		return this.students.put(student.getId(), student) == null;
	}

	@Override
	public boolean addAll(Collection<? extends Student> students) {
		for (Student student : students) {
			if (student == null) {
				throw new NullPointerException(UI.COLLECTION_CONTAINS_NULL);
			}
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
	public void clear() {
		this.students.clear();
	}

	@Override
	public boolean contains(Object student) {
		if (student == null) {
			throw new NullPointerException(UI.NULL_STUDENT);
		} 
		
		return this.students.containsKey(((Student) student).getId());
	}

	@Override
	public boolean containsAll(Collection<?> students) {
		for (Object student : students) {
			if (student == null) {
				throw new NullPointerException(UI.COLLECTION_CONTAINS_NULL);
			}
		}
		
		
		for (Object student : students) {
			if (!this.contains(student)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean isEmpty() {
		return this.students.isEmpty();
	}

	@Override
	public Iterator<Student> iterator() {
		return this.students.values().iterator();
	}

	@Override
	public boolean remove(Object student) {
		if (student == null) {
			throw new NullPointerException(UI.NULL_STUDENT);
		} 
		return this.students.remove(((Student) student).getId()) != null;
	}

	@Override
	public boolean removeAll(Collection<?> students) {
		for (Object student : students) {
			if (student == null) {
				throw new NullPointerException(UI.COLLECTION_CONTAINS_NULL);
			}
		}
		boolean changed = false;
		for (Object student : students) {
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
	public int size() {
		return this.students.size();
	}

	@Override
	public Object[] toArray() {
		return this.students.values().toArray();
	}

	@Override
	public <T> T[] toArray(T[] students) {
		return this.students.values().toArray(students);
	}
}
