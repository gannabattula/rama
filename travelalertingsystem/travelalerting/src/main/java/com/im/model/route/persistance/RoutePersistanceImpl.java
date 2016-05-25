package com.im.model.route.persistance;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;

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
import com.im.model.route.entity.Route;

@Repository("routePersistance")
public class RoutePersistanceImpl implements RoutePersistance {

private static final Logger logger = LoggerFactory.getLogger(RoutePersistanceImpl.class);
	
	@Resource(name="namedParameterJdbcTemplate")
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	public Route findAll(){
		
		String sql = SqlReader.getMessageByKey("ROUTE_SELECT_BY_ALL");
		
		SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
		
			
		;

		if(logger.isInfoEnabled()){
			logger.info("findAll : sql : " + sql);
		}
		
		Route routes = null;
		try {
			routes = namedParameterJdbcTemplate.queryForObject(sql, sqlParameterSource, new RowMapper<Route>(){
				
				public Route mapRow(ResultSet rs, int rowNum) throws SQLException {
					
				
					/* `id` bigint(20) NOT NULL,
					  `route_type` varchar(45) DEFAULT NULL,
					  `name` varchar(45) DEFAULT NULL,
					  `start_latitude` decimal(10,2) DEFAULT NULL,
					  `start_longitude` decimal(10,2) DEFAULT NULL,
					  `end_latitude` decimal(10,2) DEFAULT NULL,
					  `end_longitude` decimal(10,2) DEFAULT NULL,
					 -- `vehicle_id` bigint(20) DEFAULT NULL,
					  PRIMARY KEY (`id`)*/
					  
					Route route = new Route();
					route.setRouteId(rs.getLong("id"));
					route.setRouteType(rs.getString("route_type"));
					route.setName(rs.getString("name"));
					route.setStartlatitude(rs.getDouble("start_latitude"));
					route.setStartLongitude(rs.getDouble("start_longitude"));
					route.setEndLatitude(rs.getDouble("end_latitude"));
					route.setEndLongitude(rs.getDouble("end_longitude"));
					
					
					
					return route;
				}
				
			});
		} catch (EmptyResultDataAccessException e) {
			logger.info("NO records found");
		}
		
		return routes;
	}
		


public Route create(Route pricing){
	
	String sql = SqlReader.getMessageByKey("PRICING_CREATE");
	
	//values( :productId, :quoteprice)
	SqlParameterSource sqlParameterSource = new MapSqlParameterSource()

	;

	
	
	if(logger.isInfoEnabled()){
		logger.info("create : sql : " + sql);
	}
	KeyHolder keyHolder = new GeneratedKeyHolder();
	int update = namedParameterJdbcTemplate.update(sql, sqlParameterSource, keyHolder, new String[] { "id" });
	
	pricing.setRouteId(keyHolder.getKey().longValue());
	
	return pricing;
}
	

}
