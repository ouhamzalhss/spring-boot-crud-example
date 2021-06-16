package com.ouhamza.crup_app.dao;

import com.ouhamza.crup_app.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author <a href="https://github.com/ouhamzalhss"> Lhouceine OUHAMZA </a>
 */
@Repository
public interface UserRepo extends JpaRepository<Users,Long> {

    Users findByUsername(String username);
    Users findByEmail(String email);
}

