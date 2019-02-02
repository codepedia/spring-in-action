/*Lombok to generate all of those missing methods as well as a constructor that accepts
all final properties as arguments.*/


package tacos;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@Entity
public class Ingredient {
	
	  
	@Id
	private String id;
	private String name;
	private Type type; 
	
	public static enum Type { WRAP , PROTEIN , VEGGIES, CHEESE, SAUCE}

//		 Ingredient in = new Ingredient(); // test lomobk
//		 in.setId("zz"); 
//		 System.out.println(in.getId());	 

}