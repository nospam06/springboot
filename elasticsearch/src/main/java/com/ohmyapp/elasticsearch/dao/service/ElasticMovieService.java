package com.ohmyapp.elasticsearch.dao.service;

import com.ohmyapp.elasticsearch.dao.entity.EntityMovie;
import com.ohmyapp.elasticsearch.dao.repo.RepoMovie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * author by lip on 12/8/2015.
 */
@Service
public class ElasticMovieService {
    @Autowired
    private RepoMovie repository;

    public List<EntityMovie> getByName(String name) {
        return repository.findByName(name);
    }

    public List<EntityMovie> getByRatingInterval(Double beginning, Double end) {
        return repository.findByRatingBetween(beginning, end);
    }

    public void addMovie(EntityMovie movie) {
        repository.save(movie);
    }
}
