package com.im.model.student.service;

import java.util.List;

import com.im.framework.exceptions.ValidationException;
import com.im.model.student.entity.Student;

// TODO: Auto-generated Javadoc
/**
 * The Interface StudentService.
 */
public interface StudentService {

	/**
	 * Gets the all students.
	 *
	 * @return the all students
	 */
	public List<Student> getAllStudents();
	
	/**
	 * Gets the students by pickup points id.
	 *
	 * @param pickupPointId the pickup point id
	 * @return the students by pickup points id
	 * @throws ValidationException the validation exception
	 */
	public List<Student> getStudentsByPickupPointsId(long pickupPointId) throws ValidationException;
	
	/**
	 * Gets the by id.
	 *
	 * @param id the id
	 * @return the by id
	 * @throws ValidationException the validation exception
	 */
	public Student getById(long id) throws ValidationException;
	
}
