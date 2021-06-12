package com.ouhamza.crup_app.web;

import com.ouhamza.crup_app.dao.StudentRepo;
import com.ouhamza.crup_app.model.Student;
import com.ouhamza.crup_app.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

/**
 * @author <a href="https://github.com/ouhamzalhss"> Lhouceine OUHAMZA </a>
 */

@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentRepo studentRepo;

//    @RequestMapping("/")
//    public String index(Model model){
//        model.addAttribute("students", studentService.getAll());
//        return "index";
//    }


    @GetMapping("/")
    public String findAllPage(
            @RequestParam(value = "page", required = false, defaultValue = "0") int pageNumber,
            @RequestParam(value = "size", required = false, defaultValue = "5") int size,
            Model model,
            @RequestParam(value = "keyWord", defaultValue = "") String keyWord) {
        Page<Student> studentPage = studentRepo.findByNameContains(keyWord,PageRequest.of(pageNumber, size));
        model.addAttribute("students", studentPage.getContent());
        model.addAttribute("pages", new int[studentPage.getTotalPages()]);
        model.addAttribute("currentPage", pageNumber);
        model.addAttribute("keyWord", keyWord);
        return "index";
    }



    @RequestMapping("/new")
    public String newStudent(Model model){
        model.addAttribute("student", new Student());
        return "student-form";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("student") Student student, BindingResult result){
        if(result.hasErrors()){
            return "student-form";
        }
        studentService.save(student);
        return "redirect:/";
    }


    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable("id") long id, Model model){
        Student student = studentService.get(id);
        model.addAttribute("student", student);
        return "student-form";
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable("id") long id, RedirectAttributes attributes){
        studentService.delete(id);
        attributes.addFlashAttribute("success", "The student has deleted!");
        return "redirect:/";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/403")
    public String accessDenied(){
        return "accessDenied";
    }



}
