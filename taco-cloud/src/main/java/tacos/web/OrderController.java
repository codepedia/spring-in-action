package tacos.web;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;

import lombok.extern.slf4j.Slf4j;
import tacos.Order;
import tacos.data.OrderRepository;


@Slf4j
@Controller
@RequestMapping("/orders")
/*@GetMapping is a composed annotation that acts as a shortcut 
for @RequestMapping(method = RequestMethod.GET).*/

/*The class-level @RequestMapping specifies that any request-handling methods in
this controller will handle requests whose path begins with /orders. When combined
with the method-level @GetMapping, it specifies that the orderForm() method will handle
HTTP GET requests for /orders/current.*/

public class OrderController {
	
 private OrderRepository orderRepo;
 
 
 public OrderController(OrderRepository orderRepo) {
	   this.orderRepo = orderRepo;
 }
 
	
@GetMapping("/current")
public String orderForm(Model model) {
model.addAttribute("order", new Order()); // creates a new instance of order and populate it with data

return "orderForm";
 }

@PostMapping
public String processOrder(@Valid Order order, Errors errors,
		SessionStatus sessionStatus) {
	if (errors.hasErrors()) {
	return "orderForm";
	}
	orderRepo.save(order);
	sessionStatus.setComplete();
	
	log.info("Order submitted : "  + order);
	System.out.println(order);
	
	return "redirect:/";
}
}
