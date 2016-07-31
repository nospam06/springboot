package com.ohmyapp.elasticsearch.dao;

import com.ohmyapp.elasticsearch.entity.EntityMovie;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * author by lip on 12/8/2015.
 */
@Repository
public interface DaoMovie extends ElasticsearchRepository<EntityMovie, String> {
    List<EntityMovie> findByName(String name);

    List<EntityMovie> findByRatingBetween(Double beginning, Double end);
}
