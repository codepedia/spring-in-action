package tacos.data;

import java.sql.Timestamp;
import java.sql.Types;
import java.util.Arrays;
import java.util.Date;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import tacos.Ingredient;
import tacos.Taco;

public class JdbcTacoRepository implements TacoRepository {
	
	 private JdbcTemplate jdbc;
 
	public JdbcTacoRepository(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}
	
	/*Saving a taco design requires that you also save 
	  the ingredients associated with that taco to the Taco_Ingredients table.*/
	
	@Override
	public Taco save(Taco taco) {
        
		long tacoid = saveTacoInfo( taco);
		taco.setId(tacoid);
		for( Ingredient ingredient : taco.getIngredients()) {
	        saveIngredientToTaco(ingredient, tacoid);   
		}
		// TODO Auto-generated method stub
		return taco;
	}
	
	
	
	/*
	 * @Override
public Taco save(Taco taco) {
long tacoId = saveTacoInfo(taco);
taco.setId(tacoId);
for (Ingredient ingredient : taco.getIngredients()) {
saveIngredientToTaco(ingredient, tacoId);
}
return taco;
}
	 * 
	 * */
	
	public long saveTacoInfo(Taco taco) {
		taco.setCreatedAt(new Date());
		PreparedStatementCreator psc =
				new PreparedStatementCreatorFactory(
				"insert into Taco (name, createdAt) values (?, ?)",
				Types.VARCHAR, Types.TIMESTAMP
				).newPreparedStatementCreator(
				Arrays.asList(
				taco.getName(),
				new Timestamp(taco.getCreatedAt().getTime())));
		
		/*public interface KeyHolder -Interface for retrieving keys, typically used for auto-generated
		 *  keys as potentially returned by JDBC insert statements.Implementations of this interface 
		 *  can hold any number of keys. In the general case, the keys are returned as a List 
		 *  containing one Map for each row of keys. */
		
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbc.update(psc, keyHolder);
		return keyHolder.getKey().longValue();/*return the taco ID*/
	}
	
	
	/*method starts by calling the private saveTacoInfo()
	method, and then uses the taco ID returned from that method to call saveIngredient-
	ToTaco(), which saves each ingredient.*/
	
	public void saveIngredientToTaco(Ingredient ingredient ,long tacoid) {
		
		    jdbc.update("insert into Taco_Ingredients (taco, ingredient)" +
		    "values (? , ?)",
		    tacoid, ingredient.getId());	    		
	}

}