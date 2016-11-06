package com.ohmyapp.boot;

import com.ohmyapp.boot.transaction.dao.RepoAddress;
import com.ohmyapp.boot.transaction.entity.EntityAddress;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

/**
 * author by lip on 12/5/2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@EnableAutoConfiguration
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {TestConfig.class})
public class ApplicationTest {
    @Autowired
    private ApplicationContext context;

    @Test
    public void testMain() throws Exception {
        RepoAddress addressRepo = context.getBean(RepoAddress.class);
        EntityAddress address = new EntityAddress();
        address.setId(1L);
        address.setLine1("1111 2nd street");
        address.setCity("My city");
        address.setState("CA");
        address.setZip("12345");
        addressRepo.save(address);
        //
        EntityAddress one = addressRepo.findOne(1L);
        Assert.assertEquals(address.getLine1(), one.getLine1());
        Assert.assertEquals(address.getCity(), one.getCity());
        Assert.assertEquals(address.getState(), one.getState());
        Assert.assertEquals(address.getZip(), one.getZip());
    }
}