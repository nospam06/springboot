package com.ohmyapp.elasticsearch;

import com.ohmyapp.elasticsearch.service.RunnerMovie;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * Created by Emerald on 7/25/2016.
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestConfig.class })
@ComponentScan
public class RunnerMovieTest {
    @Autowired
    RunnerMovie movie;
    @Test
    public void run() throws Exception {
        movie.run();
    }

}