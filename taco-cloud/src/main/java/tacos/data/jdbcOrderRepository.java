package tacos.data;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;


import tacos.Order;
import tacos.Taco;
//DAO 


/*Description: Parameter 0 of constructor in tacos.web.OrderController required a bean of type 'tacos.data.
 * OrderRepository' that could not be found.
 *Action:Consider defining a bean of type 'tacos.data.OrderRepository' in your configuration.
   ***SOLVED BY ADDING @SERVICE ANNOTAION*/

@Repository
public  class jdbcOrderRepository implements OrderRepository{
	
	
	/*rather than use the cumbersome Prepared-
	StatementCreator( as in the case of saving taco id to Taco_Ingredients...), 
	allow me to introduce you to SimpleJdbcInsert, an object that
	wraps JdbcTemplate to make it easier to insert data into a table.*/
	private SimpleJdbcInsert orderInserter;
	private SimpleJdbcInsert orderTacoInserter;
	private ObjectMapper objectMapper;
	private JdbcTemplate jdbc;/*ObjectMapper provides functionality for reading and writing JSON, 
	                          either to and from basic POJOs (Plain Old Java Objects),
	                          or to and from a general-purpose JSON Tree Model */
	
	public jdbcOrderRepository(JdbcTemplate jdbc) {
		   this.orderInserter    = new SimpleJdbcInsert(jdbc)
				                    .withTableName("Taco")
				                    .usingGeneratedKeyColumns("id");
		   
		   this.orderTacoInserter = new SimpleJdbcInsert(jdbc)
		   .withTableName("Taco_Order_Tacos");		   
		   this.objectMapper = objectMapper;
		      
		   /*JdbcOrderRepository is injected with JdbcTemplate
             through its constructor. But instead of assigning 
             JdbcTemplate directly to an instancevariable, the 
             constructor uses it to construct a couple of SimpleJdbcInsert instances.*/
	}
	
	
	
	
	
	/*create table if not exists Taco (
      id identity,
      name varchar(50) not null,
      createdAt timestamp not null
     );*/
	
	/*The save() method doesn’t actually save anything. It defines the flow for saving an
      Order and its associated Taco objects, and delegates the persistence work to save-OrderDetails()*/
	  @Override
	  public Order save(Order order) {
	    order.setPlacedAt(new Date());
	    
	    /*Saves the order details and returns the id so we can asscoiate it with the Taco*/
	    long orderId = saveOrderDetails(order);
	    order.setId(orderId);
	    List<Taco> tacos = order.getTacos();  /*Tacos getter form order class, addd list of tacos*/
	    for (Taco taco : tacos) {
	        saveTacoOrder(taco, orderId);
	    }

	    return order;
	  }
	

	  
	  
	  private long saveOrderDetails(Order order) {
		    /*It’s easy to create such a Map by copying the values from Order into entries of the
		    Map. But Order has several properties, and those properties all share the same name
		    with the columns that they’re going into. Because of that, in saveOrderDetails(),
		    I’ve decided to use Jackson’s ObjectMapper and its convertValue() method to convert
		    an Order into a Map.1 Once the Map is created, you’ll set the placedAt entry to the
		    value of the Order object’s placedAt property. This is necessary because Object-
		    Mapper would otherwise convert the Date property into a long, which is incompatible
		    with the placedAt field in the Taco_Order table.
		    With a Map full of order data ready, you can now call executeAndReturnKey() on
          orderInserter. This saves the order information to the Taco_Order table and returns
          the database-generated ID as a Number object, which a call to longValue() converts to
          a long returned from the method.*/
		    @SuppressWarnings("unchecked")
		    Map<String, Object> values =
		        objectMapper.convertValue(order, Map.class);	    
		    values.put("placedAt", order.getPlacedAt());

		    long orderId =
		        orderInserter
		            .executeAndReturnKey(values)
		            .longValue();
		    return orderId;
		  }
	
	  
	  /*SimpleJdbcInsert has a couple of useful methods for executing the insert:
        execute() and executeAndReturnKey(). Both accept a Map<String, Object>, where
        the map keys correspond to the column names in the table the data is inserted into*/
	
	/*create table if not exists Taco_Order_Tacos (
			tacoOrder bigint not null,
			taco bigint not null
		);*/
	
	  
	
	public void saveTacoOrder(Taco taco, long orderId) {
		/*The saveTacoToOrder() method is significantly simpler. Rather than use the
          ObjectMapper to convert an object to a Map, you create the Map and set the appropriate
          values. Once again, the map keys correspond to column names in the table. A simple
          call to the orderTacoInserter’s execute() method performs the insert.*/
		
		 Map<String, Object> values = new HashMap<>();
		    values.put("tacoOrder", orderId);
		    values.put("taco", taco.getId());
		    orderTacoInserter.execute(values);	
	}

	
}



/*Disclaimer: 
I’ll admit that this is a hackish use of ObjectMapper, but you already have Jackson in the classpath; Spring
Boot’s web starter brings it in. Also, using ObjectMapper to map an object into a Map is much easier than
copying each property from the object into the Map. Feel free to replace the use of ObjectMapper with any
code you prefer that builds the Map you’ll give to the inserter objects.	
*/