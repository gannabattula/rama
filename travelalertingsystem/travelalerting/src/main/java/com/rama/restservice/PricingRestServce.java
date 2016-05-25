package com.rama.restservice;

import java.util.List;

import javax.annotation.Resource;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.rama.framework.exceptions.AppException;
import com.rama.framework.exceptions.ValidationException;
import com.rama.framework.sdo.ResponseObject;
import com.rama.pricing.entity.Pricing;
import com.rama.pricing.service.PricingService;

@Component
@Path("/pricings")
public class PricingRestServce extends AppRestService {
	private static final Logger logger = LoggerFactory.getLogger(PricingRestServce.class);
	@Resource
	PricingService productService;
	
	
    

    @GET
    @Path("query")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public ResponseObject getPrincingByProductId(@QueryParam("productId") Integer productId) {
 
    	

		if(logger.isDebugEnabled()){
			logger.debug("getPrincingByProductId  start");
		}
		
		ResponseObject responseObject= null;
		try{
					
			//validation 
				
			//business
			
			Pricing pricingByProductId = productService.getPricingByProductId(productId);
			
			//output
			responseObject= buildSuccessResponse(pricingByProductId, "read.success", "Pricing");
		
		
		} catch (ValidationException ve) {
			logger.error("Validation Error ", ve);
			responseObject = buildValidationErrorResponse(ve);
		}catch(AppException e){			
			responseObject = buildExceptionResponse(e, "get pricing");
		}catch(Exception e){
			responseObject = buildExceptionResponse(e, "get pricing");
		}
		
		if(logger.isDebugEnabled()){
			logger.debug("getPrincingByProductId end :  responseObject : " + responseObject);
		}
		
		return responseObject;
		
		
    }
    
   
    
    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public ResponseObject getCreate(Pricing product) {
    	

		if(logger.isDebugEnabled()){
			logger.debug("getPrincingByProductId  start");
		}
		
		ResponseObject responseObject= null;
		try{
					
			//validation 
				
			//business
			
			Pricing pricingByProductId = productService.create(product);
			
			//output
			responseObject= buildSuccessResponse(pricingByProductId, "create.success", "Pricing");
		
		
		} catch (ValidationException ve) {
			logger.error("Validation Error ", ve);
			responseObject = buildValidationErrorResponse(ve);
		}catch(Exception e){
			responseObject = buildExceptionResponse(e, "create pricing");
		}
		
		if(logger.isDebugEnabled()){
			logger.debug("getPrincingByProductId end :  responseObject : " + responseObject);
		}
		
		return responseObject;
		
    }
    @DELETE
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public String getDelete(@PathParam("id") Integer productId) {
    	// similar to first method model.. for simplicity ommitted alll others.
        productService.delete(productId);
        
        return "Success";
    }
}
