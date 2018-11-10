	package tacos.web;
	import java.util.Arrays;
	import java.util.List;
	import java.util.stream.Collectors;
	import javax.validation.Valid;
    import org.springframework.stereotype.Controller;
	import org.springframework.ui.Model;
	import org.springframework.validation.Errors;
	import org.springframework.web.bind.annotation.GetMapping;
	import org.springframework.web.bind.annotation.ModelAttribute;
	import org.springframework.web.bind.annotation.PostMapping;
	import org.springframework.web.bind.annotation.RequestMapping;
    import lombok.Data;
    import lombok.extern.slf4j.Slf4j;
	import tacos.Ingredient;
	import tacos.Ingredient.Type;
	import tacos.Taco;

	@Slf4j
	/* https://ww.slf4j.org/) Logger in the class. This modest annotation has
	 * private static final org.slf4j.Logger log =
	 * org.slf4j.LoggerFactory.getLogger(DesignTacoController.class); */
	@Controller
	/*Mapping annotation, when applied at the class level, specifies the kind of requests that
      this controller handles. In this case, it specifies that DesignTacoController will handle
      requests whose path begins with /design.*/
	
	//The lengthier @RequestMapping(method=RequestMethod.GET)
	@RequestMapping("/design")/*specifies that when an HTTP GET request is received for
    design, showDesignForm() will be called to handle the req*/
	public class DesignTacoController {

	//end::head[]
		
		
		
		public static String myfunc(String s) {
			
			System.out.println(s);
			
			return s;
			
			
		}
	@ModelAttribute
	public void addIngredientsToModel(Model model) {
		List<Ingredient> ingredients = Arrays.asList(
		  new Ingredient("FLTO", "Flour Tortilla", Type.WRAP),
		  new Ingredient("COTO", "Corn Tortilla", Type.WRAP),
		  new Ingredient("GRBF", "Ground Beef", Type.PROTEIN),
		  new Ingredient("CARN", "Carnitas", Type.PROTEIN),
		  new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES),
		  new Ingredient("LETC", "Lettuce", Type.VEGGIES),
		  new Ingredient("CHED", "Cheddar", Type.CHEESE),
		  new Ingredient("JACK", "Monterrey Jack", Type.CHEESE),
		  new Ingredient("SLSA", "Salsa", Type.SAUCE),
		  new Ingredient("SRCR", "Sour Cream", Type.SAUCE)
		);
		

		Type[] types = Ingredient.Type.values();
		for (Type type : types) {
			
			System.out.println("filterbyType used for :   " + "toStringed type" + type.toString() + "  <- vs ->" + type );		
		  model.addAttribute(type.toString().toLowerCase(),
		      filterByType(ingredients, type));
		  
		  
		  
		}
	}
		
	//tag::showDesignForm[]
	  @GetMapping
	  public String showDesignForm(Model model) {
		 	  
	//public class Taco {private String name;   private List<String> ingredients;}

	    model.addAttribute("design", new Taco()); // new instance of taco and 	                                              // a list of the ingredients to be added 
	    return "design";
	  }

	  // Takes an array list of ingredients created by the add ingrdnt methods 
	  // and filters the result by the type, using stream.
	  
	  //Class Object is the root of the class hierarchy. Every class has Object as a superclass. All objects, including arrays, implement the methods of this class.
	  private Object filterByType(List<Ingredient> ingredients, Type type) {
		   return ingredients.stream().filter(x -> x.getType().equals(type))
				                              .collect(Collectors.toList());
		}
	  
	  @PostMapping		  
	  public String processOrder(@Valid Taco design, Errors errors) {
	    if (errors.hasErrors()) {
		    return "orderForm";
		  }
		  // This should be of type Design and not String
		  System.out.println("Print Desgin vals   :" + design); //DEBUG: ERROR:Process Design:  null
		  log.info("Process Design:  " + design );
		  
		  /*"redirect:", indicating that this is a redirect view.
		   More specifically, it indicates that after processDesign() 
		   completes, the user’s browser should be redirected to the 
		   relative path /order/current.*/
		  
		  System.out.println("Procesing the Design object" + design);
		  
		  return "redirect:/orders/current";
	  }
	  
	  
	
	}
