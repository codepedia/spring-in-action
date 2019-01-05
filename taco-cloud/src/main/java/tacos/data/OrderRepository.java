package tacos.data;


import tacos.Order;
import org.springframework.jdbc.core.JdbcTemplate;
import javax.sql.DataSource;

/*saving an order requires that you also save the tacos associated with the order to
the Taco_Order_Tacos table*/

public interface OrderRepository{	
      Order save(Order order);
      Order getTacos(Order order);

}