/*Lombok to generate all of those missing methods as well as a constructor that accepts
all final properties as arguments.*/


package tacos;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
/*@NoArgsConstructor annotation at the class level. JPA requires that entities have a noarguments
constructor, so Lombok’s @NoArgsConstructor does that for you. You
don’t want to be able to use it, though, so you make it private by setting the access
attribute to AccessLevel.PRIVATE. And because there are final properties that must
be set, you also set the force attribute to true, which results in the Lombok-generated
constructor setting them to null.
You also add a @RequiredArgsConstructor. The @Data implicitly adds a required
arguments constructor, but when a @NoArgsConstructor is used, that constructor gets
removed. An explicit @RequiredArgsConstructor ensures that you’ll still have a
required arguments constructor in addition to the private no-arguments constructor.
Now let’s move on to the Taco class and see how to annotate it as a JPA entity.*/

@Data
@RequiredArgsConstructor /*Duplicate method Ingredient() in type Ingredient*/
@NoArgsConstructor(access=AccessLevel.PRIVATE, force=true)
@Entity
public class Ingredient {
	
	  
	@Id
	private final String id;
	private final String name;
	private final Type type; 
	
	public static enum Type { WRAP , PROTEIN , VEGGIES, CHEESE, SAUCE}

//		 Ingredient in = new Ingredient(); // test lomobk
//		 in.setId("zz"); 
//		 System.out.println(in.getId());	 

}