package com.ouhamza.crup_app.service;

import com.ouhamza.crup_app.dao.StudentRepo;
import com.ouhamza.crup_app.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author <a href="https://github.com/ouhamzalhss"> Lhouceine OUHAMZA </a>
 */

@Service
public class StudentService {

    @Autowired
    private StudentRepo studentRepo;

    public List<Student> getAll(){
        return studentRepo.findAll();
    }

    public void save(Student student){
        studentRepo.save(student);
    }


    public Student get(long id) {
        return studentRepo.findById(id).get();
    }

    public void delete(long id) {
        studentRepo.deleteById(id);
    }
}
