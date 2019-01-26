package tacos;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.CreditCardNumber;
import javax.validation.constraints.NotBlank;

import lombok.Data;




@Data
public class Order {

	  //end::allButValidation[]
	  @NotBlank(message="Name is required")
	  //tag::allButValidation[]
	  private String name;
	  //end::allButValidation[]

	  @NotBlank(message="Street is required")
	  //tag::allButValidation[]
	  private String street;
	  //end::allButValidation[]

	  @NotBlank(message="City is required")
	  //tag::allButValidation[]
	  private String city;
	  //end::allButValidation[]

	  @NotBlank(message="State is required")
	  //tag::allButValidation[]
	  private String state;
	  //end::allButValidation[]

	  @NotBlank(message="Zip code is required")
	  //tag::allButValidation[]
	  private String zip;
	  //end::allButValidation[]

	  @CreditCardNumber(message="Not a valid credit card number")
	  //tag::allButValidation[]
	  private String ccNumber;
	  //end::allButValidation[]

	  @Pattern(regexp="^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$",
	           message="Must be formatted MM/YY")
	  //tag::allButValidation[]
	  private String ccExpiration;
	  //end::allButValidation[]

	  @Digits(integer=3, fraction=0, message="Invalid CVV")
	  //tag::allButValidation[]
	  private String ccCVV;
	  
	  private Long id;
	  private Date placedAt;// see below
	  private List<Taco> tacos = new ArrayList<>();
	  
	  public void addDesign(Taco design) {  /*Is this right?*/
		 this.tacos.add(design);  /*add designs for the tacos, list of tacos List<Taco>*/
	  }
	  
	  
	  
	  /*When persisting objects to a database, it’s generally a good idea to have one field that
       uniquely identifies the object. Your Ingredient class already has an id field, but you
       need to add id fields to both Taco and Order.
       Moreover, it might be useful to know when a     is created and when an Order is
       placed. You’ll also need to add a field to each object to capture the date and time that
       the objects are saved. The following listing shows the new id and createdAt fields
       needed in the Taco class.*/
}
