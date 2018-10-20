package tacos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication  /*This is a spring boot application*/
public class TacoCloudApplication {

	public static void main(String[] args) {
		
		SpringApplication.run(TacoCloudApplication.class, args);
		System.out.println(SpringApplication.class);
		
	}
}
