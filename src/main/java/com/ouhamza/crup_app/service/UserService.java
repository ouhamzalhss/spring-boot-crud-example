package com.ouhamza.crup_app.service;

import com.ouhamza.crup_app.dao.ConfirmationTokenRepository;
import com.ouhamza.crup_app.dao.RoleRepo;
import com.ouhamza.crup_app.dao.UserRepo;
import com.ouhamza.crup_app.model.ConfirmationToken;
import com.ouhamza.crup_app.model.Roles;
import com.ouhamza.crup_app.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author <a href="https://github.com/ouhamzalhss"> Lhouceine OUHAMZA </a>
 */
@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;

    @Autowired
    private EmailSenderService emailSenderService;



//    public void register(Users user) throws Exception{
//
//        if(checkIfUserExist(user.getUsername())){
//            throw new Exception("User Already existe");
//        }
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        user.setActive(true);
//        userRepo.save(user);
//        addRoleToUser(user.getUsername(), "USER");
//
//    }

    public void register(Users user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.save(user);
        addRoleToUser(user.getUsername(), "USER");

        ConfirmationToken confirmationToken = new ConfirmationToken(user);
        confirmationTokenRepository.save(confirmationToken);

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(user.getEmail());
        mailMessage.setSubject("Complete registration!");
        mailMessage.setFrom("learn.to.code.webapp@gmail.com");
        mailMessage.setText("To confirm your account please click here: "
                + "http://localhost:9090/confirm-account?token="+ confirmationToken.getConfirmationToken());

        emailSenderService.sendMail(mailMessage);


    }


    public boolean checkIfUserExist(String username){
        return userRepo.findByUsername(username) != null;
    }


    public void addRoleToUser(String username,String roleName){
        Roles role = roleRepo.findByRoleName(roleName);
        Users user = userRepo.findByUsername(username);
        user.getRoles().add(role);
        userRepo.save(user);
    }
}
