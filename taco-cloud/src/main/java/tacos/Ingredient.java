/*Lombok to generate all of those missing methods as well as a constructor that accepts
all final properties as arguments.*/

package tacos;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import tacos.Ingredient.Type;
@Data
@RequiredArgsConstructor
@Accessors(chain = true)    // z
@AllArgsConstructor         //z
public class Ingredient {
	
	private String id;
	private String name;
	private Type type; 
	
	public static enum Type { WRAP , PROTEIN , VEGGIES, CHEESE, SAUCE}

//		 Ingredient in = new Ingredient(); // test lomobk
//		 in.setId("zz"); 
//		 System.out.println(in.getId());	 

}