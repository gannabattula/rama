package com.im.registration.persistance;

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
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.im.common.SqlReader;
import com.im.registration.entity.Registration;

@Repository
public class RegistrationPersistance {

	private static final Logger logger = LoggerFactory.getLogger(RegistrationPersistance.class);
	@Resource(name="namedParameterJdbcTemplate")
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	
	/*reg_insert = insert into registration(name) values(:name)		

			reg_select_all = select * from registration;

			reg_select_by_id = select * from registration where id=:id;*/

	
public Registration findRegistrationById(Integer id){
		
		String sql = SqlReader.getMessageByKey("reg_select_by_id");
		
		SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
		.addValue("id", id)
			
		;

		if(logger.isInfoEnabled()){
			logger.info("findRegistrationById : sql : " + sql);
		}
		
		Registration registration = null;
		try {
			registration = namedParameterJdbcTemplate.queryForObject(sql, sqlParameterSource, new RowMapper<Registration>(){
				
				public Registration mapRow(ResultSet rs, int rowNum) throws SQLException {
					
				
					Registration registration = new Registration();
					registration.setName(rs.getString("name"));
					registration.setId(rs.getInt("id"));
				   
					
					return registration;
				}
				
			});
		} catch (EmptyResultDataAccessException e) {
			logger.info("NO records found");
		}
		
		return registration;
	}
		


public Registration create(Registration registration){
	
	String sql = SqlReader.getMessageByKey("reg_insert");
	
	//values( :productId, :quoteprice)
	SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
	.addValue("name", registration.getName())
	;

	
	
	if(logger.isInfoEnabled()){
		logger.info("create : sql : " + sql);
	}
	KeyHolder keyHolder = new GeneratedKeyHolder();
	int update = namedParameterJdbcTemplate.update(sql, sqlParameterSource, keyHolder, new String[] { "id" });
	
	registration.setId(keyHolder.getKey().intValue());
	
	return registration;
}
	


public List<Registration> findAll(){
	
	String sql = SqlReader.getMessageByKey("reg_select_all");
	
	SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
		
	;

	if(logger.isInfoEnabled()){
		logger.info("findAll : sql : " + sql);
	}
	
	List<Registration> registration = null;
	try {
		registration = namedParameterJdbcTemplate.query(sql, sqlParameterSource, new RowMapper<Registration>(){
			
			public Registration mapRow(ResultSet rs, int rowNum) throws SQLException {
				
			
				Registration registration = new Registration();
				registration.setName(rs.getString("name"));
				registration.setId(rs.getInt("id"));
			   
				
				return registration;
			}
			
		});
	} catch (EmptyResultDataAccessException e) {
		logger.info("NO records found");
	}
	
	return registration;
}
	
	
}
