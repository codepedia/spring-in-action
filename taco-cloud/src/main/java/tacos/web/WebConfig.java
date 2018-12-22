package tacos.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/*when a controller is
simple enough that it doesn’t populate a model or process input—as is the case with
your HomeController—there’s another way that you can define the controller. Have a
look at the next listing to see how you can declare a view controller—a controller that
does nothing but forward the request to a view.*/


@Configuration //@Configuration classes are typically bootstrapped using either AnnotationConfigApplicationContext or its web-capable variant
public class WebConfig implements WebMvcConfigurer {
	/*prefer creating a new configuration class for each kind of configuration (web, data, security, and so
      on), keeping the application bootstrap configuration clean and simple.*/
	
	//https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/servlet/config/annotation/WebMvcConfigurer.html
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		
		/*The addViewControllers() method is given a ViewControllerRegistry that you
          can use to register one or more view controllers. Here, you call addViewController()
          on the registry, passing in "/", which is the path for which your view controller will
          handle GET requests.That method returns a ViewControllerRegistration object,
          on which you immediately call setViewName() to specify home as the view that a
          request for "/" should be forwarded to. And just like that, you’ve been able to replace*/
		
		  /*We could have added the same view controller declaration to the 
		   * bootstrap TacoCloudApplication*/
		
	      registry.addViewController("/").setViewName("home");
	}

}



/*Deleted the below home controller and implemented Webconf....*/
/*public class HomeController {	@GetMapping("/") public String home() {return "home";} }*/
