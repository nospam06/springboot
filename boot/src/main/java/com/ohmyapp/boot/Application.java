package com.ohmyapp.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ImportResource;

/**
 * author by lip on 12/5/2015.
 */
@SpringBootApplication
@ImportResource("classpath:activiti.cfg.xml")
public class Application {
    private static ConfigurableApplicationContext applicationContext;
    public static void main(String[] args) {
         applicationContext = SpringApplication.run(Application.class);
    }

    public static <T> T getBean(Class<T> tClass) {
        if (applicationContext == null) {
            applicationContext = SpringApplication.run(Application.class);
        }
        return applicationContext.getBean(tClass);
    }
}
