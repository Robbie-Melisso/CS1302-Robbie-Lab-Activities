package edu.westga.cs1302.cms.model;

import java.util.Comparator;

/** sorts students in ascending order
 * @author rmeliss1
 * @version 1
 */
public class StudentAscendingGradeComparator implements Comparator<Student> {

	@Override
	public int compare(Student o1, Student o2) {
		// TODO Auto-generated method stub
		if (o1.getGrade() < o2.getGrade()) {
			return -1;
		} else if (o1.getGrade() > o2.getGrade()) {
			return 1;
		} else {
			return 0;
		}
	}

}
