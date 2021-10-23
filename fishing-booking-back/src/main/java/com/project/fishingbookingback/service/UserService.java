package com.project.fishingbookingback.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.project.fishingbookingback.exception.EntityNotFoundException;
import com.project.fishingbookingback.exception.InvalidCredentialsException;
import com.project.fishingbookingback.model.Address;
import com.project.fishingbookingback.model.User;
import com.project.fishingbookingback.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;

import static com.project.fishingbookingback.Constants.EXPIRATION_TIME;
import static com.project.fishingbookingback.Constants.SECRET;


@Service
public class UserService {
    private final AddressService addressService;
    private final UserRepository userRepository;

    public UserService(AddressService addressService, UserRepository userRepository) {
        this.addressService = addressService;
        this.userRepository = userRepository;
    }

    public User findByID(Long id) {
        Objects.requireNonNull(id);
        return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(User.class.getName()));
    }

    public User register(User user) {
        Objects.requireNonNull(user);
        Address address = addressService.insert(user.getAddress());
        user.setAddress(address);
        return userRepository.save(user);
    }

    public String login(String email, String password) {
        User existingUser = userRepository.findByEmail(email);
        if (existingUser == null) {
            throw new InvalidCredentialsException();
        }
        if (existingUser.getPassword().equals(password)) {
            return JWT.create()
                    .withSubject(email)
                    .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                    .sign(Algorithm.HMAC512(SECRET));
        } else throw new InvalidCredentialsException();
    }
}
