package com.rama.pricing.service;

import java.util.HashMap;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.rama.framework.exceptions.AppException;
import com.rama.framework.exceptions.ValidationException;
import com.rama.pricing.entity.Pricing;
import com.rama.pricing.persistance.PricingPersistance;
import com.rama.restclient.product.Product;
import com.rama.restclient.product.ProductProxy;

@Component("pricingService")
public class PricingServiceImpl implements PricingService {

	private static final Logger logger = LoggerFactory.getLogger(PricingServiceImpl.class);
	
	@Resource
	PricingPersistance pricingPersistance;
	
	@Resource
	ProductProxy productProxy;
	
	public Pricing getPricingByProductId(Integer productId) throws ValidationException,AppException{
		if (logger.isDebugEnabled()) {
			logger.debug("getPricingByProductId() - start ");
		}
 
		
		HashMap<String, Object[]> hashMap = new HashMap<String,Object[]>();
		if(productId == null ){
			
			hashMap.put("value.required", new Object[]{"product Id"});
		}
		
		
		// call to get product object
		
		Product product = productProxy.getProductByProductId(productId);
		
		if (product == null){
			hashMap.put("not.found", new Object[]{"product"});
		}
		
		if(!hashMap.isEmpty()){
			ValidationException valExc = new ValidationException(400,hashMap );
			throw valExc;
		}
		
		Pricing pricings = pricingPersistance.findPrincingDetailsByProductId(productId);
		if (pricings != null){
			pricings.setProductName(product.getProductName());
			pricings.setProductType(product.getProductType());
		}
        if (logger.isDebugEnabled()) {
			logger.debug("getPricingByProductId() - end ");
		}
		return  pricings;
	}



	public Pricing create(Pricing pricing) throws ValidationException {
		// TODO follow the same loggers, business validattions.. for simpliciy just call directly
		
		// call to validate product object
		Product product = productProxy.getProductByProductId(pricing.getProductId());
		HashMap<String, Object[]> hashMap = new HashMap<String,Object[]>();
		
		if (product == null){
			hashMap.put("not.found", new Object[]{"product"});
		}
		
		if(!hashMap.isEmpty()){
			ValidationException valExc = new ValidationException(400,hashMap );
			throw valExc;
		}
		
		// check if product has already price details then update
		
		Pricing pricings = pricingPersistance.findPrincingDetailsByProductId(pricing.getProductId());
		
		if (pricings == null){
			// not exist create one
			return pricingPersistance.create(pricing);
		}else{
			// exist --generaly update ,,,but for now just return the same
			return pricings;
		}
		
	}

	public void delete(Integer productId) {
		// TODO follow the same loggers, business validattions.. for simpliciy just call directly
		 pricingPersistance.delete(productId);
		
	}
}
