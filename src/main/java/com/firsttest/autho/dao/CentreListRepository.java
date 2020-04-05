package com.firsttest.autho.dao;

import com.firsttest.autho.entities.CentreList;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource
public interface CentreListRepository extends MongoRepository<CentreList,String> {
}
