package com.ouhamza.crup_app.dao;

import com.ouhamza.crup_app.model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author <a href="https://github.com/ouhamzalhss"> Lhouceine OUHAMZA </a>
 */
@Repository
public interface RoleRepo extends JpaRepository<Roles, Long> {

    Roles findByRoleName(String RoleName);
}
