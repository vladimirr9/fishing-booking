package com.project.fishingbookingback.controller;


import com.project.fishingbookingback.dto.mapper.UserMapper;
import com.project.fishingbookingback.dto.request.ChangePasswordRequstDTO;
import com.project.fishingbookingback.dto.request.UserDetailsRequestDTO;
import com.project.fishingbookingback.dto.response.UserDetailsResponseDTO;
import com.project.fishingbookingback.model.User;
import com.project.fishingbookingback.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping(value = "/api/users")
public class UserController {

    private final UserMapper userMapper;
    private final UserService userService;

    public UserController(UserMapper userMapper, UserService userService) {
        this.userMapper = userMapper;
        this.userService = userService;
    }

    @PreAuthorize("hasRole('ROLE_FISHING_INSTRUCTOR') or hasRole('ROLE_HOME_OWNER') or hasRole('ROLE_BOAT_OWNER') or hasRole('ROLE_CLIENT') or hasRole('ROLE_ADMIN')")
    @GetMapping(value = "/{email}")
    public ResponseEntity<UserDetailsResponseDTO> getUserDetails(@PathVariable String email) {
        User user = userService.findByEmail(email);
        UserDetailsResponseDTO userDetailsResponseDTO = userMapper.toResponseDTO(user);
        return ResponseEntity.ok(userDetailsResponseDTO);
    }

    @PreAuthorize("hasRole('ROLE_FISHING_INSTRUCTOR') or hasRole('ROLE_HOME_OWNER') or hasRole('ROLE_BOAT_OWNER') or hasRole('ROLE_CLIENT') or hasRole('ROLE_ADMIN')")
    @PutMapping(value = "/{id}")
    public ResponseEntity<UserDetailsResponseDTO> updateUserDetails(@Valid @RequestBody UserDetailsRequestDTO userDetailsRequestDTO, @PathVariable Long id) {
        UserDetailsResponseDTO responseBody = userMapper.toResponseDTO(userService.updateUser(id, userDetailsRequestDTO));
        return ResponseEntity.ok(responseBody);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping()
    public ResponseEntity<List<User>> getAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    @PreAuthorize("hasRole('ROLE_FISHING_INSTRUCTOR') or hasRole('ROLE_HOME_OWNER') or hasRole('ROLE_BOAT_OWNER') or hasRole('ROLE_CLIENT') or hasRole('ROLE_ADMIN')")
    @PutMapping(value = "/{email}/passwords")
    public ResponseEntity<HttpStatus> changePassword(@Valid @RequestBody ChangePasswordRequstDTO changePasswordRequstDTO, @PathVariable String email) {
        userService.changePassword(email, changePasswordRequstDTO);
        return ResponseEntity.noContent().build();
    }
}
