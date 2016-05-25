package com.rama;

import javax.annotation.Resource;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Component;

import com.rama.test.TestService;

@Component
@Path("/test")
public class TestRs {
	
	@Resource
	TestService testService;
	
    @GET
    //@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public BookType getBookInfo() {
 
        BookType bookType = testService.getDetails();
        return bookType;
    }

	

}
