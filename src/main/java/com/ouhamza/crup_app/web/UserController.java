package com.ouhamza.crup_app.web;

import com.ouhamza.crup_app.dao.ConfirmationTokenRepository;
import com.ouhamza.crup_app.dao.UserRepo;
import com.ouhamza.crup_app.model.ConfirmationToken;
import com.ouhamza.crup_app.model.Users;
import com.ouhamza.crup_app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author <a href="https://github.com/ouhamzalhss"> Lhouceine OUHAMZA </a>
 */

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;


    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("user", new Users());
        return "register";
    }


    @PostMapping("/register")
    public ModelAndView saveUser(ModelAndView modelAndView, Users user){
        Users existingUser = userRepo.findByEmail(user.getEmail());
        if(existingUser != null){
            modelAndView.addObject("message", "This email already exists!");
            modelAndView.setViewName("error");
        }else{
            userService.register(user);
            modelAndView.addObject("emailId",user.getEmail());
            modelAndView.setViewName("successfulRegistration");
        }
        return modelAndView;
    }

    @GetMapping("/confirm-account")
    public ModelAndView confirmAccount(ModelAndView modelAndView, @RequestParam("token") String confirmToken){
        ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmToken);
        if(token != null){
            Users user = userRepo.findByEmail(token.getUser().getEmail());
            user.setActive(true);
            userRepo.save(user);
            confirmationTokenRepository.delete(token);
            modelAndView.setViewName("accountVerified");
        }else{
            modelAndView.addObject("message", "The link is invalid or broken");
            modelAndView.setViewName("error");
        }
            return modelAndView;
    }


}
