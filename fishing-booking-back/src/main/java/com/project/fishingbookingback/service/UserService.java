package com.project.fishingbookingback.service;

import com.project.fishingbookingback.exception.EntityNotFoundException;
import com.project.fishingbookingback.model.User;
import com.project.fishingbookingback.repository.UserRepository;
import org.springframework.stereotype.Service;


@Service
public class UserService {
    private final AddressService addressService;
    private final UserRepository userRepository;

    public UserService(AddressService addressService, UserRepository userRepository) {
        this.addressService = addressService;
        this.userRepository = userRepository;
    }

    public User findByID(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(User.class.getName()));
    }

    public User findByEmail(String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new EntityNotFoundException(User.class.getName());
        }
        return user;
    }


}
