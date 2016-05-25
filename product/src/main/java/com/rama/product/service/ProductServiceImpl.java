package com.rama.product.service;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.rama.product.entity.Product;
import com.rama.product.persistance.ProductPersistance;

@Component("productService")
public class ProductServiceImpl implements ProductService {

	private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);
	
	@Resource
	ProductPersistance productPersistance;
	
	
	public List<Product> getProducts(){
		if (logger.isDebugEnabled()) {
			logger.debug("getBooks() - start ");
		}
 
		List<Product> products = productPersistance.findAllProducts();
        
        if (logger.isDebugEnabled()) {
			logger.debug("getBooks() - end ");
		}
		return  products;
	}

	public List<Product> getByProductType(String productType) {
		// TODO follow the same loggers, business validattions.. for simpliciy just call directly
		return productPersistance.findByProductType(productType);
	}

	public Product create(Product product) {
		// TODO follow the same loggers, business validattions.. for simpliciy just call directly
				return productPersistance.create(product);
	}

	public void delete(Integer productId) {
		// TODO follow the same loggers, business validattions.. for simpliciy just call directly
		 productPersistance.delete(productId);
		
	}

	public Product getByProductId(Integer productId) {
		// TODO Auto-generated method stub
		return productPersistance.findByProductId(productId);
	}
}
