package tacos;
import lombok.Data;
import java.util.*;

/*As you can see, Taco is a straightforward Java domain object with a couple of properties.
Like Ingredient, the Taco class is annotated with @Data to automatically generate
essential JavaBean methods for you at runtime.*/

@Data
public class Taco {
private String name;
private List<String> ingredients;	
}