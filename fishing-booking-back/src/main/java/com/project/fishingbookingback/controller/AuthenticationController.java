package com.project.fishingbookingback.controller;

import com.project.fishingbookingback.model.ProviderRegistration;
import com.project.fishingbookingback.model.User;
import com.project.fishingbookingback.service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/auth")
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping(value = "/provider-registration")
    public ResponseEntity<ProviderRegistration> register(@Valid @RequestBody ProviderRegistration providerRegistration) {
        ProviderRegistration request = authenticationService.registerProvider(providerRegistration);
        return ResponseEntity.ok(request);
    }


    @PostMapping(value = "/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        String token = authenticationService.login(user.getEmail(), user.getPassword());
        return ResponseEntity.ok(token);
    }

}
