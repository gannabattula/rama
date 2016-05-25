package com.rama.product.service;

import java.util.List;

import com.rama.product.entity.Product;

public interface ProductService {

	public List<Product> getProducts();

	List<Product> getByProductType(String productType);

	Product create(Product product);

	void delete(Integer productId);

	public Product getByProductId(Integer productId);

}
