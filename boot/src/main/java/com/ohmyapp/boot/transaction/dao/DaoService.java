package com.ohmyapp.boot.transaction.dao;

import com.ohmyapp.boot.transaction.entity.EntityService;
import org.springframework.data.repository.PagingAndSortingRepository;


/**
 * author by lip on 12/5/2015.
 */
public interface DaoService extends PagingAndSortingRepository<EntityService, Long> {
}
