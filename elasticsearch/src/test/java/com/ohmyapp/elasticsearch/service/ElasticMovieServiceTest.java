package com.ohmyapp.elasticsearch.service;

import com.ohmyapp.elasticsearch.entity.EntityMovie;
import org.junit.Test;

/**
 * Created by Emerald on 7/25/2016.
 *
 */
public class ElasticMovieServiceTest {
    @Test
    public void getByName() throws Exception {

    }

    @Test
    public void getByRatingInterval() throws Exception {

    }

    @Test
    public void addMovie() throws Exception {
        EntityMovie movie = new EntityMovie();
        movie.setName("Starwars");
    }

}