	package tacos.web;

	import java.util.Arrays;
	import java.util.List;
	import java.util.stream.Collectors;

	import javax.validation.Valid;

import org.junit.Before;
import org.springframework.stereotype.Controller;
	import org.springframework.ui.Model;
	import org.springframework.validation.Errors;
	import org.springframework.web.bind.annotation.GetMapping;
	import org.springframework.web.bind.annotation.ModelAttribute;
	import org.springframework.web.bind.annotation.PostMapping;
	import org.springframework.web.bind.annotation.RequestMapping;

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
		  model.addAttribute(type.toString().toLowerCase(),
		      filterByType(ingredients, type));
		  
		  
		  
		}
	}
	
	//tag::showDesignForm[]
	  @GetMapping
	  public String showDesignForm(Model model) {
	    model.addAttribute("design", new Taco());
	    return "design";
	  }
	  
	  
	  // Takes an array list of ingredients created by the add ingrdnt methods 
	  // and filters the result by the type, usng stream.
	  private Object filterByType(List<Ingredient> ingredients, Type type) {
			// TODO Auto-generated method stub
		   return ingredients.stream().filter(x -> x.getType().equals(type))
				                              .collect(Collectors.toList());
		}
	  
	
	}
