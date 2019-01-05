package tacos.web;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import lombok.extern.slf4j.Slf4j;
import tacos.Order;

@Slf4j
@Controller
@RequestMapping("/orders")
/*@GetMapping is a composed annotation that acts as a shortcut 
for @RequestMapping(method = RequestMethod.GET).*/
public class OrderController {
	
@GetMapping("/current")
public String orderForm(Model model) {
model.addAttribute("order", new Order());
return "orderForm";
 }

@PostMapping
public String processOrder(Order order) {	
	log.info("Order submitted : "  + order);
	System.out.println(order);
	return "redirect:/";
}
}
