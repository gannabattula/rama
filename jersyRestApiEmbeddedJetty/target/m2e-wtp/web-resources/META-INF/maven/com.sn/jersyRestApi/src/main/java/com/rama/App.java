package com.rama;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.context.support.XmlWebApplicationContext;

public class App {

	//https://nikolaygrozev.wordpress.com/2014/10/16/rest-with-embedded-jetty-and-jersey-in-a-single-jar-step-by-step/
	//https://www.jamesward.com/2012/08/13/containerless-spring-mvc
	//http://digitalsanctum.com/2012/11/24/how-to-embed-jetty-with-spring-mvc/
	private static final String CONFIG_LOCATION = "spring-jersey.xml";
	
	 public static void main(String[] args) throws Exception {

		 ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
	        context.setContextPath("/ordermgmt");
	        context.addEventListener(new ContextLoaderListener(getContext()));
	        //context.setInitParameter("contextClass", "spring-jersy.xml");
	        Server jettyServer = new Server(8080);
	        jettyServer.setHandler(context);
	 
	        ServletHolder jerseyServlet = context.addServlet(
	             org.glassfish.jersey.servlet.ServletContainer.class, "/*");
	        jerseyServlet.setInitOrder(0);
	 
	        // Tells the Jersey Servlet which REST service/class to load.
	        jerseyServlet.setInitParameter(
	           "jersey.config.server.provider.packages",
	           "com.rama");
	 
	        try {
	            jettyServer.start();
	            jettyServer.join();
	        } finally {
	            jettyServer.destroy();
	        }
	    }
	 
	 private static WebApplicationContext getContext() {
	        AnnotationConfigWebApplicationContext appContext = new AnnotationConfigWebApplicationContext();
	       ;
	        
	       // XmlWebApplicationContext appContext = new XmlWebApplicationContext();
	        //appContext.setConfigLocation("/WEB-INF/applicationContext.xml");
	       // String path = App.class.getResource(".").getPath() + CONFIG_LOCATION;
	      //  appContext.setConfigLocation(path);
	      //  context.getEnvironment().setDefaultProfiles(DEFAULT_PROFILE);
	        return appContext;
	    }
}
