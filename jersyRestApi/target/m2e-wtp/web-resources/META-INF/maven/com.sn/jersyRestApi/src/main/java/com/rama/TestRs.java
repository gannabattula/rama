package com.rama;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Component;

@Component
@Path("/test")
public class TestRs {
	
	
	
    @GET
    //@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
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
