package utils;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

public class PrintBeans {
    @Autowired
    ApplicationContext applicationContext;

    public void printBeans() {
        System.out.println(Arrays.asList(applicationContext.getBeanDefinitionNames()));
    }
    
    
    
    public void main(String [] args) {
    	
    
    	printBeans(); 
    	
    	
    }
}