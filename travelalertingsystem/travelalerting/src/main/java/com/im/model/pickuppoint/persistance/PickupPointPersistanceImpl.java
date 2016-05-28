package com.im.model.pickuppoint.persistance;

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

import com.im.framework.SqlReader;
import com.im.framework.exceptions.AppException;
import com.im.model.pickuppoint.entity.PickupPoint;
import com.im.model.pickuppoint.persistance.PickupPointPersistance;
import com.im.model.route.entity.Route;
import com.im.model.route.persistance.RoutePersistanceImpl;

@Repository("pickupPointPersistance")
public class PickupPointPersistanceImpl implements PickupPointPersistance {
 
	private static final Logger logger = LoggerFactory.getLogger(RoutePersistanceImpl.class);

	
	@Resource(name="namedParameterJdbcTemplate")
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	public List<PickupPoint> findAll(){
		
		String sql = SqlReader.getMessageByKey("PICKUP_POINT_ALL");
		
		SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
		
			
		;

		if(logger.isInfoEnabled()){
			logger.info("findAll : sql : " + sql);
		}
		
		List<PickupPoint> pickupPoints = null;
		try {
			pickupPoints = namedParameterJdbcTemplate.query(sql, sqlParameterSource, new RowMapper<PickupPoint>(){
				
				public PickupPoint mapRow(ResultSet rs, int rowNum) throws SQLException {
							
						
					/*id bigint(20) PK 
					latitude decimal(10,2) 
					longitude decimal(10,2) 
					vehicle_id bigint(20) 
					student_id bigint(20) 
					pickup_point_name varchar(45)
					*/
					
					PickupPoint pickupPoint = new PickupPoint();
					pickupPoint.setPickupPointId(rs.getLong("id"));
					pickupPoint.setRouteId(rs.getLong("route_id"));
					pickupPoint.setLatitude(rs.getDouble("latitude"));
					pickupPoint.setLongitude(rs.getDouble("longitude"));
					pickupPoint.setPickupPointName(rs.getString("pickup_point_name"));
					
					
					return pickupPoint;
				}
				
			});
		} catch (EmptyResultDataAccessException e) {
			logger.info("NO records found");
		}
		
		return pickupPoints;
	}
		


	
public List<PickupPoint> findPickupPointsByRouteId(long routeId){
		
		String sql = SqlReader.getMessageByKey("PICKUP_POINT_BY_ROUTE_ID");
		
		SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
		.addValue("routeId", routeId)
		
			
		;

		if(logger.isInfoEnabled()){
			logger.info("findPickupPointsByRouteId : sql : " + sql);
		}
		
		List<PickupPoint> pickupPoints = null;
		try {
			pickupPoints = namedParameterJdbcTemplate.query(sql, sqlParameterSource, new RowMapper<PickupPoint>(){
				
				public PickupPoint mapRow(ResultSet rs, int rowNum) throws SQLException {
							
						
					/*id bigint(20) PK 
					latitude decimal(10,2) 
					longitude decimal(10,2) 
					vehicle_id bigint(20) 
					student_id bigint(20) 
					pickup_point_name varchar(45)
					*/
					
					PickupPoint pickupPoint = new PickupPoint();
					pickupPoint.setPickupPointId(rs.getLong("id"));
					pickupPoint.setRouteId(rs.getLong("route_id"));
					pickupPoint.setLatitude(rs.getDouble("latitude"));
					pickupPoint.setLongitude(rs.getDouble("longitude"));
					pickupPoint.setPickupPointName(rs.getString("pickup_point_name"));
					
					
					return pickupPoint;
				}
				
			});
		} catch (EmptyResultDataAccessException e) {
			logger.info("NO records found");
		}
		
		return pickupPoints;
	}
		



public PickupPoint findById(long id){
	
	String sql = SqlReader.getMessageByKey("PICKUP_POINT_BY_ID");
	
	SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
	.addValue("pickupPointId", id)
	
		
	;

	if(logger.isInfoEnabled()){
		logger.info("findById : sql : " + sql);
	}
	
	PickupPoint pickupPoint = null;
	try {
		pickupPoint = namedParameterJdbcTemplate.queryForObject(sql, sqlParameterSource, new RowMapper<PickupPoint>(){
			
			public PickupPoint mapRow(ResultSet rs, int rowNum) throws SQLException {
						
					
				/*id bigint(20) PK 
				latitude decimal(10,2) 
				longitude decimal(10,2) 
				vehicle_id bigint(20) 
				student_id bigint(20) 
				pickup_point_name varchar(45)
				*/
				
				PickupPoint pickupPoint = new PickupPoint();
				pickupPoint.setPickupPointId(rs.getLong("id"));
				pickupPoint.setRouteId(rs.getLong("route_id"));
				pickupPoint.setLatitude(rs.getDouble("latitude"));
				pickupPoint.setLongitude(rs.getDouble("longitude"));
				pickupPoint.setPickupPointName(rs.getString("pickup_point_name"));
				
				
				return pickupPoint;
			}
			
		});
	} catch (EmptyResultDataAccessException e) {
		logger.info("NO records found");
	}
	
	return pickupPoint;
}
	


	
	
}
