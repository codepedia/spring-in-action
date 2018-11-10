package utils;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

public class PrintBeans {
    @Autowired
    ApplicationContext applicationContext;

       // System.out.println(Arrays.asList(applicationContext.getBeanDefinitionNames()));
}

