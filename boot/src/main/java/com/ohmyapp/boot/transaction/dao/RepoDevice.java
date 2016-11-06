package com.ohmyapp.boot.transaction.dao;

import com.ohmyapp.boot.transaction.entity.EntityAppliance;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;


/**
 * author by lip on 12/5/2015.
 */
@EnableJpaRepositories
public interface RepoDevice extends CrudRepository<EntityAppliance, Long> {
}
