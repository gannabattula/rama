package com.rama.pricing.service;

import com.rama.framework.exceptions.AppException;
import com.rama.framework.exceptions.ValidationException;
import com.rama.pricing.entity.Pricing;

public interface PricingService {

	public Pricing getPricingByProductId(Integer productId) throws ValidationException, AppException;


	Pricing create(Pricing product) throws ValidationException;

	void delete(Integer productId);

}
