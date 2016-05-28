package com.im.model.student.persistance;

import java.util.List;

import com.im.model.student.entity.Student;

// TODO: Auto-generated Javadoc
/**
 * The Interface StudentPersistance.
 */
public interface StudentPersistance {

	/**
	 * Find all.
	 *
	 * @return the list
	 */
	public List<Student> findAll();
	
	/**
	 * Find students by pickup points id.
	 *
	 * @param pickupPointId the pickup point id
	 * @return the list
	 */
	public List<Student> findStudentsByPickupPointsId(long pickupPointId);
	
	/**
	 * Find by id.
	 *
	 * @param id the id
	 * @return the student
	 */
	public Student findById(long id);
}
