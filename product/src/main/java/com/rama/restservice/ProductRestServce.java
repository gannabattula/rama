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

import com.rama.product.entity.Product;
import com.rama.product.service.ProductService;

@Component
@Path("/products")
public class ProductRestServce {
	private static final Logger logger = LoggerFactory.getLogger(ProductRestServce.class);

	@Resource
	ProductService productService;
	

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Product> getProducts() {
 
        return productService.getProducts();
    }
    

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Product getPrincingByProductId(@PathParam("id") Integer productId) {
 
    	

		if(logger.isDebugEnabled()){
			logger.debug("getPrincingByProductId  start");
		}
	
					
			//validation 
				
			//business
			
			 Product product = productService.getByProductId(productId);
			
			
		
		
		if(logger.isDebugEnabled()){
			logger.debug("getPrincingByProductId end :  responseObject : " + product);
		}
		
		return product;
		
		
    }
    
    
    @GET
    @Path("search")
    //@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Product> findByProductType(@QueryParam("productType") String productType) {
    	//TODO do validationns
        return productService.getByProductType(productType);
    }
    
    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Product getCreate(Product product) {
 
        return productService.create(product);
    }
    @DELETE
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public String getDelete(@PathParam("id") Integer productId) {
 
        productService.delete(productId);
        
        return "Success";
    }
}
