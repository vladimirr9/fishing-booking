package com.project.fishingbookingback.service;

import com.project.fishingbookingback.model.User;
import com.project.fishingbookingback.repository.UserRepository;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;


import java.net.UnknownHostException;

@Service
public class UserRegistrationService {
    private final UserService userService;
    private final UserRepository userRepository;
    private final EmailService emailService;
    private final Environment environment;
    private final String confirmationControllerPath="/api/auth/confirm-client/";
    private final String emailText = "To confirm your profile creating press following link. This registration is one time only. DO NOT PRESS it multiple times!\n";

    public UserRegistrationService(UserService userService, UserRepository userRepository, EmailService emailService, Environment environment) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.emailService = emailService;
        this.environment = environment;
    }

    public User registerUser(User user) throws UnknownHostException {
        int port = Integer.parseInt(environment.getProperty("server.port"));
        userRepository.save(user);
        emailService.sendSimpleMessage(user.getEmail(),"Confirm registration",emailText+"http://localhost:"+port+confirmationControllerPath+user.getId());
        System.out.println(emailText+"http://localhost:"+port+confirmationControllerPath+user.getId());
        return user;
    }

    public User confirmUser(User user){
        user.setEnabled(true);
        userRepository.save(user);
        return user;
    }
}
