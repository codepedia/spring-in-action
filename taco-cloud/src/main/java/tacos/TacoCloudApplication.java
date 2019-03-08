package tacos;


import java.io.IOException;
import java.io.InputStream;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.Properties;
import java.net.URL;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.thymeleaf.Thymeleaf;
import org.thymeleaf.spring5.view.ThymeleafView;

import com.jayway.jsonpath.internal.function.json.Append;

import tacos.Ingredient.Type;
import tacos.data.IngredientRepository;

@SpringBootApplication  /*This is a spring boot application*/
public class TacoCloudApplication {

	public static void main(String[] args) {
		
		SpringApplication.run(TacoCloudApplication.class, args);
		
		
		TacoCloudApplication TCA = new TacoCloudApplication();
		//TCA.printClassPaht();
		//TCA.printThemAll(); Fix later to read props
		
	}
		
		private void printThemAll() {

			Properties prop = new Properties();
			InputStream input = null;

			try {

				String filename = "config.properties";
				input = getClass().getClassLoader().getResourceAsStream(filename);
				if (input == null) {
					System.out.println("Sorry, unable to find " + filename);
					return;
				}

				prop.load(input);

				Enumeration<?> e = prop.propertyNames();
				while (e.hasMoreElements()) {
					String key = (String) e.nextElement();
					String value = prop.getProperty(key);
					System.out.println("Key : " + key + ", Value : " + value);
				}

			} catch (IOException ex) {
				ex.printStackTrace();
			} finally {
				if (input != null) {
					try {
						input.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}

		  }
		
	   private void printClassPaht() {	
	    ClassLoader cl = ClassLoader.getSystemClassLoader();

        URL[] urls = ((URLClassLoader)cl).getURLs();

        for(URL url: urls){
        	System.out.println("-----");
        	System.out.println(url.getFile());
        }
	   }
/*Moved all these to own class, development class*/	
//	/*To prevent the ingredient data from being loaded every time the application 
//	  starts in a production deployment, you could annotate the CommandLineRunner 
//	  bean method with @Profile'
//	  @Bean
//      @Profile("dev")
//      public CommandLineRunner*/
//	
//	  @Bean
//	  public CommandLineRunner dataLoader(IngredientRepository repo) {
//	    return new CommandLineRunner() {
//	      @Override
//	      public void run(String... args) throws Exception {
//	        repo.save(new Ingredient("FLTO", "Flour Tortilla", Type.WRAP));
//	        repo.save(new Ingredient("COTO", "Corn Tortilla", Type.WRAP));
//	        repo.save(new Ingredient("GRBF", "Ground Beef", Type.PROTEIN));
//	        repo.save(new Ingredient("CARN", "Carnitas", Type.PROTEIN));
//	        repo.save(new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES));
//	        repo.save(new Ingredient("LETC", "Lettuce", Type.VEGGIES));
//	        repo.save(new Ingredient("CHED", "Cheddar", Type.CHEESE));
//	        repo.save(new Ingredient("JACK", "Monterrey Jack", Type.CHEESE));
//	        repo.save(new Ingredient("SLSA", "Salsa", Type.SAUCE));
//	        repo.save(new Ingredient("SRCR", "Sour Cream", Type.SAUCE));
//	      }
//	    };
//	  }
//	
	
}
