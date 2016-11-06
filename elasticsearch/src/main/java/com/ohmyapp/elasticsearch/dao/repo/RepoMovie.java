package com.ohmyapp.elasticsearch.dao.repo;

import com.ohmyapp.elasticsearch.dao.entity.EntityMovie;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * author by lip on 12/8/2015.
 */
public interface RepoMovie extends ElasticsearchRepository<EntityMovie, String> {
    List<EntityMovie> findByName(String name);

    List<EntityMovie> findByRatingBetween(Double beginning, Double end);
}
