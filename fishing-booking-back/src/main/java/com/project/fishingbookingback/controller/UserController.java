package com.project.fishingbookingback.controller;


import com.project.fishingbookingback.dto.mapper.UserMapper;
import com.project.fishingbookingback.dto.request.UserDetailsRequestDTO;
import com.project.fishingbookingback.dto.response.UserDetailsResponseDTO;
import com.project.fishingbookingback.model.User;
import com.project.fishingbookingback.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@RequestMapping(value = "/api/users")
public class UserController {

    private final UserMapper userMapper;
    private final UserService userService;

    public UserController(UserMapper userMapper, UserService userService) {
        this.userMapper = userMapper;
        this.userService = userService;
    }

    @GetMapping(value = "/{email}")
    public ResponseEntity<UserDetailsResponseDTO> getUserDetails(@PathVariable String email) {
        User user = userService.findByEmail(email);
        UserDetailsResponseDTO userDetailsResponseDTO = userMapper.toResponseDTO(user);
        return ResponseEntity.ok(userDetailsResponseDTO);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<UserDetailsResponseDTO> updateUserDetails(@Valid @RequestBody UserDetailsRequestDTO userDetailsRequestDTO, @PathVariable Long id) {
        UserDetailsResponseDTO responseBody = userMapper.toResponseDTO(userService.updateUser(id, userDetailsRequestDTO));
        return ResponseEntity.ok(responseBody);
    }
}
