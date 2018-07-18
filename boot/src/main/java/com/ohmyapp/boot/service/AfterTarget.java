package com.ohmyapp.boot.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * Created by lip on 5/16/2017.
 * after advice
 */
@Component("success")
public class AfterTarget implements AfterReturningAdvice {
    private static final Logger LOGGER = LoggerFactory.getLogger(AfterTarget.class);

    @Override
    public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
        LOGGER.debug("In after target");
        LOGGER.debug("input {}", args[0]);
        LOGGER.debug("output {}", args[1]);
    }
}
