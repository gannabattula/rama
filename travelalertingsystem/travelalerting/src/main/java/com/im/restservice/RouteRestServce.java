package com.im.restservice;

import javax.annotation.Resource;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.im.framework.exceptions.AppException;
import com.im.framework.exceptions.ValidationException;
import com.im.framework.sdo.ResponseObject;
import com.im.model.route.entity.Route;
import com.im.model.route.service.RouteService;

@Component
@Path("/routes")
public class RouteRestServce extends AppRestService {
	private static final Logger logger = LoggerFactory.getLogger(RouteRestServce.class);
	@Resource
	RouteService routeService;
	
	
    

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public ResponseObject getAllRoutes() {
 
    	

		if(logger.isDebugEnabled()){
			logger.debug("getPrincingByProductId  start");
		}
		
		ResponseObject responseObject= null;
		try{
					
			//validation 
				
			//business
			Route allRoutes = routeService.getAllRoutes();
			
			//output
			responseObject= buildSuccessResponse(allRoutes, "read.success", "Routes");
		
		
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
    

    @GET
    //@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Path("test")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public BookType getBookInfo() {
 
        // retrieve book information based on the id supplied in the formal argument
 
        BookType bookType = new BookType();
        bookType.setBookId(2522);
        bookType.setBookName("rrrrr");
        bookType.setAuthor("");
        bookType.setCategory("sss");
        return bookType;
    }

 }
