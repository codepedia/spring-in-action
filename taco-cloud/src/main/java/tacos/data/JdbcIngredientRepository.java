
package tacos.data;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;
import tacos.Ingredient;
//https://www.youtube.com/watch?v=L6odfDSw4xA
@Repository/*By annotating JdbcIngredientRepository with
@Repository, you declare that it should be automatically discovered by Spring component
scanning and instantiated as a bean in the Spring application context.*/
public class JdbcIngredientRepository implements IngredientRepository{
  
  
  /*When Spring creates the JdbcIngredientRepository bean, it injects it with Jdbc-
Template via the @Autowired annotated construction. The constructor assigns
JdbcTemplate to an instance variable that will be used in other methods to query and
insert into the database. Speaking of those other methods, let’s take a look at the
implementations of findAll() and findById().*/
  private JdbcTemplate jdbc;
  
  
    //end::jdbcTemplate[]

    @Autowired
    public JdbcIngredientRepository(JdbcTemplate jdbc) {
      this.jdbc = jdbc;
    }
  //end::classShell[]

    //tag::finders[]
    @Override
    public Iterable<Ingredient> findAll() {
      return jdbc.query("select id, name, type from Ingredient",
          this::mapRowToIngredient);
    }

    // tag::findOne[]
    @Override
    public Ingredient findById(String id) {
      return jdbc.queryForObject(
          "select id, name, type from Ingredient where id=?",
          this::mapRowToIngredient, id);
    }
    
    // end::findOne[]
    
    //end::finders[]

    /*
    //tag::preJava8RowMapper[]
    @Override
    public Ingredient findOne(String id) {
      return jdbc.queryForObject(
          "select id, name, type from Ingredient where id=?",
          new RowMapper<Ingredient>() {
            public Ingredient mapRow(ResultSet rs, int rowNum) 
                throws SQLException {
              return new Ingredient(
                  rs.getString("id"), 
                  rs.getString("name"),
                  Ingredient.Type.valueOf(rs.getString("type")));
            };
          }, id);
    }
    //end::preJava8RowMapper[]
     */
  
    
    
    //tag::save[]
    @Override
    public Ingredient save(Ingredient ingredient) {
      jdbc.update(
          "insert into Ingredient (id, name, type) values (?, ?, ?)",
          ingredient.getId(), 
          ingredient.getName(),
          ingredient.getType().toString());
      return ingredient;
    }
    //end::save[]

    // tag::findOne[]
    //tag::finders[]
    private Ingredient mapRowToIngredient(ResultSet rs, int rowNum)
        throws SQLException {
      return new Ingredient(
          rs.getString("id"), 
          rs.getString("name"),
          Ingredient.Type.valueOf(rs.getString("type")));
    }
    //end::finders[]
    // end::findOne[]

	@Override
	public Ingredient findOne(String id) {
		// TODO Auto-generated method stub
		return null;
	}

    
    /*
  //tag::classShell[]

    ...
  //end::classShell[]
     */
  //tag::classShell[]

  }
  //end::classShell[]

  
