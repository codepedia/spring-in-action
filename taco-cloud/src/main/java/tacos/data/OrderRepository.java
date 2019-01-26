package tacos.data;


import tacos.Ingredient;
import tacos.Order;
import tacos.Taco;

import org.springframework.jdbc.core.JdbcTemplate;
import javax.sql.DataSource;

/*saving an order requires that you also save the tacos associated with the order to
the Taco_Order_Tacos table*/

public interface OrderRepository{	
      Order save(Order order);
      //Order getTacos(Taco design);
      //Need to fix the getTacos() method. It is used in the DAO class;

}