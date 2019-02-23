package utils;


import java.net.URL;



import java.net.URLClassLoader;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
@EnableAutoConfiguration
public class PrintBeans {
    @Autowired
    ApplicationContext applicationContext;

    public void printBeans() {
        System.out.println(Arrays.asList(applicationContext.getBeanDefinitionNames()));
    }
    
    
    
    public void main(String [] args) {
    	
    	System.out.println("zee");
    	 ClassLoader cl = ClassLoader.getSystemClassLoader();

         URL[] urls = ((URLClassLoader)cl).getURLs();

         for(URL url: urls){
         	System.out.println(url.getFile());
    	}
}
    
}