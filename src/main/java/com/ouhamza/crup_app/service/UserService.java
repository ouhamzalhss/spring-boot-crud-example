package com.ouhamza.crup_app.service;

import com.ouhamza.crup_app.dao.RoleRepo;
import com.ouhamza.crup_app.dao.UserRepo;
import com.ouhamza.crup_app.model.Roles;
import com.ouhamza.crup_app.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author <a href="https://github.com/ouhamzalhss"> Lhouceine OUHAMZA </a>
 */
@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void register(Users user) throws Exception{

        if(checkIfUserExist(user.getUsername())){
            throw new Exception("User Already existe");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setActive(true);
        userRepo.save(user);
        addRoleToUser(user.getUsername(), "USER");

    }

    public boolean checkIfUserExist(String username){
        return userRepo.findByUsername(username)!=null ? true : false;
    }


    public void addRoleToUser(String username,String roleName){
        Roles role = roleRepo.findByRoleName(roleName);
        Users user = userRepo.findByUsername(username);
        user.getRoles().add(role);
        userRepo.save(user);
    }
}
