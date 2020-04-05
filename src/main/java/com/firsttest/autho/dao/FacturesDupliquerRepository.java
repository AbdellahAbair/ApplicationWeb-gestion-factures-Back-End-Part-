package com.firsttest.autho.dao;

import com.firsttest.autho.entities.Facture;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RepositoryRestResource
public interface FacturesDupliquerRepository extends MongoRepository<Facture,String> {
    public List<Facture> findByCodeExp(String code_exp);
    public List<Facture> findByCodeClt(String code_clt);
    public List<Facture> findByDateFac(String date_fac);
    public List<Facture> findByNumFac(int num_fac);
    public List<Facture> findByCodeExpAndCodeClt(String code_exp,String code_clt);
    public List<Facture> findByCodeExpAndNumFac(String code_exp,int num_fac);
    public List<Facture> findByCodeExpAndDateFac(String code_exp,String date_fac);
    public List<Facture> findByCodeCltAndNumFac(String code_clt,int num_fac);
    public List<Facture> findByCodeCltAndDateFac(String code_clt,String date_fac);
    public List<Facture> findByNumFacAndDateFac(int num_fac,String date_fac);
    public List<Facture> findByCodeExpAndCodeCltAndNumFac(String code_exp,String code_clt,int num_fac);
    public List<Facture> findByCodeExpAndCodeCltAndDateFac(String code_exp,String code_clt,String date_fac);
    public List<Facture> findByCodeExpAndNumFacAndDateFac(String code_exp,int num_fac,String date_fac);
    public List<Facture> findByNumFacAndCodeCltAndDateFac(int num_fac,String code_clt,String date_fac);
    public List<Facture> findByCodeExpAndCodeCltAndNumFacAndDateFac(String code_exp,String code_clt,int num_fac,String date_fac);
}
