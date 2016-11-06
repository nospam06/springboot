package com.ohmyapp.boot.transaction.dao;

import com.ohmyapp.boot.transaction.entity.EntityAddress;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


/**
 * author by lip on 12/5/2015.
 */
@Repository
public interface RepoAddress extends PagingAndSortingRepository<EntityAddress, Long> {
}
