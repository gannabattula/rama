package com.rama.pricing.persistance;

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

import com.rama.framework.SqlReader;
import com.rama.pricing.entity.Pricing;

@Repository("pricingPersistance")
public class PricingPersistanceImpl implements PricingPersistance {

private static final Logger logger = LoggerFactory.getLogger(PricingPersistanceImpl.class);
	
	@Resource(name="namedParameterJdbcTemplate")
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	public Pricing findPrincingDetailsByProductId(Integer productId){
		
		String sql = SqlReader.getMessageByKey("PRICING_SELECT_BY_PRODUCTID");
		
		SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
		.addValue("productId", productId)
			
		;

		if(logger.isInfoEnabled()){
			logger.info("findAll : sql : " + sql);
		}
		
		Pricing product = null;
		try {
			product = namedParameterJdbcTemplate.queryForObject(sql, sqlParameterSource, new RowMapper<Pricing>(){
				
				public Pricing mapRow(ResultSet rs, int rowNum) throws SQLException {
					
				
					Pricing product = new Pricing();
					product.setPricingId(rs.getInt("pricing_id"));
					product.setProductId(rs.getInt("product_id"));
					product.setPrice(rs.getDouble("quoteprice"));
				   
					
					return product;
				}
				
			});
		} catch (EmptyResultDataAccessException e) {
			logger.info("NO records found");
		}
		
		return product;
	}
		


public Pricing create(Pricing pricing){
	
	String sql = SqlReader.getMessageByKey("PRICING_CREATE");
	
	//values( :productId, :quoteprice)
	SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
	.addValue("productId", pricing.getProductId())
	.addValue("quoteprice", pricing.getPrice())
	;

	
	
	if(logger.isInfoEnabled()){
		logger.info("create : sql : " + sql);
	}
	KeyHolder keyHolder = new GeneratedKeyHolder();
	int update = namedParameterJdbcTemplate.update(sql, sqlParameterSource, keyHolder, new String[] { "pricing_id" });
	
	pricing.setPricingId(keyHolder.getKey().intValue());
	
	return pricing;
}
	

public void delete(Integer pricingId)  {

	String sql = SqlReader.getMessageByKey("PRICING_DELETE");

	try {
		
		SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
														.addValue("pricingId", pricingId);
		
			
		if(logger.isInfoEnabled()){
			logger.info("delete : sql : " + sql);
		}
		
		int update = namedParameterJdbcTemplate.update(sql, sqlParameterSource);
		
	} catch (EmptyResultDataAccessException ex) {
		logger.error("BusinessUnitVO update() - No data to soft delete");
	}
}
}
