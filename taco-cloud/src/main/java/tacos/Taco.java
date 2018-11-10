package tacos;
import lombok.Data;
import java.util.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/*As you can see, Taco is a straightforward Java domain object with a couple of properties.
Like Ingredient, the Taco class is annotated with @Data to automatically generate
essential JavaBean methods for you at runtime.*/

@Data
public class Taco {
// end::allButValidation[]
@NotNull
@Size(min=5, message="Name must be at least 5 characters long")
// tag::allButValidation[]
private String name;
// end::allButValidation[]
@Size(min=1, message="You must choose at least 1 ingredient")
// tag::allButValidation[]
private List<String> ingredients; /**/


private Long id;
private Date createdAt;



}