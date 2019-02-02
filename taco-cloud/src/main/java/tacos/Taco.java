package tacos;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Entity
public class Taco {
// end::allButValidation[]

/*Because you’re relying on the database to automatically
generate the ID value, you also annotate the id property with @GeneratedValue, specifying
a strategy of AUTO.*/	
@Id
@GeneratedValue(strategy=GenerationType.AUTO)
private Long id;


@NotNull
@Size(min=5, message="Name must be at least 5 characters long")
// tag::allButValidation[]
private String name;
// end::allButValidation[]
@Size(min=1, message="You must choose at least 1 ingredient")
// tag::allButValidation[]

@ManyToMany(targetEntity=Ingredient.class)
private List<Ingredient> ingredients; /*Changing the Ingredients t type ingredient fixed issues in srvc class*/


private Date createdAt;





}