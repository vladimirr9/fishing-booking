package com.project.fishingbookingback.controller;

import com.project.fishingbookingback.dto.mapper.UserMapper;
import com.project.fishingbookingback.dto.request.AdminRegistrationDTO;
import com.project.fishingbookingback.dto.request.UserRegistrationDTO;
import com.project.fishingbookingback.dto.response.TokenDTO;
import com.project.fishingbookingback.model.Admin;
import com.project.fishingbookingback.model.ProviderRegistration;
import com.project.fishingbookingback.model.User;
import com.project.fishingbookingback.service.AuthenticationService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.UnknownHostException;

@RestController
@RequestMapping(value = "/api/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final UserMapper userMapper;

    public AuthenticationController(AuthenticationService authenticationService, UserMapper userMapper) {
        this.authenticationService = authenticationService;
        this.userMapper = userMapper;
    }

    @PostMapping(value = "/provider-registration")
    public ResponseEntity<ProviderRegistration> register(@Valid @RequestBody ProviderRegistration providerRegistration) {
        ProviderRegistration request = authenticationService.registerProvider(providerRegistration);
        return ResponseEntity.ok(request);
    }

    @PostMapping(value = "/admins")
    public ResponseEntity<User> registerAdmin(@Valid @RequestBody AdminRegistrationDTO adminRegistrationDTO) {
        Admin admin = userMapper.toModel(adminRegistrationDTO);
        User newAdmin = authenticationService.registerAdmin(admin);
        return ResponseEntity.ok(newAdmin);
    }

    @PostMapping(value = "/clients")
    public ResponseEntity<User> registerUser(@Valid @RequestBody UserRegistrationDTO userDTO) throws UnknownHostException {
        User user = userMapper.toModel(userDTO);
        authenticationService.registerUser(user);
        return ResponseEntity.ok(user);
    }

    @GetMapping(value = "/confirm-client/{clientID}")
    public ResponseEntity<String> confirmUser(@PathVariable Long clientID) {
        User user = authenticationService.ConfirmUser(clientID);
        return ResponseEntity.ok("Account with email:" + user.getEmail() + " successfully registered!");
    }

    //test
    @PostMapping(value = "/login")
    public ResponseEntity<TokenDTO> login(@RequestBody User user) {
        String token = authenticationService.login(user.getEmail(), user.getPassword());
        TokenDTO tokenDTO = new TokenDTO(token);
        return ResponseEntity.ok(tokenDTO);
    }

}
