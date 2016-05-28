package com.im.model.student.service;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.im.framework.exceptions.ValidationException;
import com.im.model.pickuppoint.service.PickupPointServiceImpl;
import com.im.model.student.entity.Student;
import com.im.model.student.persistance.StudentPersistance;

@Service("studentService")
public class StudentServiceImpl implements StudentService {


	private static final Logger logger = LoggerFactory.getLogger(PickupPointServiceImpl.class);
	
	@Resource
	StudentPersistance studentPersistance;
	
	/* (non-Javadoc)
	 * @see com.im.model.student.service.StudentService#getAllStudents()
	 */
	public List<Student> getAllStudents() {
		if (logger.isDebugEnabled()) {
			logger.debug("getAllPickupPoints() - start ");
		}
 
		
		List<Student> findAll = studentPersistance.findAll();

        if (logger.isDebugEnabled()) {
			logger.debug("getAllPickupPoints() - end ");
		}
		return  findAll;


	}

	public List<Student> getStudentsByPickupPointsId(long pickupPointId) throws ValidationException {
		if (logger.isDebugEnabled()) {
			logger.debug("findStudentsByPickupPointsId() - start ");
		}
 

		HashMap<String, Object[]> hashMap = new HashMap<String,Object[]>();
		if(pickupPointId == 0 ){
			
			hashMap.put("value.required", new Object[]{"pickupPointId Id"});
		}
		

		if(!hashMap.isEmpty()){
			ValidationException valExc = new ValidationException(400,hashMap );
			throw valExc;
		}
		
		List<Student> findStudentsByPickupPointsId = studentPersistance.findStudentsByPickupPointsId(pickupPointId);
		
		if (logger.isDebugEnabled()) {
			logger.debug("findStudentsByPickupPointsId() - end ");
		}
		
		return findStudentsByPickupPointsId;

	}

	public Student getById(long id) throws ValidationException {

		if (logger.isDebugEnabled()) {
			logger.debug("findById() - start ");
		}
		
		HashMap<String, Object[]> hashMap = new HashMap<String,Object[]>();
		if(id == 0 ){
			
			hashMap.put("value.required", new Object[]{"Student Id"});
		}
		

		if(!hashMap.isEmpty()){
			ValidationException valExc = new ValidationException(400,hashMap );
			throw valExc;
		}
		
		Student findById = studentPersistance.findById(id);
		
		if (logger.isDebugEnabled()) {
			logger.debug("findById() - end ");
		}
		
		return findById;

	}

}
