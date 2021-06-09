package com.ouhamza.crup_app.dao;

import com.ouhamza.crup_app.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Lhouceine OUHAMZA
 */

@Repository
public interface UserRepo extends JpaRepository<Users, Long> {

    Users findByUsername(String username);
}
