package com.ohmyapp.boot.transaction.service;

import com.ohmyapp.boot.transaction.dao.RepoDevice;
import com.ohmyapp.boot.transaction.entity.EntityAppliance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * author by lip on 12/21/2015.
 */
@Service
@Transactional
public class ApplianceService {
    @Autowired
    private RepoDevice repository;

    public EntityAppliance findById(Long id) {
        return repository.findOne(id);
    }

    public void updateAppliance(EntityAppliance appliance) {
        repository.save(appliance);
    }

}
