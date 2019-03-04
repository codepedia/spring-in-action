package tacos.data;
import tacos.Order;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import tacos.User;

public interface UserRepository extends CrudRepository<User, Long> {
	
	User findByUsername(String username); //Of type String.
}
