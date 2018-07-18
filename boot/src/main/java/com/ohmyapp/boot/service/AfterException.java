package com.ohmyapp.boot.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.ThrowsAdvice;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * Created by lip on 5/16/2017.
 * after exception is thrown
 */
@Component("exception")
public class AfterException implements ThrowsAdvice {
    private static final Logger LOGGER = LoggerFactory.getLogger(AfterException.class);

    public void afterThrowing(Method method, Object[] args, Object target, Exception ex) {
        LOGGER.debug("In target exception");
        LOGGER.debug("input {}", args[0]);
        LOGGER.debug("output {}", args[1]);
    }
}
