package com.ohmyapp.boot.service;

import com.ohmyapp.boot.AutoConfig;
import com.ohmyapp.boot.api.MyProxy;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.util.ArrayList;

/**
 * Created by lip on 5/16/2017.
 * test
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {AutoConfig.class})
public class MyProxyTest {
    @Autowired
    private ApplicationContext context;

    @Test
    public void doSomething() throws Exception {
        MyProxy proxy = (MyProxy) context.getBean("proxy1");
        ArrayList<String> input = new ArrayList<>();
        ArrayList<String> output = new ArrayList<>();
        proxy.doSomething(input, output);
        // do again
        proxy.doSomething(input, output);
    }
}