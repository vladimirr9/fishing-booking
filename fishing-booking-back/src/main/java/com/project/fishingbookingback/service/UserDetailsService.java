package com.project.fishingbookingback.service;

import com.project.fishingbookingback.model.User;
import com.project.fishingbookingback.model.UserDetails;
import com.project.fishingbookingback.repository.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {


    private final UserRepository repository;

    public UserDetailsService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = repository.findByEmail(s);
        if (user == null) {
            return null;
        }
        return new UserDetails(user);
    }
}
