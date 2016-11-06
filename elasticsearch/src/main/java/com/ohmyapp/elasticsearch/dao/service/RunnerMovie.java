package com.ohmyapp.elasticsearch.dao.service;

import com.ohmyapp.elasticsearch.dao.entity.EntityMovie;
import com.ohmyapp.elasticsearch.dao.entity.Genre;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * author by lip on 12/8/2015.
 */
@Component
public class RunnerMovie {
    @Autowired
    private ElasticMovieService movieService;

    private static final Logger logger = LoggerFactory.getLogger(RunnerMovie.class);

    /**
     * run movie.
     * @throws Exception ex
     */
    public void run() throws Exception {
        addSomeMovies();
        // We indexed star wars and pricess bride to our movie
        // listing in elastic search
        //Lets query if we have a movie with Star Wars as name
        List<EntityMovie> starWarsNameQuery = movieService.getByName("Star Wars");
        logger.info("Content of star wars name query is {}", starWarsNameQuery);
        //Lets query if we have a movie with The Princess Bride as name
        List<EntityMovie> brideQuery = movieService.getByName("The Princess Bride");
        logger.info("Content of princess bride name query is {}", brideQuery);
        //Lets query if we have a movie with rating between 6 and 9
        List<EntityMovie> byRatingInterval = movieService.getByRatingInterval(6d, 9d);
        logger.info("Content of Rating Interval query is {}", byRatingInterval);
    }

    // add star wars and
    // princess bride as a movie
    // to elastic search
    private void addSomeMovies() {
        EntityMovie starWars = getFirstMovie();
        movieService.addMovie(starWars);
        EntityMovie princessBride = getSecondMovie();
        movieService.addMovie(princessBride);
    }

    private EntityMovie getSecondMovie() {
        EntityMovie secondMovie = new EntityMovie();
        secondMovie.setId("2");
        secondMovie.setRating(8.4d);
        secondMovie.setName("The Princess Bride");
        List<Genre> princessPrideGenre = new ArrayList<>();
        princessPrideGenre.add(new Genre("ACTION"));
        princessPrideGenre.add(new Genre("ROMANCE"));
        secondMovie.setGenre(princessPrideGenre);
        return secondMovie;
    }

    private EntityMovie getFirstMovie() {
        EntityMovie firstMovie = new EntityMovie();
        firstMovie.setId("1");
        firstMovie.setRating(9.6d);
        firstMovie.setName("Star Wars");
        List<Genre> starWarsGenre = new ArrayList<>();
        starWarsGenre.add(new Genre("ACTION"));
        starWarsGenre.add(new Genre("SCI_FI"));
        firstMovie.setGenre(starWarsGenre);
        return firstMovie;
    }
}
