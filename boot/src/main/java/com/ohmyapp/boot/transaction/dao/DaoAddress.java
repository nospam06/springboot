package com.ohmyapp.boot.transaction.dao;

import com.ohmyapp.boot.transaction.entity.EntityAddress;
import org.springframework.data.repository.PagingAndSortingRepository;


/**
 * author by lip on 12/5/2015.
 */
public interface DaoAddress extends PagingAndSortingRepository<EntityAddress, Long> {
}
