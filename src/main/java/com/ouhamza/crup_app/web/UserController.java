package com.ouhamza.crup_app.web;

import com.ouhamza.crup_app.model.Users;
import com.ouhamza.crup_app.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author <a href="https://github.com/ouhamzalhss"> Lhouceine OUHAMZA </a>
 */

@Controller
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("user", new Users());
        return "register";
    }

    @PostMapping("/register")
    public String saveUser(Users user){
        // TODO: service
        try {
            userService.register(user);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "redirect:/login";
    }
}
