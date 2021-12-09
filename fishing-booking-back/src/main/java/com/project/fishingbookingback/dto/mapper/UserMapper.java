package com.project.fishingbookingback.dto.mapper;

import com.project.fishingbookingback.dto.request.AdminRegistrationDTO;
import com.project.fishingbookingback.dto.response.UserDetailsResponseDTO;
import com.project.fishingbookingback.model.Address;
import com.project.fishingbookingback.model.Admin;
import com.project.fishingbookingback.model.Role;
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

    public Admin toModel(AdminRegistrationDTO adminRegistrationDTO) {
        Admin admin = new Admin();
        admin.setEmail(adminRegistrationDTO.getEmail());
        admin.setPassword(adminRegistrationDTO.getPassword());
        admin.setFirstName(adminRegistrationDTO.getFirstName());
        admin.setLastName(adminRegistrationDTO.getLastName());
        admin.setPhoneNumber(adminRegistrationDTO.getPhoneNumber());
        admin.setAddress(new Address(adminRegistrationDTO.getStreetAndNumber(),
                adminRegistrationDTO.getCity(),
                adminRegistrationDTO.getCountry(),
                null, null));
        admin.setRole(Role.ROLE_ADMIN);
        return admin;
    }

}
