package com.firsttest.autho.dao;

import com.firsttest.autho.entities.AppUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;

@Repository
@RepositoryRestResource
public interface AppUserRepository extends MongoRepository<AppUser,Long> {
    public AppUser findByUsername(String username);
    public List<AppUser> findByActived(boolean active);

}
