package com.ohmyapp.boot.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * Created by lip on 5/16/2017.
 * proxy before target
 */
@Component("before")
public class BeforeTarget implements MethodBeforeAdvice {
    private static final Logger LOGGER = LoggerFactory.getLogger(BeforeTarget.class);

    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        LOGGER.debug("In before target");
        LOGGER.debug("input {}", args[0]);
        LOGGER.debug("output {}", args[1]);
    }
}
