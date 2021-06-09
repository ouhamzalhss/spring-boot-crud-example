package com.ouhamza.crup_app.dao;

import com.ouhamza.crup_app.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Lhouceine OUHAMZA
 */
@Repository
public interface StudentRepo extends JpaRepository<Student, Long> {

    Page<Student> findByNameContains(String name, Pageable pageable);

}
