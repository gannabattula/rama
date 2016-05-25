package com.rama.product.persistance;

import java.util.List;

import com.rama.product.entity.Product;

public interface ProductPersistance {

	List<Product> findAllProducts();

	List<Product> findByProductType(String productType);

	Product create(Product product);

	void delete(Integer productId);
	
	public Product findByProductId(Integer productId);

}
