package com.ohmyapp.workflow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
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
        getContext();
    }

    public static <T> T getBean(Class<T> tClass) {
        return getContext().getBean(tClass);
    }

    public static ApplicationContext getContext() {
        if (applicationContext == null) {
            applicationContext = SpringApplication.run(Application.class);
        }
        return applicationContext;
    }
}
