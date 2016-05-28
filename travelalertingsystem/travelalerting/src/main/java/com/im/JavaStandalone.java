package com.im;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.im.framework.exceptions.AppException;
import com.im.framework.exceptions.ValidationException;
import com.im.model.pickuppoint.entity.PickupPoint;
import com.im.model.pickuppoint.service.PickupPointService;
import com.im.model.route.entity.Route;
import com.im.model.route.service.RouteService;

public class JavaStandalone {

	private static final Logger logger = LoggerFactory.getLogger(JavaStandalone.class);

	  private JavaStandalone(){
		  
	  }
	public static void main(String[] arg) {
		/*
		 * Bootstrapping
		 * staring UI
		 */
		
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				"spring-jersey.xml");
		try {
			// bootstrapping the application 
			
			logger.info("Bootstrapping the application Initializing Spring context.");

			
			getRoutes(applicationContext);
			
			getRouteBydId(1, applicationContext);
			
			getPickupPointsByRouteId(1, applicationContext);
			
			
			
			
			
			// keep checking at intervals saying monitor is fine and processor is fine.
			
		} catch ( Exception e) {
			
			
			logger.error("Exception in agent while running and it is stopped", e);
		} finally{
			
			applicationContext = null;
		}
		
	}
	private static void getPickupPointsByRouteId(long routeId,
			ApplicationContext applicationContext) throws ValidationException {
		//Device Discovery
		logger.info("pickupPointService START");
		PickupPointService pickupPointService  = (PickupPointService) applicationContext
				.getBean("pickupPointService");
		 List<PickupPoint> pickupPoints = pickupPointService.getPickupPointsByRouteId(routeId);
		 for(PickupPoint pp : pickupPoints){
			 System.out.println("pick point name   : " +  pp.getPickupPointName());
		 }
		logger.info("pickupPointService END");
	}
	

	private static void getRoutes(ApplicationContext applicationContext) throws ValidationException, AppException {
		logger.info("getRoutes START");
		RouteService routeService  = (RouteService) applicationContext
				.getBean("routeService");
		List<Route> allRoutes = routeService.getAllRoutes();
		 for(Route route : allRoutes){
			 System.out.println("route name   : " +  route.getName());
		 }
	}
	
	
	private static void getRouteBydId(long routeId,
			ApplicationContext applicationContext) throws ValidationException, AppException {
		logger.info("getRouteBydId START");
		RouteService routeService  = (RouteService) applicationContext
						.getBean("routeService");
				Route routeById = routeService.getRouteById(routeId);
					 System.out.println("route name   : " +  routeById.getName());
	}
	
	
	
}
