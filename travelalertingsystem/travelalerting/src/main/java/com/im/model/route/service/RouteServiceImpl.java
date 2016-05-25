package com.im.model.route.service;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.im.framework.exceptions.AppException;
import com.im.framework.exceptions.ValidationException;
import com.im.model.route.entity.Route;
import com.im.model.route.persistance.RoutePersistance;

@Component("routeService")
public class RouteServiceImpl implements RouteService {

	private static final Logger logger = LoggerFactory.getLogger(RouteServiceImpl.class);
	
	@Resource
	RoutePersistance routePersistance;

	
	public Route getAllRoutes() throws ValidationException,AppException{
		if (logger.isDebugEnabled()) {
			logger.debug("getPricingByProductId() - start ");
		}
 
	/*	
		HashMap<String, Object[]> hashMap = new HashMap<String,Object[]>();
		if(productId == null ){
			
			hashMap.put("value.required", new Object[]{"product Id"});
		}
		*/
		
		Route findAll = routePersistance.findAll();

        if (logger.isDebugEnabled()) {
			logger.debug("getPricingByProductId() - end ");
		}
		return  findAll;
	}



	
}
