package com.ohmyapp.elasticsearch;

import com.ohmyapp.elasticsearch.dao.service.RunnerMovie;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

/**
 * Created by Emerald on 7/25/2016.
 * test
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {TestConfig.class})
public class RunnerMovieTest {
    @Autowired
    private RunnerMovie movie;

    @Test
    public void run() throws Exception {
        movie.run();
    }

}