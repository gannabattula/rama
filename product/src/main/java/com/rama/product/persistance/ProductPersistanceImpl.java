package com.rama.product.persistance;

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
import com.rama.product.entity.Product;

@Repository("productPersistance")
public class ProductPersistanceImpl implements ProductPersistance {

private static final Logger logger = LoggerFactory.getLogger(ProductPersistanceImpl.class);
	
	@Resource(name="namedParameterJdbcTemplate")
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	public List<Product> findAllProducts(){
		
		String sql = SqlReader.getMessageByKey("PRODUCT_SELECT_ALL");
		
		SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
			
		;

		if(logger.isInfoEnabled()){
			logger.info("findAll : sql : " + sql);
		}
		
		List<Product> products = null;
		try {
			products = namedParameterJdbcTemplate.query(sql, sqlParameterSource, new RowMapper<Product>(){
				
				public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
					
				
					Product product = new Product();
					product.setProductId(rs.getInt("product_id"));
					product.setProductName(rs.getString("product_name"));
					product.setProductType(rs.getString("product_type"));
					
					return product;
				}
				
			});
		} catch (EmptyResultDataAccessException e) {
			logger.info("NO records found");
		}
		
		return products;
	}
		
	
	
public Product findByProductId(Integer productId){
		
		String sql = SqlReader.getMessageByKey("PRODUCT_SELECT_BY_ID");
		
		SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
		.addValue("productId", productId)
			
		;

		if(logger.isInfoEnabled()){
			logger.info("findAll : sql : " + sql);
		}
		
		Product products = null;
		try {
			products = namedParameterJdbcTemplate.queryForObject(sql, sqlParameterSource, new RowMapper<Product>(){
				
				public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
					
				
					Product product = new Product();
					product.setProductId(rs.getInt("product_id"));
					product.setProductName(rs.getString("product_name"));
					product.setProductType(rs.getString("product_type"));
					
					return product;
				}
				
			});
		} catch (EmptyResultDataAccessException e) {
			logger.info("NO records found");
		}
		
		return products;
	}

public List<Product> findByProductType(String productType){
		
		String sql = SqlReader.getMessageByKey("PRODUCT_SELECT_BY_TYPE");
		
		SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
		.addValue("productType", productType)
		
			
		;

		if(logger.isInfoEnabled()){
			logger.info("findAll : sql : " + sql);
		}
		
		List<Product> products =null;
		try {
			products = namedParameterJdbcTemplate.query(sql, sqlParameterSource, new RowMapper<Product>(){
				
				public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
					
				
					Product product = new Product();
					product.setProductId(rs.getInt("product_id"));
					product.setProductName(rs.getString("product_name"));
					product.setProductType(rs.getString("product_type"));
					
					return product;
				}
				
			});
		} catch (EmptyResultDataAccessException e) {
			logger.info("No records found");
		}
		
		return products;
	}
		
	

public Product create(Product product){
	
	String sql = SqlReader.getMessageByKey("PRODUCT_CREATE");
	
	SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
	.addValue("productType", product.getProductType())
	.addValue("productName", product.getProductName())
	
		
	;

	if(logger.isInfoEnabled()){
		logger.info("findAll : sql : " + sql);
	}
	
	if(logger.isInfoEnabled()){
		logger.info("create : sql : " + sql);
	}
	KeyHolder keyHolder = new GeneratedKeyHolder();
	int update = namedParameterJdbcTemplate.update(sql, sqlParameterSource, keyHolder, new String[] { "product_id" });
	
	product.setProductId(keyHolder.getKey().intValue());
	
	return product;
}
	

public void delete(Integer productId)  {

	String sql = SqlReader.getMessageByKey("PRODUCT_DELETE");

	try {
		
		SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
														.addValue("productId", productId);
		
			
		if(logger.isInfoEnabled()){
			logger.info("update : sql : " + sql);
		}
		
		int update = namedParameterJdbcTemplate.update(sql, sqlParameterSource);
		
	} catch (EmptyResultDataAccessException ex) {
		logger.error("BusinessUnitVO update() - No data to soft delete");
	}
}
}
