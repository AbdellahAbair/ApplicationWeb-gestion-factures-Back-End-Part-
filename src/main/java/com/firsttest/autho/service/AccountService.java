package com.firsttest.autho.service;

import com.firsttest.autho.entities.AppRole;
import com.firsttest.autho.entities.AppUser;
import com.firsttest.autho.entities.CentreList;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collection;
import java.util.List;

public interface AccountService  {
    public AppUser saveUser(String username,String password,String confirmedPassword);
    public AppRole save(AppRole role);
    public AppUser loadUserByUsername(String username);
    public void addRoleToUser(String username,String rolename);
    public void deleteUser();
    public void updateuser(boolean active,String username);
    public void loadusers();
    public void savecentre(CentreList c);
    public List<AppUser> listesroles(boolean active);

}
