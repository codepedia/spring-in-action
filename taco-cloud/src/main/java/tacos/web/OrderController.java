package tacos.web;
import javax.validation.Valid;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import lombok.extern.slf4j.Slf4j;
import tacos.Order;
import tacos.User;
import tacos.data.OrderRepository;


@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("order")
/*@GetMapping is a composed annotation that acts as a shortcut 
for @RequestMapping(method = RequestMethod.GET).*/

/*The class-level @RequestMapping specifies that any request-handling methods in
this controller will handle requests whose path begins with /orders. When combined
with the method-level @GetMapping, it specifies that the orderForm() method will handle
HTTP GET requests for /orders/current.*/
public class OrderController {
	
 private OrderRepository orderRepo;
 private OrderProps props;

 public OrderController(OrderRepository orderRepo, OrderProps props) {
	   this.orderRepo = orderRepo;
	   this.props     = props;
 }
 
	
@GetMapping("/current")
public String orderForm(@AuthenticationPrincipal User user, 
	      @ModelAttribute Order order) {
	    if (order.getDeliveryName() == null) {
	      order.setDeliveryName(user.getFullname());
	    }
	    if (order.getDeliveryStreet() == null) {
	      order.setDeliveryStreet(user.getStreet());
	    }
	    if (order.getDeliveryCity() == null) {
	      order.setDeliveryCity(user.getCity());
	    }
	    if (order.getDeliveryState() == null) {
	      order.setDeliveryState(user.getState());
	    }
	    if (order.getDeliveryZip() == null) {
	      order.setDeliveryZip(user.getZip());
	    }
	    
 return "orderForm";
 }

@PostMapping
public String processOrder(@Valid Order order, Errors errors,
		SessionStatus sessionStatus, @AuthenticationPrincipal User user) {
	if (errors.hasErrors()) {
	return "orderForm";
	}
	
	order.setUser(user);
	orderRepo.save(order);
	sessionStatus.setComplete();
	
	log.info("Order submitted : "  + order);
	System.out.println(order);
	return "redirect:/";
}

public String OrdersForUser(@AuthenticationPrincipal User user, Model model ) {

	Pageable pageable = PageRequest.of(0, props.getPageSize());
    model.addAttribute("order", orderRepo.findByUserOrderByPlacedAtDesc(user, pageable));
    
	return "OrderList";

}
}
