package com.im.model.student.persistance;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.im.framework.SqlReader;
import com.im.model.student.entity.Student;

@Repository("studentPersistance")
public class StudentPersistanceImpl implements StudentPersistance{

	
private static final Logger logger = LoggerFactory.getLogger(StudentPersistanceImpl.class);

	
	@Resource(name="namedParameterJdbcTemplate")
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	/* (non-Javadoc)
	 * @see com.im.model.student.persistance.StudentPersistance#findAll()
	 */
	public List<Student> findAll(){
		
		String sql = SqlReader.getMessageByKey("STUDENT_ALL");
		
		SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
		
			
		;

		if(logger.isInfoEnabled()){
			logger.info("findAll : sql : " + sql);
		}
		
		List<Student> students = null;
		try {
			students = namedParameterJdbcTemplate.query(sql, sqlParameterSource, new RowMapper<Student>(){
				
				public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
							
					
				Student student = new Student();
				student.setId(rs.getLong("id"));
				student.setFirstName(rs.getString("first_name"));
				student.setLastName(rs.getString("last_name"));
				student.setDob(rs.getDate("dob"));
				student.setParentId(rs.getLong("parent_id"));
				student.setEnrollementId(rs.getLong("enrollment_no"));
				student.setGender(rs.getString("gender"));
				student.setObservations(rs.getString("observation"));
				student.setStudentClass(rs.getInt("class"));
				student.setSection(rs.getString("section"));
				student.setContactNo(rs.getLong("contact_no"));
				student.setPickupPointId(rs.getLong("pickup_point_id"));
				
					
					
					
					return student;
				}
				
			});
		} catch (EmptyResultDataAccessException e) {
			logger.info("NO records found");
		}
		
		return students;
	}
		


	
public List<Student> findStudentsByPickupPointsId(long pickupPointId){
		
		String sql = SqlReader.getMessageByKey("STUDENTS_BY_PICKUP_POINT_ID");
		
		SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
		.addValue("pickupPointId", pickupPointId)
			
		;

		if(logger.isInfoEnabled()){
			logger.info("findStudentsByPickupPointsId : sql : " + sql);
		}
		
		List<Student> students = null;
		try {
			students = namedParameterJdbcTemplate.query(sql, sqlParameterSource, new RowMapper<Student>(){
				
				public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
							
						
				Student student = new Student();
				student.setId(rs.getLong("id"));
				student.setFirstName(rs.getString("first_name"));
				student.setLastName(rs.getString("last_name"));
				student.setDob(rs.getDate("dob"));
				student.setParentId(rs.getLong("parent_id"));
				student.setEnrollementId(rs.getLong("enrollment_no"));
				student.setGender(rs.getString("gender"));
				student.setObservations(rs.getString("observation"));
				student.setStudentClass(rs.getInt("class"));
				student.setSection(rs.getString("section"));
				student.setContactNo(rs.getLong("contact_no"));
				student.setPickupPointId(rs.getLong("pickup_point_id"));
				

					return student;
				}
				
			});
		} catch (EmptyResultDataAccessException e) {
			logger.info("NO records found");
		}
		
		return students;
	}
		



public Student findById(long id){
	
	String sql = SqlReader.getMessageByKey("STUDENT_BY_ID");
	
	SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
	.addValue("studentId", id)
	
		
	;

	if(logger.isInfoEnabled()){
		logger.info("findById : sql : " + sql);
	}
	
	Student student = null;
	try {
		student = namedParameterJdbcTemplate.queryForObject(sql, sqlParameterSource, new RowMapper<Student>(){
			
			public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
						
					
				Student student = new Student();
				student.setId(rs.getLong("id"));
				student.setFirstName(rs.getString("first_name"));
				student.setLastName(rs.getString("last_name"));
				student.setDob(rs.getDate("dob"));
				student.setParentId(rs.getLong("parent_id"));
				student.setEnrollementId(rs.getLong("enrollment_no"));
				student.setGender(rs.getString("gender"));
				student.setObservations(rs.getString("observation"));
				student.setStudentClass(rs.getInt("class"));
				student.setSection(rs.getString("section"));
				student.setContactNo(rs.getLong("contact_no"));
				student.setPickupPointId(rs.getLong("pickup_point_id"));
				
				return student;
			}
			
		});
	} catch (EmptyResultDataAccessException e) {
		logger.info("NO records found");
	}
	
	return student;
}
	

	
}
