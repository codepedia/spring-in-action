package tacos.data;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import com.fasterxml.jackson.databind.ObjectMapper;


import tacos.Order;
import tacos.Taco;
//DAO 
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
	    List<Taco> tacos = order.getTacos();
	    for (Taco taco : tacos) {
	        saveTacoOrder(taco, orderId);
	    }

	    return order;
	  }
	

	  private long saveOrderDetails(Order order) {
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
	
	
	/*create table if not exists Taco_Order_Tacos (
			tacoOrder bigint not null,
			taco bigint not null
		);*/
	
	
	public void saveTacoOrder(Taco taco, long orderId) {
		
		 Map<String, Object> values = new HashMap<>();
		    values.put("tacoOrder", orderId);
		    values.put("taco", taco.getId());
		    orderTacoInserter.execute(values);	
	}

	
}