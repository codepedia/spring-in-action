package tacos.web;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import lombok.Data;

@ConfigurationProperties(prefix="taco.orders")/*taco.orders, which means that when setting the pageSize property, you need to use a configuration property named
taco.orders.pageSize.*/
@Component /*Order props is also annotated with @Component so that Spring component scanning will automatically
discover it and create it as a bean in the Spring application context.*/
@Data
public class OrderProps {
	
   @Min(value=5, message="must be between 5 and 25")
   @Max(value=25, message="must be between 5 and 25")
   private int pageSize = 20;
  
}
//end::validated[]
