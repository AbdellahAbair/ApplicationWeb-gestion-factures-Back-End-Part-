package com.firsttest.autho.dao;

import com.firsttest.autho.entities.Utilisateur;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UtilisateurRepositories extends MongoRepository<Utilisateur,String> {
}
