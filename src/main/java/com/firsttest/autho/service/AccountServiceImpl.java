package com.firsttest.autho.service;

import com.firsttest.autho.dao.AppRoleRepository;
import com.firsttest.autho.dao.AppUserRepository;
import com.firsttest.autho.dao.CentreListRepository;
import com.firsttest.autho.entities.AppRole;
import com.firsttest.autho.entities.AppUser;
import com.firsttest.autho.entities.CentreList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;

@Service
@Transactional
@RestController
public class AccountServiceImpl implements AccountService{

    private AppUserRepository appUserRepository;
    private AppRoleRepository appRoleRepository;
    private CentreListRepository centreListRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public AccountServiceImpl(AppUserRepository appUserRepository, AppRoleRepository appRoleRepository, BCryptPasswordEncoder bCryptPasswordEncoder,CentreListRepository centreListRepository) {
        this.appUserRepository = appUserRepository;
        this.appRoleRepository = appRoleRepository;
        this.centreListRepository=centreListRepository;


    }

    @Override
    public AppUser saveUser(String username, String password, String confirmedPassword) {
       System.out.println(username);

        AppUser  user=appUserRepository.findByUsername(username);
        if(user!=null) throw new RuntimeException("User already exists");
//        if(!password.equals(confirmedPassword)) throw new RuntimeException("Please confirm your password");
        AppUser appUser=new AppUser();
        //appUser.setId(id);
        appUser.setUsername(username);
        //System.out.println( bCryptPasswordEncoder.encode(password));
        //System.out.println(password);
        String Pass= bCryptPasswordEncoder.encode(password);
        appUser.setPassword(Pass);
        appUser.setActived(true);
        appUserRepository.save(appUser);
        addRoleToUser(username,"USER");
        return appUser;
    }

    @Override
    public AppRole save(AppRole role) {
        return appRoleRepository.save(role);
    }

    @Override
    public AppUser loadUserByUsername( String username) {
        return appUserRepository.findByUsername(username);
    }

    @Override
    public void addRoleToUser(String username, String rolename) {
        AppUser appUser=appUserRepository.findByUsername(username);
        AppRole appRole=appRoleRepository.findByRoleName(rolename);
        //System.out.println(appRole);
        //System.out.println(appUser);
        //Collection<AppRole> liste_users=new ArrayList<>();
        appUser.getRoles().add(appRole);
        //System.out.println(appUser);
        appUserRepository.save(appUser);

    }

    @Override
    public void deleteUser() {
        this.appUserRepository.deleteAll();
        this.appRoleRepository.deleteAll();
        this.centreListRepository.deleteAll();
    }

    @Override
    public void updateuser(boolean active, String username) {
        AppUser appUser=appUserRepository.findByUsername(username);
        appUser.setActived(active);
        appUserRepository.save(appUser);
    }

    @Override
    public void loadusers() {

        /*for (AppUser user :appUserRepository.findAll())
        {
            System.out.println(user);
        }*/

        appUserRepository.findAll().forEach(user->{
            System.out.println(user.getUsername());

                });

    }

    @Override
    public void savecentre(CentreList c) {
        this.centreListRepository.save(c);
    }

    @Override

    public List<AppUser> listesroles(boolean active) {
       return appUserRepository.findByActived(active);
    }



}
