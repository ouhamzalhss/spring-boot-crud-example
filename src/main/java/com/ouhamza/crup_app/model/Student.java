package com.ouhamza.crup_app.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * @author <a href="https://github.com/ouhamzalhss"> Lhouceine OUHAMZA </a>
 */
@Entity
@Data
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "field can't be empty")
    @Size(min = 4, max = 30, message = "{Size.Student.name}")
    private String name;
    @NotEmpty(message = "field can't be empty")
    @Email
    private String email;
    private String address;
    private String phone;


}
