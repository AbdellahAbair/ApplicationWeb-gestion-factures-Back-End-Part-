package com.firsttest.autho.dao;

import com.firsttest.autho.entities.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource
public interface AppRoleRepository extends MongoRepository<AppRole,Long> {
    public AppRole findByRoleName(String roleName);


}
