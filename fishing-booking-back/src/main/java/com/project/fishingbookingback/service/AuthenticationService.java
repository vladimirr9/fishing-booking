package com.project.fishingbookingback.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.project.fishingbookingback.exception.EntityAlreadyExistsException;
import com.project.fishingbookingback.exception.InvalidCredentialsException;
import com.project.fishingbookingback.model.Admin;
import com.project.fishingbookingback.model.ProviderRegistration;
import com.project.fishingbookingback.model.User;
import org.springframework.stereotype.Service;

import java.util.Date;

import static com.project.fishingbookingback.Constants.EXPIRATION_TIME;
import static com.project.fishingbookingback.Constants.SECRET;

@Service
public class AuthenticationService {
    private final UserService userService;
    private final ProviderRegistrationService providerRegistrationService;

    public AuthenticationService(UserService userService, ProviderRegistrationService providerRegistrationService) {
        this.userService = userService;
        this.providerRegistrationService = providerRegistrationService;
    }

    public ProviderRegistration registerProvider(ProviderRegistration providerRegistration) {
        return providerRegistrationService.createRequest(providerRegistration);
    }

    public String login(String email, String password) {
        User existingUser = userService.findByEmail(email);
        if (existingUser.getPassword().equals(password)) {
            return JWT.create()
                    .withSubject(email)
                    .withClaim("role", existingUser.getRole().toString())
                    .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                    .sign(Algorithm.HMAC512(SECRET));
        } else throw new InvalidCredentialsException();
    }

    public User registerAdmin(Admin admin) {
        if (providerRegistrationService.registrationExists(admin.getEmail()) || userService.userExists(admin.getEmail())) {
            throw new EntityAlreadyExistsException(admin.getEmail());
        }
        return userService.saveUser(admin);
    }
}
