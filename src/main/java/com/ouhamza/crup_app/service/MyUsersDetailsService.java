package com.ouhamza.crup_app.service;

import com.ouhamza.crup_app.dao.UserRepo;
import com.ouhamza.crup_app.model.MyUserPrincipal;
import com.ouhamza.crup_app.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author <a href="https://github.com/ouhamzalhss"> Lhouceine OUHAMZA </a>
 */
@Service
public class MyUsersDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            Users user = userRepo.findByUsername(username);
            if(user == null || !user.isActive()) throw new UsernameNotFoundException(username);

            return new MyUserPrincipal(user);
    }
}
