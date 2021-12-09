package com.project.fishingbookingback.dto.mapper;

import com.project.fishingbookingback.dto.response.UserDetailsResponseDTO;
import com.project.fishingbookingback.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserDetailsResponseDTO toResponseDTO(User user) {
        return new UserDetailsResponseDTO(user.getId(),
                user.getEmail(),
                user.getFirstName(),
                user.getLastName(),
                user.getPhoneNumber(),
                user.getAddress().getStreetAndNumber(),
                user.getAddress().getCity(),
                user.getAddress().getCountry());
    }

}
