package com.project.fishingbookingback.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.project.fishingbookingback.exception.EntityAlreadyExistsException;
import com.project.fishingbookingback.exception.EntityNotFoundException;
import com.project.fishingbookingback.exception.InvalidCredentialsException;
import com.project.fishingbookingback.exception.UserNotConfirmedException;
import com.project.fishingbookingback.model.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.UnknownHostException;
import java.util.Date;

import static com.project.fishingbookingback.Constants.EXPIRATION_TIME;
import static com.project.fishingbookingback.Constants.SECRET;

@Service
public class AuthenticationService {
    private final UserService userService;
    private final ProviderRegistrationService providerRegistrationService;
    private final ClientRegistrationService clientRegistrationService;

    public AuthenticationService(UserService userService, ProviderRegistrationService providerRegistrationService, ClientRegistrationService clientRegistrationService) {
        this.userService = userService;
        this.providerRegistrationService = providerRegistrationService;
        this.clientRegistrationService = clientRegistrationService;
    }

    public ProviderRegistration registerProvider(ProviderRegistration providerRegistration) {
        return providerRegistrationService.createRequest(providerRegistration);
    }

    public String login(String email, String password) {
        User existingUser = userService.findByEmail(email);
        checkEnability(existingUser);

        if (existingUser.getPassword().equals(password)) {
            return JWT.create()
                    .withSubject(email)
                    .withClaim("role", existingUser.getRole().toString())
                    .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                    .sign(Algorithm.HMAC512(SECRET));
        } else throw new InvalidCredentialsException();
    }

    public Client ConfirmClient(Long userID){
            Client client = (Client) userService.findByID(userID);
            if(client==null)
                throw new EntityNotFoundException(client.getClass().getName());

            if(client.isEnabled())
                throw new EntityAlreadyExistsException(client.getEmail());

            return clientRegistrationService.confirmClient(client);
    }

    @Transactional(readOnly = true)
    public Client registerClient(Client client) throws UnknownHostException {
        if(providerRegistrationService.registrationExists(client.getEmail()) || userService.userExists(client.getEmail()))
            throw new EntityAlreadyExistsException(client.getEmail());

        return clientRegistrationService.registerClient(client);
    }

    public User registerAdmin(Admin admin) {
        if (providerRegistrationService.registrationExists(admin.getEmail()) || userService.userExists(admin.getEmail())) {
            throw new EntityAlreadyExistsException(admin.getEmail());
        }
        return userService.saveUser(admin);
    }
    
    private void checkEnability(User existingUser){
        if(existingUser.getRole() == Role.ROLE_CLIENT){
            Client client = (Client) existingUser;
            if(!client.isEnabled()) throw new UserNotConfirmedException("User account not yet confirmed!");
        }
    }
}
